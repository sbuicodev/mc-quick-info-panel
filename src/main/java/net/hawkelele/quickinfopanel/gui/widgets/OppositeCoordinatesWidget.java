package net.hawkelele.quickinfopanel.gui.widgets;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
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
        String oppositeDimension = Coordinates.getOppositeDimensionId();

        if (oppositeDimension == null) {
            return;
        }


        this.children(
                new Text(Component.literal(Coordinates.getOppositeDimensionIcon()).withStyle(colors.getOrDefault(Coordinates.getOppositeDimensionId(), ChatFormatting.WHITE))),
                new Text(Component.literal(Coordinates.opposite().toShortString()).withStyle(ChatFormatting.GRAY))
        ).gap(2);
    }
}
