package net.runelite.client.plugins.microbot.runecrafting.ourania;

import net.runelite.api.*;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;
import net.runelite.client.plugins.microbot.globval.enums.InterfaceTab;
import net.runelite.client.plugins.microbot.qualityoflife.scripts.pouch.Pouch;
import net.runelite.client.plugins.microbot.runecrafting.ourania.enums.OuraniaState;
import net.runelite.client.plugins.microbot.runecrafting.ourania.enums.Path;
import net.runelite.client.plugins.microbot.util.Rs2InventorySetup;
import net.runelite.client.plugins.microbot.util.antiban.Rs2Antiban;
import net.runelite.client.plugins.microbot.util.antiban.Rs2AntibanSettings;
import net.runelite.client.plugins.microbot.util.antiban.enums.Activity;
import net.runelite.client.plugins.microbot.util.bank.Rs2Bank;
import net.runelite.client.plugins.microbot.util.equipment.Rs2Equipment;
import net.runelite.client.plugins.microbot.util.gameobject.Rs2GameObject;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Item;
import net.runelite.client.plugins.microbot.util.inventory.RunePouchType;
import net.runelite.client.plugins.microbot.util.magic.Rs2Magic;
import net.runelite.client.plugins.microbot.util.magic.Rs2Spells;
import net.runelite.client.plugins.microbot.util.magic.Rs2Staff;
import net.runelite.client.plugins.microbot.util.magic.Runes;
import net.runelite.client.plugins.microbot.util.math.Rs2Random;
import net.runelite.client.plugins.microbot.util.misc.Rs2Potion;
import net.runelite.client.plugins.microbot.util.npc.Rs2Npc;
import net.runelite.client.plugins.microbot.util.player.Rs2Player;
import net.runelite.client.plugins.microbot.util.tabs.Rs2Tab;
import net.runelite.client.plugins.microbot.util.walker.Rs2Walker;
import net.runelite.client.plugins.skillcalculator.skills.MagicAction;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static net.runelite.client.plugins.microbot.util.Global.sleepUntilTrue;

public class OuraniaScript extends Script {
    
    private final WorldArea ouraniaAltarArea = new WorldArea(new WorldPoint(3054, 5574, 0), 12, 12);
    private final OuraniaPlugin plugin;
    public static OuraniaState state;
    int staffID;
    Rs2InventorySetup rs2InventorySetup;
    
    @Inject
    public OuraniaScript(OuraniaPlugin plugin) {
        this.plugin = plugin;
    }
    
