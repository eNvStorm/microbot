/*
 * ******************************************************************************
 *  * Copyright (c) 2019 openosrs
 *  *  Redistributions and modifications of this software are permitted as long as this notice remains in its original unmodified state at the top of this file.
 *  *  If there are any questions comments, or feedback about this software, please direct all inquiries directly to the file authors:
 *  *  ST0NEWALL#9112
 *  *   openosrs Discord: https://discord.gg/Q7wFtCe
 *  *   openosrs website: https://openosrs.com
 *  *****************************************************************************
 */

package net.runelite.client.plugins.microbot.pvp;


import net.runelite.api.Client;
import net.runelite.api.Varbits;
import net.runelite.api.WorldType;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.table.TableComponent;
import net.runelite.client.ui.table.TableElement;
import net.runelite.client.ui.table.TableRow;
import org.apache.commons.lang3.ArrayUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;
import java.util.Arrays;

@Singleton
public class PlayerCountOverlay extends Overlay
{
	private static final int[] CLAN_WARS_REGIONS = {9520, 13135, 13134, 13133, 13131, 13130, 13387, 13386};

	private final PvpToolsPlugin plugin;
	private final PvpToolsConfig config;
	private final Client client;

	@Inject
	public PlayerCountOverlay(final PvpToolsPlugin plugin, final PvpToolsConfig config, final Client client)
	{
		this.plugin = plugin;
		this.config = config;
		this.client = client;
		setLayer(OverlayLayer.ABOVE_WIDGETS);
		setPriority(OverlayPriority.HIGHEST);
		setPosition(OverlayPosition.TOP_LEFT);
		setPreferredPosition(OverlayPosition.TOP_LEFT);
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		if (config.countPlayers() &&
			(client.getVarbitValue(Varbits.IN_WILDERNESS) == 1 || WorldType.isPvpWorld(client.getWorldType())
				|| ArrayUtils.contains(CLAN_WARS_REGIONS, client.getMapRegions()[0]) ||
				WorldType.isDeadmanWorld(client.getWorldType())))
		{
			// Make this stop showing up when its not relevant
			TableComponent tableComponent = new TableComponent();
			TableElement[] firstRowElements = {
				TableElement.builder().content("Friendly").color(Color.GREEN).build(),
				TableElement.builder().content(String.valueOf(plugin.getFriendlyPlayerCount())).build()};
			TableRow firstRow = TableRow.builder().elements(Arrays.asList(firstRowElements)).build();
			TableElement[] secondRowElements = {
				TableElement.builder().content("Enemy").color(Color.RED).build(),
				TableElement.builder().content(String.valueOf(plugin.getEnemyPlayerCount())).build()};
			TableRow secondRow = TableRow.builder().elements(Arrays.asList(secondRowElements)).build();
			tableComponent.addRows(firstRow, secondRow);
			return tableComponent.render(graphics);
		}
		return null;
	}
}
