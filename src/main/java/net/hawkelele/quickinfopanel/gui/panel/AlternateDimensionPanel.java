package net.hawkelele.quickinfopanel.gui.panel;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.coordinates.AlternateDimensionCoordinates;
import net.hawkelele.quickinfopanel.gui.coordinates.Coordinates;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class AlternateDimensionPanel extends Panel {
    private static AlternateDimensionPanel instance;

    public static AlternateDimensionPanel getInstance() {
        return instance == null ? instance = new AlternateDimensionPanel() : instance;
    }

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

        if (!Config.getInstance().settings().position.invertLines) {
            lineHeight = -lineHeight;
        }

        if (Config.getInstance().settings().position.centered) {
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
                   .append(Text.literal(String.valueOf(coordinates.x)).withColor(Color.decode("#AAAAAA").hashCode()))
                   .append(" ")
                   .append(Text.literal(String.valueOf(coordinates.z)).withColor(Color.decode("#AAAAAA").hashCode()));
    }

    @Override
    public boolean shouldBeHidden() {
        return super.shouldBeHidden()
                || client.world == null
                || !Config.getInstance().settings().displayAlternateDimensionInfo
                || client.world.getDimensionEntry().getIdAsString().equals("the_end");
    }
}