    public boolean run() {
        Microbot.enableAutoRunOn = false;
        Rs2Antiban.resetAntibanSettings();
        Rs2Antiban.antibanSetupTemplates.applyRunecraftingSetup();
        Rs2Antiban.setActivity(Activity.CRAFTING_RUNES_AT_OURANIA_ALTAR);
        staffID = Rs2Equipment.get(EquipmentInventorySlot.WEAPON).getId();
        rs2InventorySetup = new Rs2InventorySetup("ourania", mainScheduledFuture);
        mainScheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                if (!Microbot.isLoggedIn()) return;
                if (!super.run()) return;
                long startTime = System.currentTimeMillis();
                
                if (!Rs2Magic.isLunar()) {
                    Microbot.showMessage("Not currently on Lunar Spellbook");
                    shutdown();
                    return;
                }
                
                if (Rs2Inventory.anyPouchUnknown()) {
                    Rs2Inventory.checkPouches();
                    return;
                }

                if (hasStateChanged()) {
                    state = updateState();
                }

                if (state == null) {
                    Microbot.showMessage("Unable to evaluate state");
                    shutdown();
                    return;
                }
                int i = 0;
                System.out.println("V New loop!");
                while (this.isRunning() && i<101) {
                    if (i % 20 == 0) {
                        System.out.println("#");
                    }
                    if (i<100) System.out.print("@@@@@@@@@@");
                    ++i;
                }
                System.out.println("A New loop!");
                System.out.println("State: " + state.name());

                switch (state) {
                    case CRAFTING:
                        if(Rs2Inventory.hasItem(plugin.getEssence().getItemId())) {
                            Rs2GameObject.interact(ObjectID.ALTAR_29631, "craft-rune");
                            Rs2Player.waitForXpDrop(Skill.RUNECRAFT, false);
                        }
                        if (Rs2Inventory.hasAnyPouch() && !Rs2Inventory.allPouchesEmpty()) {
                            Rs2Inventory.emptyPouches();
                            Rs2Inventory.waitForInventoryChanges(1200);
                        }
                        if (Rs2Inventory.hasItem(plugin.getEssence().getItemId())) {
                            Rs2GameObject.interact(ObjectID.ALTAR_29631, "craft-rune");
                            Rs2Player.waitForXpDrop(Skill.RUNECRAFT, false);
                        }
                        break;
                    case RESETTING:
                        if (Rs2Magic.getRequiredRunes(Rs2Spells.NPC_CONTACT, Rs2Magic.getRs2Staff(staffID), 1, true).isEmpty()) {
                            repairPouch("done crafting runes");
                        }
                        if (Rs2Player.getWorldLocation().distanceTo(new WorldPoint(2468, 3246, 0)) > 24) {
                            Rs2Magic.cast(MagicAction.OURANIA_TELEPORT);
                        }
                        sleepUntil(() -> Rs2Player.getWorldLocation().distanceTo(new WorldPoint(2468, 3246, 0)) < 24);
                        //TODO change this to something not as slow
                        if (!Rs2GameObject.interact(29635)) {
                            Rs2Walker.walkFastCanvas(new WorldPoint(2455, 3242, 0));
                            sleepUntilTrue(() -> Rs2GameObject.interact(29635), 600, 6000);
                            //Rs2Walker.walkTo(new WorldPoint(3014, 5625, 0));
                        }
                        if (Rs2Tab.getCurrentTab() != InterfaceTab.INVENTORY) {
                            Rs2Tab.switchToInventoryTab();
                        }
                        sleepUntilTrue(() -> Rs2Npc.getNpc(NpcID.ENIOLA) != null, 100,8000);
                        break;
                    case BANKING:
                        if (plugin.isRanOutOfAutoPay()) {
                            Microbot.showMessage("You have ran out of auto-pay runes, check runepouch!");
                            shutdown();
                            return;
                        }

                        repairPouch("Banking");

                        NPC eniola = Rs2Npc.getNpc(NpcID.ENIOLA);
                        if (eniola == null) return;
                        if (!Rs2Bank.isOpen()) {
                            boolean isBankOpen = Rs2Npc.interact(eniola, "bank");
                            sleepUntilTrue(Rs2Bank::isOpen, 60, 6000);
                            if (!isBankOpen || !Rs2Bank.isOpen()) return;
                        }
                        
                        //plugin.calcuateProfit();
                        /*
                        // Get all RunePouchType IDs
                        Integer[] runePouchIds = Arrays.stream(RunePouchType.values())
                                .map(RunePouchType::getItemId)
                                .toArray(Integer[]::new);

                        // Get all eligible pouch IDs based on Runecrafting level
                        Integer[] eligiblePouchIds = Arrays.stream(Pouch.values())
                                .filter(Pouch::hasRequiredRunecraftingLevel)
                                .flatMap(pouch -> Arrays.stream(pouch.getItemIds()).boxed())
                                .toArray(Integer[]::new);

                        // Combine RunePouchType IDs and eligible pouch IDs into a single array
                        Integer[] excludedIds = Stream.concat(Arrays.stream(runePouchIds), Arrays.stream(eligiblePouchIds))
                                .toArray(Integer[]::new);

                        //TODO
                        System.out.println("inventory compare : "+((28 - rs2InventorySetup.getInventoryItems().size()) - (28 - Rs2Inventory.size()))+" - "+rs2InventorySetup.getInventoryItems().size());
                        System.out.println("Rs2Inventory.emptySlotCount() : "+Rs2Inventory.emptySlotCount());
                        if (((28 - rs2InventorySetup.getInventoryItems().size()) - (28 - Rs2Inventory.size())) > rs2InventorySetup.getInventoryItems().size()) { Rs2Bank.depositAll(); } else {
                            Rs2Bank.depositAllExcept(excludedIds);
                        }*/
                        if (Rs2Inventory.size()>rs2InventorySetup.getInventoryItems().size()) Rs2Bank.depositAll();
                        //Rs2Inventory.waitForInventoryChanges(1800);
                        if (Rs2Inventory.isEmpty()) {
                            withdrawPouches("banking");
                        }

                        if (plugin.isUseEnergyRestorePotions() && Rs2Player.getRunEnergy() <= plugin.getDrinkAtPercent()) {
                            boolean hasStaminaPotion = Rs2Bank.hasItem(Rs2Potion.getStaminaPotion());
                            boolean hasEnergyRestorePotion = Rs2Bank.hasItem(Rs2Potion.getRestoreEnergyPotionsVariants());

                            if ((Rs2Player.hasStaminaBuffActive() && hasEnergyRestorePotion) || (!hasStaminaPotion && hasEnergyRestorePotion)) {
                                Rs2Item energyRestoreItem = Rs2Bank.bankItems().stream()
                                        .filter(rs2Item -> Rs2Potion.getRestoreEnergyPotionsVariants().stream()
                                                .anyMatch(variant -> rs2Item.getName().toLowerCase().contains(variant.toLowerCase())))
                                        .findFirst()
                                        .orElse(null);

                                if (energyRestoreItem == null) {
                                    Microbot.showMessage("Unable to find Restore Energy Potion but hasItem?");
                                    shutdown();
                                    return;
                                }

                                withdrawAndDrink(energyRestoreItem.getName());
                                ensureBankIsOpen("drinking energy restore");
                                if (Rs2Inventory.hasItem(energyRestoreItem.getName())) Rs2Bank.depositAll(energyRestoreItem.getName());
                            } else if (hasStaminaPotion) {
                                Rs2Item staminaPotionItem = Rs2Bank.bankItems().stream()
                                        .filter(rs2Item -> rs2Item.getName().toLowerCase().contains(Rs2Potion.getStaminaPotion().toLowerCase()))
                                        .findFirst()
                                        .orElse(null);

                                if (staminaPotionItem == null) {
                                    Microbot.showMessage("Unable to find Stamina Potion but hasItem?");
                                    shutdown();
                                    return;
                                }

                                withdrawAndDrink(staminaPotionItem.getName());
                                ensureBankIsOpen("drinking stamina potion");
                                if (Rs2Inventory.hasItem(staminaPotionItem.getName())) Rs2Bank.depositAll(staminaPotionItem.getName());
                            } else {
                                Microbot.showMessage("Unable to find Stamina Potion OR Energy Restore Potions");
                                shutdown();
                                return;
                            }
                        }

                        if (Rs2Player.getHealthPercentage() <= plugin.getEatAtPercent()) {
                            while (Rs2Player.getHealthPercentage() < 100 && ensureBankIsOpen("getting food") && this.isRunning() && isRunning()) {
                                if (!Rs2Bank.hasItem(plugin.getRs2Food().getId())) {
                                    Microbot.showMessage("Missing Food in Bank!");
                                    shutdown();
                                    break;
                                }

                                Rs2Bank.withdrawOne(plugin.getRs2Food().getId());
                                sleepUntilTrue(() -> Rs2Inventory.hasItem(plugin.getRs2Food().getId()), 60, 600);
                                Rs2Player.useFood();
                                sleepUntilTrue(() -> !Rs2Inventory.hasItem(plugin.getRs2Food().getId()), 60, 600);

                                if (Rs2Inventory.hasItem(ItemID.JUG)) {
                                    Rs2Bank.depositAll(ItemID.JUG);
                                    Rs2Inventory.waitForInventoryChanges(1800);
                                }
                                if (Rs2Inventory.hasItem(plugin.getRs2Food().getId())) {
                                    Rs2Bank.depositAll(plugin.getRs2Food().getId());
                                    Rs2Inventory.waitForInventoryChanges(1800);
                                }
                            }
                            if (Rs2Inventory.hasItem(ItemID.JUG)) {
                                Rs2Bank.depositAll(ItemID.JUG);
                                Rs2Inventory.waitForInventoryChanges(1800);
                            }
                            if (Rs2Inventory.hasItem(plugin.getRs2Food().getId())) {
                                Rs2Bank.depositAll(plugin.getRs2Food().getId());
                                Rs2Inventory.waitForInventoryChanges(1800);
                            }

                        }
                        
                        int requiredEssence = Rs2Inventory.getEmptySlots() + Rs2Inventory.getRemainingCapacityInPouches();
                        
                        if (!Rs2Bank.hasBankItem(plugin.getEssence().getItemId(), requiredEssence)) {
                            Microbot.showMessage("Not enough essence to full run");
                            shutdown();
                            return;
                        }
                        
                        if (Rs2Inventory.hasAnyPouch()) {
                            while (!Rs2Inventory.allPouchesFull() && ensureBankIsOpen("something with fill pouches?") && Rs2Bank.isOpen() && isRunning() && this.isRunning()) {
                                if (Rs2Inventory.hasItem(plugin.getRs2Food().getId())) {
                                    Rs2Bank.depositAll(plugin.getRs2Food().getId());
                                    Rs2Inventory.waitForInventoryChanges(200);
                                }
                                Rs2Bank.withdrawAll(plugin.getEssence().getItemId());
                                Rs2Inventory.waitForInventoryChanges(300);
                                Rs2Inventory.fillPouches();
                                Rs2Inventory.waitForInventoryChanges(300);
                                if (Rs2Inventory.hasDegradedPouch()){
                                    if (Rs2Inventory.hasItem(plugin.getEssence().getItemId())) Rs2Bank.depositAll(plugin.getEssence().getItemId());
                                    Rs2Inventory.waitForInventoryChanges(300);
                                    repairPouch("filling pouch");
                                    return;
                                }
                            }
                        }
                        
                        Rs2Bank.withdrawAll(plugin.getEssence().getItemId());
                        Rs2Inventory.waitForInventoryChanges(1200);
                        if (hasRequiredItems()) {
                            Rs2Bank.closeBank();
                            sleepUntil(() -> !Rs2Bank.isOpen());
                        }
                        break;
                    case RUNNING_TO_ALTAR:
                        Rs2Walker.walkTo(plugin.getPath().getWorldPoint());
                        if (plugin.getPath().equals(Path.LONG)) {
                            Rs2GameObject.interact(ObjectID.CRACK_29626, "squeeze-through");
                            sleepUntil(this::isNearAltar, 10000);
                        }
                        break;
                }

                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("Total time for loop " + totalTime);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                Microbot.log("Error in Ourania Altar Script: " + ex.getMessage());
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
        return true;
    }
    private boolean ensureBankIsOpen(String debug){
        if (Rs2Bank.isOpen()) { return true; } else {
            System.out.println(debug);
            Rs2Npc.interact(NpcID.ENIOLA, "bank");
            sleepUntilTrue(Rs2Bank::isOpen,100,5000);
            return true;
        }

    }
    private void repairPouch(String debug){
        if (Rs2Inventory.hasDegradedPouch()) {
            if (Rs2Bank.isOpen()) {
                if (Rs2Inventory.isFull()) {
                    Rs2Bank.depositAll();
                    Rs2Inventory.waitForInventoryChanges(200);
                    withdrawPouches("repairing pouch");
                } else {
                    withdrawPouches("repairing pouch");
                }
                while (this.isRunning() && !Rs2Magic.getRequiredRunes(Rs2Spells.NPC_CONTACT, Rs2Magic.getRs2Staff(staffID), 1, true).isEmpty()) {
                    Map<Runes, Integer> requiredRunes = Rs2Magic.getRequiredRunes(Rs2Spells.NPC_CONTACT, Rs2Magic.getRs2Staff(staffID), 1, true);
                    for (Map.Entry<Runes, Integer> entry : requiredRunes.entrySet()) {
                        Runes rune = entry.getKey();
                        System.out.println("We should be grabbing our runes!");
                        if (Rs2Bank.hasItem(rune.getItemId())) {
                            System.out.println("bank has our runes, should be grabbing them.");
                            Rs2Bank.withdrawAll(rune.getItemId());
                            Rs2Inventory.waitForInventoryChanges(200);
                        } else {
                            System.out.println("Bank does not have our rune : "+rune.getItemId());
                        }
                    }
                }
                Rs2Bank.closeBank();
                sleepUntilTrue(() -> !Rs2Bank.isOpen(), 100, 2000);
                Rs2Magic.repairPouchesWithLunar();
                if (!Rs2Inventory.hasDegradedPouch()) {
                    if (Rs2Tab.getCurrentTab() != InterfaceTab.INVENTORY) {
                        Rs2Tab.switchToInventoryTab();
                    }
                    Rs2Inventory.checkPouches();
                    ensureBankIsOpen(debug + " repair pouches");
                    Rs2Bank.depositAll();
                    Rs2Inventory.waitForInventoryChanges(300);
                    withdrawPouches("aaaaaaaa");
                }
            } else {
                if (Rs2Inventory.hasDegradedPouch() && Rs2Magic.getRequiredRunes(Rs2Spells.NPC_CONTACT, Rs2Magic.getRs2Staff(staffID), 1, true).isEmpty()) {
                    while (this.isRunning() && Rs2Inventory.hasDegradedPouch() && Rs2Magic.getRequiredRunes(Rs2Spells.NPC_CONTACT, Rs2Magic.getRs2Staff(staffID), 1, true).isEmpty()) {
                        Rs2Magic.repairPouchesWithLunar();
                        sleep(100);
                    }
                }
            }
        }
    }
    private void withdrawPouches(String debug){
        System.out.println(debug + ": getting our pouch back from the bank");
        while (this.isRunning() && Rs2Inventory.size() < rs2InventorySetup.getInventoryItems().size()) {
            if (Rs2Inventory.size() < rs2InventorySetup.getInventoryItems().size()) {
                while (this.isRunning() && Rs2Inventory.size() < rs2InventorySetup.getInventoryItems().size()) {
                    if (rs2InventorySetup != null) {
                        rs2InventorySetup.getInventoryItems().forEach(item -> System.out.println("Item ID: " + item.getId()));
                        rs2InventorySetup.loadInventory();
                        if (Rs2Inventory.size() < rs2InventorySetup.getInventoryItems().size() && rs2InventorySetup.getInventoryItems().stream().anyMatch(x -> x.getId() == ItemID.COLOSSAL_POUCH) && Rs2Bank.hasItem(ItemID.COLOSSAL_POUCH_26906)) {
                            Rs2Bank.withdrawAll(ItemID.COLOSSAL_POUCH_26906);
                        }
                        if (Rs2Inventory.size() < rs2InventorySetup.getInventoryItems().size() && rs2InventorySetup.getInventoryItems().stream().anyMatch(x -> x.getId() == ItemID.COLOSSAL_POUCH) && Rs2Bank.hasItem(ItemID.COLOSSAL_POUCH_26786)) {
                            System.out.println("Should be withdrawing our decayed rune pouch");
                            Rs2Bank.withdrawAll(ItemID.COLOSSAL_POUCH_26786);
                        }
                        if (Rs2Inventory.size() < rs2InventorySetup.getInventoryItems().size() && rs2InventorySetup.getInventoryItems().stream().anyMatch(x -> x.getId() == ItemID.GIANT_POUCH) && Rs2Bank.hasItem(ItemID.GIANT_POUCH_5515)) {
                            System.out.println("Should be withdrawing our decayed rune pouch");
                            Rs2Bank.withdrawAll(ItemID.GIANT_POUCH_5515);
                        }
                        if (Rs2Inventory.size() < rs2InventorySetup.getInventoryItems().size() && rs2InventorySetup.getInventoryItems().stream().anyMatch(x -> x.getId() == ItemID.LARGE_POUCH) && Rs2Bank.hasItem(ItemID.LARGE_POUCH_5513)) {
                            System.out.println("Should be withdrawing our decayed rune pouch");
                            Rs2Bank.withdrawAll(ItemID.LARGE_POUCH_5513);
                        }
                        if (Rs2Inventory.size() < rs2InventorySetup.getInventoryItems().size() && rs2InventorySetup.getInventoryItems().stream().anyMatch(x -> x.getId() == ItemID.LARGE_POUCH) && Rs2Bank.hasItem(ItemID.LARGE_POUCH_6819)) {
                            System.out.println("Should be withdrawing our decayed rune pouch");
                            Rs2Bank.withdrawAll(ItemID.LARGE_POUCH_6819);
                        }
                        if (Rs2Inventory.size() < rs2InventorySetup.getInventoryItems().size() && rs2InventorySetup.getInventoryItems().stream().anyMatch(x -> x.getId() == ItemID.MEDIUM_POUCH) && Rs2Bank.hasItem(ItemID.MEDIUM_POUCH_5511)) {
                            System.out.println("Should be withdrawing our decayed rune pouch");
                            Rs2Bank.withdrawAll(ItemID.MEDIUM_POUCH_5511);
                        }
                        if (Rs2Inventory.size() < rs2InventorySetup.getInventoryItems().size()) Rs2Inventory.waitForInventoryChanges(200);
                    }
                }
                repairPouch("ajshngfjklash");
            }
        }
    }
    @Override
    public void shutdown() {
        Rs2Antiban.resetAntibanSettings();
        super.shutdown();
    }
    
