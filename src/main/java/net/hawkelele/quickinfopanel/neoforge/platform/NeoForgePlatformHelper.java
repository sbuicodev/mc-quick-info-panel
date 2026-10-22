package net.hawkelele.quickinfopanel.neoforge.platform;

//? if neoforge {
/*import net.hawkelele.quickinfopanel.platform.services.IPlatformHelper;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        //? if >=1.21.9 {
        return !FMLLoader.getCurrent().isProduction();
        //?} else {
        /^return !FMLLoader.isProduction();
        ^///?}
    }
}
*///?}
