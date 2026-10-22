package net.hawkelele.quickinfopanel.handlers;

import net.minecraft.client.Minecraft;

import java.util.function.Consumer;

@FunctionalInterface
public interface IClientEventHandler extends Consumer<Minecraft> {
}
