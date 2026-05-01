package net.hawkelele.quickinfopanel.providers.client;

import net.hawkelele.quickinfopanel.providers.DimensionProvider;
import net.minecraft.client.Minecraft;

public class PlayerDimensionProvider implements DimensionProvider {
    private static final Minecraft client = Minecraft.getInstance();

    @Override
    public String getDimension() {
        assert client.level != null;
        return client.level.dimensionTypeRegistration().getRegisteredName();
    }
}
