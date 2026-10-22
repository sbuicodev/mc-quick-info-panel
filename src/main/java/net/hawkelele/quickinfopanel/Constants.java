package net.hawkelele.quickinfopanel;

//? if >=1.21.9 {
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
//?}
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
    public static final String MOD_ID = "quickinfopanel";
    public static final String MOD_NAME = "Quick Info Panel";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    //? if >=1.21.9 {
    public static final KeyMapping.Category KEYMAPPING_CATEGORY = new KeyMapping.Category(Identifier.fromNamespaceAndPath(Constants.MOD_ID, "name"));
    //?} else {
    /*// Key mapping categories are plain translation keys before 1.21.9
    public static final String KEYMAPPING_CATEGORY = "key.category." + MOD_ID + ".name";
    *///?}
    public static final boolean ENABLE_SINGLE_ELEMENT_HIDING = false;
}
