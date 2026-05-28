package net.hawkelele.quickinfopanel.platform;

import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.platform.services.IConfigHelper;
import net.hawkelele.quickinfopanel.platform.services.IConfigStore;
import net.hawkelele.quickinfopanel.platform.services.IHudHelper;
import net.hawkelele.quickinfopanel.platform.services.IKeybindsHelper;
import net.hawkelele.quickinfopanel.platform.services.IPlatformHelper;

import java.util.ServiceLoader;

public class Services {
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static final IHudHelper HUD = load(IHudHelper.class);
    public static final IKeybindsHelper KEYBINDS = load(IKeybindsHelper.class);
    public static final IConfigHelper CONFIG_HELPER = load(IConfigHelper.class);
    public static final IConfigStore CONFIG = load(IConfigStore.class);

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz, Services.class.getClassLoader())
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        Constants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
