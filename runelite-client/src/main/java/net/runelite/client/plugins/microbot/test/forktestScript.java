package net.runelite.client.plugins.microbot.test;

import net.runelite.api.widgets.Widget;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;
import net.runelite.client.plugins.microbot.util.trade.Rs2Trade;

import java.util.concurrent.TimeUnit;


public class forktestScript extends Script {/*

    public static boolean test = false;
    public boolean run(forktestConfig config) {
        Microbot.enableAutoRunOn = false;
        mainScheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                if (!Microbot.isLoggedIn()) return;
                if (!super.run()) return;
                long startTime = System.currentTimeMillis();

                test = false;

                // System.out.println("Trade open: " + Rs2Trade.isOpen());
                // System.out.println("1st Trade open: " + Rs2Trade.isOpen(Rs2Trade.Screen.FIRST));
                // System.out.println("2nd Trade open: " + Rs2Trade.isOpen(Rs2Trade.Screen.SECOND));
                // System.out.println("Waiting for me: " + Rs2Trade.isWaitingForMe());

                // Compile list of roots index:
                System.out.println("Getting roots");
                StringBuilder builder = new StringBuilder();
                for (Widget root : Microbot.getClient().getWidgetRoots()) {
                    if (root == null) {
                        System.out.println("Root: null");
                        continue;
                    }
                    System.out.println("Root: " + root.getId());
                }
                System.out.println("Roots: " + builder.toString());

                 System.out.println("hasAccepted me: " + Rs2Trade.hasAccepted(Rs2Trade.Party.ME));
                // System.out.println("hasAccepted them: " + Rs2Trade.hasAccepted(Rs2Trade.Party.THEM));

//                System.out.println("Testing widget");
//                Widget widget = Rs2Widget.getWidget(162, 53); // Rs2Widget.getWidget(162, 53);
//                if (widget != null) {
//                    System.out.println("Widget: " + widget.getText());
//                } else {
//                    System.out.println("Widget: null");
//                }

                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("Total time for loop " + totalTime);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
        return true;
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }*/
}
