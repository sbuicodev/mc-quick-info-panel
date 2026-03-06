package net.hawkelele.quickinfopanel.panels.components;

import net.hawkelele.quickinfopanel.gui.elements.Panel;
import net.hawkelele.quickinfopanel.gui.elements.Text;
import net.hawkelele.quickinfopanel.services.Coordinates;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.Map;
import static java.util.Map.entry;

public class OppositeCoordinatesPanel extends Panel {
    private static final Map<String, ChatFormatting> colors = Map.ofEntries(
            entry("minecraft:overworld", ChatFormatting.GREEN),
            entry("minecraft:the_nether", ChatFormatting.GOLD)
    );

    public OppositeCoordinatesPanel() {
        this.children(
                new Text(Component.literal(Coordinates.getOppositeDimensionIcon()).withStyle(colors.getOrDefault(Coordinates.getOppositeDimensionId(), ChatFormatting.WHITE))),
                new Text(Component.literal(Coordinates.opposite().toString()).withStyle(ChatFormatting.GRAY))
        ).gap(2);
    }
}
