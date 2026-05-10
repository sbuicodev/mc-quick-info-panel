package net.hawkelele.quickinfopanel;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class ExampleMod {

    public ExampleMod(IEventBus modBus) {
        Constants.LOG.info("Hello NeoForge world!");
        Common.init();
    }
}