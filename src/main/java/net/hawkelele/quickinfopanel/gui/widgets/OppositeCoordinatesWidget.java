package net.hawkelele.quickinfopanel.gui.widgets;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.providers.client.PlayerDimensionProvider;
import net.hawkelele.quickinfopanel.providers.client.PlayerPositionProvider;
import net.hawkelele.quickinfopanel.services.Coordinates;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.Map;

import static java.util.Map.entry;

public class OppositeCoordinatesWidget extends Layout {
    private static final Map<String, ChatFormatting> colors = Map.ofEntries(
            entry("minecraft:overworld", ChatFormatting.GREEN),
            entry("minecraft:the_nether", ChatFormatting.GOLD)
    );

    public OppositeCoordinatesWidget() {
        Coordinates coordinates = new Coordinates(new PlayerPositionProvider(), new PlayerDimensionProvider());

        if (!coordinates.hasOppositeDimension()) {
            return;
        }

        this.children(
                new Text(
                        Component.literal(coordinates.getDimensionIcon(coordinates.getOppositeDimension()))
                                .withStyle(colors.getOrDefault(coordinates.getOppositeDimension(), ChatFormatting.WHITE))
                ),
                new Text(
                        Component.literal(Coordinates.toShortString(coordinates.getOppositeDimensionPosition()))
                                .withStyle(ChatFormatting.GRAY)
                )
        ).gap(2);
    }
}
