package net.runelite.client.plugins.microbot.sticktothescript.varrockanvil;

import net.runelite.api.ItemID;
import net.runelite.api.Skill;
import net.runelite.api.TileObject;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;
import net.runelite.client.plugins.microbot.smelting.enums.AnvilItem;
import net.runelite.client.plugins.microbot.smelting.enums.Bars;
import net.runelite.client.plugins.microbot.sticktothescript.common.Functions;
import net.runelite.client.plugins.microbot.util.bank.Rs2Bank;
import net.runelite.client.plugins.microbot.util.dialogues.Rs2Dialogue;
import net.runelite.client.plugins.microbot.util.gameobject.Rs2GameObject;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.player.Rs2Player;
import net.runelite.client.plugins.microbot.util.walker.Rs2Walker;
import net.runelite.client.plugins.microbot.util.widget.Rs2Widget;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

enum State {
    SMITHING,
    BANKING,
    WALK_TO_ANVIL,
    WALK_TO_BANK,
}

public class VarrockAnvilScript extends Script {

    public static String version = "1.0.1";
    public State state = State.BANKING;
    public String debug = "";
    private boolean expectingXPDrop = false;
    static int staminaTimer;
    private static WorldPoint AnvilLocation = new WorldPoint(3188, 3426, 0);
    private static WorldPoint BankLocation = new WorldPoint(3185, 3438, 0);
    private static List<Integer> AnvilIDs = Arrays.asList(2097);
    private static int AnvilMakeVarbitPlayer = 2224;
    private static int AnvilContainerWidgetID = 312;

