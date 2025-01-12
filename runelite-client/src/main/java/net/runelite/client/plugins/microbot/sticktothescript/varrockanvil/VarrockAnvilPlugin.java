package net.runelite.client.plugins.microbot.sticktothescript.varrockanvil;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Varbits;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;

@PluginDescriptor(
        name = PluginDescriptor.StickToTheScript + "Varrock Anvil",
        description = "StickToTheScript\'s Varrock Anvil plugin",
        tags = {"StickToTheScript", "STTS", "varrock", "anvil", "smithing"},
        enabledByDefault = false
)
@Slf4j
public class VarrockAnvilPlugin extends Plugin {
    @Inject
    private VarrockAnvilConfig config;
    @Inject
    private OverlayManager overlayManager;
    @Inject
    private VarrockAnvilOverlay overlay;

    @Inject
    VarrockAnvilScript script;

    @Provides
    VarrockAnvilConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(VarrockAnvilConfig.class);
    }

    @Override
    protected void startUp() throws AWTException {
        if (overlayManager != null) {
            overlayManager.add(overlay);
        }

        script.run(config);
    }
    @Subscribe
    public void onVarbitChanged(VarbitChanged event) {
        if (event.getVarbitId() == Varbits.STAMINA_EFFECT) {
            VarrockAnvilScript.staminaTimer = event.getValue();
        }
    }
    protected void shutDown() {
        script.shutdown();
        overlayManager.remove(overlay);
    }
}
