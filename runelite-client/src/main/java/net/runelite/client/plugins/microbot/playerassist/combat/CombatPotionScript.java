package net.runelite.client.plugins.microbot.playerassist.combat;

import net.runelite.api.Skill;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;
import net.runelite.client.plugins.microbot.playerassist.PlayerAssistConfig;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Item;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CombatPotionScript extends Script {

    public boolean run(PlayerAssistConfig config) {
        mainScheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                if (!Microbot.isLoggedIn()) return;
                if (!super.run()) return;
                if (!config.toggleCombatPotion() && !config.toggleRangingPotion()) return;
                if (Microbot.getClient().getBoostedSkillLevel(Skill.ATTACK) - Microbot.getClient().getRealSkillLevel(Skill.ATTACK) > 5) return;
                if (Microbot.getClient().getBoostedSkillLevel(Skill.RANGED) - Microbot.getClient().getRealSkillLevel(Skill.RANGED) > 5) return;
                List<Rs2Item> rs2Items = Rs2Inventory.getPotions();
                for (Rs2Item rs2Item: rs2Items
                ) {
                    if (rs2Item.name.toLowerCase().contains("combat") || rs2Item.name.toLowerCase().contains("ranging") || rs2Item.name.toLowerCase().contains("bastion")) {
                        Rs2Inventory.interact(rs2Item, "drink");
                        sleep(1800, 2400);
                        Rs2Inventory.dropAll("Vial");
                        break;
                    }
                }
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
        }, 0, 600, TimeUnit.MILLISECONDS);
        return true;
    }

    public void shutdown() {
        super.shutdown();
    }

}