    public boolean run(VarrockAnvilConfig config) {
        Bars barType = config.sBarType();
        AnvilItem anvilItem = config.sAnvilItem();

        Microbot.enableAutoRunOn = false;

        mainScheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            if (!super.run() || !Microbot.isLoggedIn()) {
                debug("Not running");
                return;
            }

            determineState(barType, anvilItem);

            // If the state is not smithing, then let's reset the variable as we are not expecting an XP drop
            if (state != State.SMITHING) {
                expectingXPDrop = false;
            }

            if (Rs2Dialogue.hasContinue()) {
                debug("Click to continue");
                Rs2Dialogue.clickContinue();
                expectingXPDrop = false;
                return;
            }

            switch (state) {
                case SMITHING:
                    if (Rs2Inventory.count(barType.toString()) < anvilItem.getRequiredBars()) {
                        debug("Out of bars");
                        return;
                    }

                    if (expectingXPDrop && Rs2Player.waitForXpDrop(Skill.SMITHING, 4500)) {
                        debug("Smithing in progress");
                        if(this.isRunning()) sleep(256, 789);
                        return;
                    }

//                TileObject anvilTile = Rs2GameObject.findObjectById(AnvilIDs.get(0));

                    if (Rs2GameObject.interact(2097)) {
                        debug("Using anvil");

                        // Wait until anvil screen is open
                        if(this.isRunning()) sleepUntil(() -> Rs2Widget.getWidget(AnvilContainerWidgetID, 1) != null, 5000);
                        if(this.isRunning()) sleep(186, 480);

                        if (Rs2Widget.getWidget(AnvilContainerWidgetID, 1) != null) {
                            if (Microbot.getVarbitPlayerValue(AnvilMakeVarbitPlayer) < Rs2Inventory.count(barType.getId())) {
                                debug("Selecting 'All' in the anvil");
                                Rs2Widget.clickWidget(312, 7);
                                if(this.isRunning()) sleep(186, 480);
                            }

                            Rs2Widget.clickWidget(AnvilContainerWidgetID, anvilItem.getChildId());
                            expectingXPDrop = true;
                            if(this.isRunning()) sleep(186, 480);
                        }
                    } else {
                        if (Rs2Player.isMoving()) {
                            return;
                        }

//                    debug("Walking to anvil");
//                    Rs2Walker.walkTo(AnvilLocation, 8);
//                    if(this.isRunning()) sleep(180, 540);
                    }

                    break;

                case BANKING:
                    debug("Banking");
                    bank(barType);
                    break;

                case WALK_TO_BANK:
                    if (Rs2Player.isMoving()) {
                        return;
                    }

                    if (!Rs2Player.isRunEnabled()) {
                        debug("Enabled run for bank");
                        Rs2Player.toggleRunEnergy(true);
                    }
                    Rs2Bank.openBank();

//               debug("Walking to bank");
//               Rs2Walker.walkTo(BankLocation, 10);
                    break;

                case WALK_TO_ANVIL:
                    if (Rs2Player.isMoving()) {
                        return;
                    }

                    if (!Rs2Player.isRunEnabled()) {
                        debug("Enabled run to anvil");
                        Rs2Player.toggleRunEnergy(true);
                    }

                    debug("Walking to anvil");
                    Rs2Walker.walkTo(AnvilLocation, 10);
                    break;

                default:
                    break;
            }
            if(this.isRunning()) sleep(256, 789);
            return;
        }, 0, 1000, TimeUnit.MILLISECONDS);
        return true;
    }

    // Determine the state of the script
    private void determineState(Bars barType, AnvilItem anvilItem) {
        debug("Determine state");

        if (Rs2Inventory.hasItemAmount(barType.toString(), anvilItem.getRequiredBars()) && Rs2Inventory.hasItem(ItemID.HAMMER)) {
            if (!Functions.closeToLocation(AnvilLocation)) {
                state = State.WALK_TO_ANVIL;
                debug("Walking to anvil");
            } else {
                state = State.SMITHING;
                debug("Smithing");
            }
        } else {
            if (!Functions.closeToLocation(BankLocation)) {
                state = State.WALK_TO_BANK;
                debug("Walking to bank");
            } else {
                debug("Banking for supplies");
                state = State.BANKING;
            }
        }
    }

    // Handle all banking actions
    private void bank(Bars barType) {
        if (Rs2Bank.openBank()) {
            if (this.isRunning()) sleepUntil(Rs2Bank::isOpen);
            debug("Bank is open");
            if (this.isRunning()) Rs2Bank.depositAllExcept(ItemID.HAMMER, barType.getId());
            debug("Items deposited");
            if (this.isRunning()) sleep(180, 540);
            if ( staminaTimer <= 2 && (Microbot.getClient().getEnergy() < 7500)) { useStaminaPotions(); }
            if (!Rs2Inventory.hasItem("Hammer")) {
                if (this.isRunning()) Rs2Bank.withdrawOne("Hammer");
                if (this.isRunning()) sleepUntil(() -> Rs2Inventory.hasItem("Hammer"), 3500);

                // Exit if we did not end up finding it.
                if (!Rs2Inventory.hasItem("Hammer")) {
                    debug("Could not find hammer in bank.");
                    Microbot.showMessage("Could not find hammer in bank.");
                    shutdown();
                }
                if(this.isRunning()) sleep(180, 540);

            }

            if (this.isRunning()) Rs2Bank.withdrawAll(barType.toString());
            if (this.isRunning()) sleepUntil(() -> Rs2Inventory.hasItem(barType.toString()), 3500);

            // Exit if we did not end up finding it.
            if (!Rs2Inventory.hasItem(barType.toString())) {
                debug("Could not find bars in bank.");
                Microbot.showMessage("Could not find bars in bank.");
                shutdown();
            }
            if (this.isRunning()) sleep(180, 540);
            if (this.isRunning()) Rs2Bank.closeBank();
        }
    }
    private void useStaminaPotions(){
        System.out.println("stamina timer : " + staminaTimer);
        if(Microbot.getClient().getEnergy() < 6400) {
            if (this.isRunning()) { Rs2Bank.withdrawOne("Energy potion"); }
            if (this.isRunning()) { sleepUntil(() -> Rs2Inventory.hasItem("Energy potion")); }
            String energyPotion = Rs2Inventory.get("Energy potion").getName();
            if (this.isRunning()) { sleep(61, 97); }
            if (this.isRunning()) { Rs2Inventory.interact("Energy potion", "drink"); }
            if (this.isRunning()) { sleepUntil(() -> !Objects.equals(energyPotion, Rs2Inventory.get("Energy potion").getName()), 97000); }
            if (this.isRunning()) { sleep(161, 197); }
            if (this.isRunning() && Rs2Inventory.hasItem("Energy potion")) {
                if(this.isRunning()) { Rs2Bank.depositOne("Energy potion"); }
                if(this.isRunning()) { sleepUntil(() -> !Rs2Inventory.hasItem("Energy potion"), 97000); }
            }
        }
        if (Microbot.getClient().getEnergy() < 7500) {
            if (this.isRunning()) { Rs2Bank.withdrawOne("Stamina potion"); }
            if (this.isRunning()) {  sleepUntil(() -> Rs2Inventory.hasItem("Stamina potion")); }
            String staminaPotion = Rs2Inventory.get("Stamina potion").getName();
            if (this.isRunning()) {  sleep(61, 97); }
            if (this.isRunning()) { Rs2Inventory.interact("Stamina potion", "drink"); }
            if (this.isRunning()) {  sleepUntil(() -> staminaTimer > 1 && !Objects.equals(staminaPotion, Rs2Inventory.get("Stamina potion").getName()), 97000); }
            if (this.isRunning()) {  sleep(161, 197); }
            if (this.isRunning() && (Rs2Inventory.hasItem("Stamina potion") || Rs2Inventory.hasItem(229))) {
                if (this.isRunning() && Rs2Inventory.hasItem("Stamina potion")) {
                    if(this.isRunning()) { Rs2Bank.depositOne("Stamina potion"); }
                    if(this.isRunning()) { if(this.isRunning()) sleepUntil(() -> !Rs2Inventory.hasItem("Stamina"), 97000); }
                } else {
                    if(this.isRunning()) { Rs2Bank.depositOne(229); }
                    if(this.isRunning()) { if(this.isRunning()) sleepUntil(() -> !Rs2Inventory.hasItem(229)); } }
            }
        }
        if (this.isRunning()) {  sleep(161, 197); }
    }
    private void debug(String msg) {
        debug = msg;
        System.out.println(msg);
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }
}
