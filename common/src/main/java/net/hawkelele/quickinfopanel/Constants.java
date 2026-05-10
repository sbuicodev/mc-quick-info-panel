package net.hawkelele.quickinfopanel;

import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
    public static final String MOD_ID = "quickinfopanel";
    public static final String MOD_NAME = "Quick Info Panel";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final KeyMapping.Category KEYMAPPING_CATEGORY = new KeyMapping.Category(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "name"));
}