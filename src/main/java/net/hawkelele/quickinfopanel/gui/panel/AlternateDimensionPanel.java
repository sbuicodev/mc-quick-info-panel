package net.hawkelele.quickinfopanel.gui.panel;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.coordinates.AlternateDimensionCoordinates;
import net.hawkelele.quickinfopanel.gui.coordinates.Coordinates;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

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
    public int[] getTextPosition(int width) {
        int[] position = super.getTextPosition(width);

        int lineHeight = 10;

        if (!Config.getInstance().settings().position.invertLines) {
            lineHeight = -lineHeight;
        }

        if (Config.getInstance().settings().position.centered) {
            position[0] -= 10;
        } else {
            position[0] += 2;
        }

        position[1] += lineHeight;

        return position;

    }

    @Override
    public void draw(DrawContext context) {
        assert client.player != null;
        assert client.world != null;
        Coordinates coordinates = AlternateDimensionCoordinates.get();

        Text text = Text.empty()
                        .append(getAlternateDimensionIcon())
                        .append(" ")
                        .append(Text.literal("XZ: ").formatted(Formatting.YELLOW))
                        .append(Text.literal(String.valueOf(coordinates.x)).withColor(Color.decode("#BBBBBB").hashCode()))
                        .append(" ")
                        .append(Text.literal(String.valueOf(coordinates.z)).withColor(Color.decode("#BBBBBB").hashCode()));

        int[] position = getTextPosition(client.textRenderer.getWidth(text));

        context.drawText(
                client.textRenderer,
                text,
                position[0], position[1],
                Color.decode("#AAAAAA").hashCode(),
                true
        );
    }

    @Override
    public boolean shouldBeHidden() {
        return super.shouldBeHidden()
                || client.world == null
                || !Config.getInstance().settings().displayAlternateDimensionInfo
                || client.world.getDimensionEntry().getIdAsString().equals("the_end");
    }
}
