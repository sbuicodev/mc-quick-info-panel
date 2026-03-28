package net.hawkelele.quickinfopanel.gui.components;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.services.CoordinatesService;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.Map;
import static java.util.Map.entry;

public class OppositeCoordinates extends Layout {
    private static final Map<String, ChatFormatting> colors = Map.ofEntries(
            entry("minecraft:overworld", ChatFormatting.GREEN),
            entry("minecraft:the_nether", ChatFormatting.GOLD)
    );

    public OppositeCoordinates() {
        String oppositeDimension = CoordinatesService.getOppositeDimensionId();

        if (oppositeDimension == null) {
            return;
        }


        this.children(
                new Text(Component.literal(CoordinatesService.getOppositeDimensionIcon()).withStyle(colors.getOrDefault(CoordinatesService.getOppositeDimensionId(), ChatFormatting.WHITE))),
                new Text(Component.literal(CoordinatesService.opposite().toShortString()).withStyle(ChatFormatting.GRAY))
        ).gap(2);
    }
}
