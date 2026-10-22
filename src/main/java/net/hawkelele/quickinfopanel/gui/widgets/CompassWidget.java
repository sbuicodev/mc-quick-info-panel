package net.hawkelele.quickinfopanel.gui.widgets;

import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.gui.core.elements.Image;
import net.hawkelele.quickinfopanel.gui.core.elements.Layout;
import net.hawkelele.quickinfopanel.gui.core.elements.Text;
import net.hawkelele.quickinfopanel.providers.client.PlayerDirectionProvider;
import net.hawkelele.quickinfopanel.services.Compass;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class CompassWidget extends Layout {
    @Override
    public boolean shouldBeHidden() {
        return !Config.isPanelEnabled("compass");
    }

    public CompassWidget() {
        Compass compass = new Compass(new PlayerDirectionProvider());
        MutableComponent label = Component.literal(compass.getCurrentFacingCardinalDirection());
        String axis = compass.getFacingAxis();
        if (Config.read().showCompassAxis && !axis.isEmpty()) {
            label.append(Component.literal("(" + axis + ")").withStyle(ChatFormatting.GRAY));
        }
        this.children(
                new Image(compass.getIconPath()),
                new Text(label)
        ).gap(2);
    }
}
