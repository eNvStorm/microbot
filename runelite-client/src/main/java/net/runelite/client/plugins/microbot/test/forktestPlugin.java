package net.runelite.client.plugins.microbot.test;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;

@PluginDescriptor(
        name = "Fork test",
        description = "Fork test plugin",
        tags = {"example", "fork"},
        enabledByDefault = false
)
@Slf4j
public class forktestPlugin extends Plugin {/*
    @Inject
    private forktestConfig config;
    @Provides
    forktestConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(forktestConfig.class);
    }

    @Inject
    private OverlayManager overlayManager;
    @Inject
    private forktestOverlay forktestOverlay;

    @Inject
    forktestScript forktestScript;


    @Override
    protected void startUp() throws AWTException {
        if (overlayManager != null) {
            overlayManager.add(forktestOverlay);
        }
        forktestScript.run(config);
    }

    protected void shutDown() {
        forktestScript.shutdown();
        overlayManager.remove(forktestOverlay);
    }
    int ticks = 10;
    @Subscribe
    public void onGameTick(GameTick tick)
    {
        //System.out.println(getName().chars().mapToObj(i -> (char)(i + 3)).map(String::valueOf).collect(Collectors.joining()));

        if (ticks > 0) {
            ticks--;
        } else {
            ticks = 10;
        }

    }
*/
}
