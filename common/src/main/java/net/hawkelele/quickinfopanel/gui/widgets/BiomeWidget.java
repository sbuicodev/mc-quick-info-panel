package net.hawkelele.quickinfopanel.gui.widgets;

import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.providers.client.LevelBiomeProvider;
import net.hawkelele.quickinfopanel.services.Biome;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public class BiomeWidget extends Layout {
    public BiomeWidget() {
        Biome biome = new Biome(new LevelBiomeProvider());

        this.children(new Text(Component.literal(biome.getBiomeName()).withStyle(ChatFormatting.GRAY)));
    }
}
