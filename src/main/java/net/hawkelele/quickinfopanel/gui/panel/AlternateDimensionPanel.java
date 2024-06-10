package net.hawkelele.quickinfopanel.gui.panel;

import net.hawkelele.quickinfopanel.gui.coordinates.AlternateDimensionCoordinates;
import net.hawkelele.quickinfopanel.gui.coordinates.Coordinates;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class AlternateDimensionPanel extends Panel {
    public Text getAlternateDimensionIcon() {
        assert client.world != null;
        String dimension = client.world.getDimensionEntry().getIdAsString();
        return switch (dimension) {
            case "minecraft:the_nether" -> Text.literal("\uD83C\uDF33").formatted(Formatting.DARK_GREEN);
            case "minecraft:the_end" -> Text.empty();
            default -> Text.literal("\uD83D\uDD25").formatted(Formatting.GOLD);
        };
    }

    @Override
    public int[] getTextPosition(Text text) {
        int[] position = super.getTextPosition(text);
        int lineHeight = 10;

        if (!config.get().position.invertLines) {
            lineHeight = -lineHeight;
        }

        if (config.get().position.centered) {
            position[0] -= 10;
        }

        position[1] += lineHeight;

        return position;

    }

    @Override
    public @NotNull Text getPanelText() {
        assert client.player != null;
        assert client.world != null;
        Coordinates coordinates = AlternateDimensionCoordinates.get();

        return Text.empty()
                   .append(getAlternateDimensionIcon())
                   .append(" ")
                   .append(Text.literal("XZ: ").formatted(Formatting.YELLOW))
                   .append(Text.literal(String.valueOf(coordinates.x)).withColor(Color.decode("#888888").hashCode()))
                   .append(" ")
                   .append(Text.literal(String.valueOf(coordinates.z)).withColor(Color.decode("#888888").hashCode()));
    }

    @Override
    public boolean shouldBeHidden() {
        return super.shouldBeHidden()
                || client.world == null
                || !config.get().showNetherCoordinates
                || client.world.getDimensionEntry().getIdAsString().equals("the_end");
    }
}
