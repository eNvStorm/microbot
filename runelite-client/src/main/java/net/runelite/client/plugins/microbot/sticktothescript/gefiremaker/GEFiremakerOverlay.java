package net.runelite.client.plugins.microbot.sticktothescript.gefiremaker;

import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;

public class GEFiremakerOverlay extends OverlayPanel {
    private final GEFiremakerConfig config;
    private final GEFiremakerPlugin plugin;

    @Inject
    GEFiremakerOverlay(GEFiremakerPlugin plugin, GEFiremakerConfig config)
    {
        super(plugin);
        this.plugin = plugin;
        setPosition(OverlayPosition.TOP_LEFT);
        this.config = config;
        setNaughty();
    }
    @Override
    public Dimension render(Graphics2D graphics) {
        try {
            panelComponent.setPreferredSize(new Dimension(250, 300));
            panelComponent.getChildren().add(TitleComponent.builder()
                    .text("StickToTheScript\'s GE Firemaker v" + GEFiremakerScript.version)
                    .color(Color.PINK)
                    .build());

            panelComponent.getChildren().add(LineComponent.builder().build());

            panelComponent.getChildren().add(LineComponent.builder()
                    .left("State: " + plugin.script.state.name())
                    .build());

            if (config.sDebug()) {
                panelComponent.getChildren().add(LineComponent.builder()
                        .left("---")
                        .build());
                panelComponent.getChildren().add(LineComponent.builder()
                        .left("Debug: " + plugin.script.debug)
                        .build());
                panelComponent.getChildren().add(LineComponent.builder()
                        .left("Microbot Status: " + Microbot.status)
                        .build());
            }


        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return super.render(graphics);
    }
}