    private boolean hasStateChanged() {
        if (state == null) return true;
        if (hasRequiredItems() && !isNearAltar()) return true;
        if (hasRequiredItems() && isNearAltar()) return true;
        if ((!hasRequiredItems() && isNearAltar()) || (!hasRequiredItems() && !isNearEniola())) return true;
        if (!hasRequiredItems() && isNearEniola()) return true;
        return false;
    }
    
    private OuraniaState updateState() {
        System.out.println("Changing states!");
        if (hasRequiredItems() && !isNearAltar()) return OuraniaState.RUNNING_TO_ALTAR;
        if (hasRequiredItems() && isNearAltar()) return OuraniaState.CRAFTING;
        if ((!hasRequiredItems() && isNearAltar()) || (!hasRequiredItems() && !isNearEniola()))  return OuraniaState.RESETTING;
        if (!hasRequiredItems() && isNearEniola()) return OuraniaState.BANKING;
        return null;
    }

    private boolean hasRequiredItems() {
        System.out.println("Checking our inventory");
        if (Rs2Inventory.hasAnyPouch()) {
            if (isNearAltar() && (Rs2Inventory.hasItem(plugin.getEssence().getItemId()) || !Rs2Inventory.allPouchesEmpty()))  return true;
            boolean pouchesContainEssence = !Rs2Inventory.allPouchesEmpty();
            boolean inventoryContainsEssence = Rs2Inventory.hasItem(plugin.getEssence().getItemId());
            return pouchesContainEssence && inventoryContainsEssence && Rs2Inventory.isFull();
        } else {
            return Rs2Inventory.hasItem(plugin.getEssence().getItemId());
        }
    }
    
    private boolean isNearAltar() {
        return ouraniaAltarArea.contains(Rs2Player.getWorldLocation());
    }
    
    private boolean isNearEniola() {
        NPC eniola = Rs2Npc.getNpc(NpcID.ENIOLA);
        if (eniola == null) return false;
        return Rs2Player.getWorldLocation().distanceTo2D(eniola.getWorldLocation()) < 12;
    }

    private void withdrawAndDrink(String potionItemName) {
        String simplifiedPotionName = potionItemName.replaceAll("\\s*\\(\\d+\\)", "").trim();
        Rs2Bank.withdrawOne(simplifiedPotionName);
        Rs2Inventory.waitForInventoryChanges(1800);
        Rs2Inventory.interact(simplifiedPotionName, "drink");
        Rs2Inventory.waitForInventoryChanges(1800);
        if (Rs2Inventory.hasItem(simplifiedPotionName)) {
            Rs2Bank.depositOne(simplifiedPotionName);
            Rs2Inventory.waitForInventoryChanges(1800);
        }
    }
}
