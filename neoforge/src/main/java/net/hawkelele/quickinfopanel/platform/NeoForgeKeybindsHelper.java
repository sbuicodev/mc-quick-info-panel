package net.hawkelele.quickinfopanel.platform;

import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.input.Keybind;
import net.hawkelele.quickinfopanel.platform.services.IKeybindsHelper;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;

import java.util.HashSet;
import java.util.Set;

@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class NeoForgeKeybindsHelper implements IKeybindsHelper {
    private static final Set<Keybind> KEYBINDS = new HashSet<>();
    private static boolean tickListenerRegistered = false;

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        KEYBINDS.forEach(keybind -> event.register(keybind.getKeyMapping()));
    }

    public static void onClientTick(ClientTickEvent.Post event) {
        KEYBINDS.forEach(keybind -> {
            while (keybind.getKeyMapping().consumeClick()) {
                keybind.getHandler().accept(Minecraft.getInstance());
            }
        });
    }

    @Override
    public void registerKeybind(Keybind keybind) {
        KEYBINDS.add(keybind);
        if (!tickListenerRegistered) {
            NeoForge.EVENT_BUS.addListener(NeoForgeKeybindsHelper::onClientTick);
            tickListenerRegistered = true;
        }
    }
}
