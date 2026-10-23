package net.hawkelele.quickinfopanel.fabric.config;

//? if fabric {
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import net.hawkelele.quickinfopanel.config.QuickInfoPanelConfigScreen;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return QuickInfoPanelConfigScreen::new;
    }
}
//?}
