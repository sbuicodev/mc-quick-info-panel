package net.hawkelele.quickinfopanel.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.hawkelele.quickinfopanel.Constants;
import net.hawkelele.quickinfopanel.config.Config;
import net.hawkelele.quickinfopanel.config.LayoutPreset;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public final class QipCommands {
    private QipCommands() {
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context, Commands.CommandSelection selection) {
        var toggle = Commands.literal("toggle")
                .then(Commands.literal("main").executes(ctx -> toggle(ctx, ToggleTarget.MAIN)))
                .then(Commands.literal("secondary").executes(ctx -> toggle(ctx, ToggleTarget.SECONDARY)))
                .then(Commands.literal("debug").executes(ctx -> toggle(ctx, ToggleTarget.DEBUG)));

        if (Config.isSingleElementHidingEnabled()) {
            for (String key : Config.PANEL_KEYS) {
                toggle.then(Commands.literal(key).executes(ctx -> togglePanel(ctx, key)));
            }
        }

        dispatcher.register(Commands.literal("qip")
                .then(toggle)
                .then(Commands.literal("set")
                        .then(Commands.literal("position")
                                .then(Commands.argument("preset", StringArgumentType.word())
                                        .suggests(QipCommands::suggestPresets)
                                        .executes(ctx -> setLayout(ctx, StringArgumentType.getString(ctx, "preset")))
                                )
                        )
                )
                .executes(ctx -> sendHelp(ctx))
        );
    }

    private static int sendHelp(CommandContext<CommandSourceStack> ctx) {
        String toggleTargets = Config.isSingleElementHidingEnabled()
                ? "main|secondary|debug|coordinates|compass|clock|opposite|biome|weather"
                : "main|secondary|debug";
        ctx.getSource().sendSuccess(() -> Component.literal("Usage: /qip toggle <" + toggleTargets + "> | /qip set position <preset>"), false);
        return 1;
    }

    private static int toggle(CommandContext<CommandSourceStack> ctx, ToggleTarget target) {
        try {
            Config.write(config -> {
                switch (target) {
                    case MAIN -> config.displayMainPanel = !config.displayMainPanel;
                    case SECONDARY -> config.displaySecondaryPanel = !config.displaySecondaryPanel;
                    case DEBUG -> config.debugBounds = !config.debugBounds;
                }
                return config;
            });
            ctx.getSource().sendSuccess(() -> Component.literal("Toggled " + target.name().toLowerCase(Locale.ROOT)), true);
        } catch (IOException e) {
            Constants.LOG.error("Failed to save config", e);
            ctx.getSource().sendFailure(Component.literal("Failed to save config."));
        }
        return 1;
    }

    private static int togglePanel(CommandContext<CommandSourceStack> ctx, String key) {
        try {
            Config.write(config -> {
                config.panels.put(key, !config.panels.getOrDefault(key, true));
                return config;
            });
            ctx.getSource().sendSuccess(() -> Component.literal("Toggled panel " + key), true);
        } catch (IOException e) {
            Constants.LOG.error("Failed to save config", e);
            ctx.getSource().sendFailure(Component.literal("Failed to save config."));
        }
        return 1;
    }

    private static int setLayout(CommandContext<CommandSourceStack> ctx, String preset) {
        LayoutPreset layoutPreset = LayoutPreset.fromId(preset);
        try {
            Config.write(config -> {
                config.layout = layoutPreset.id();
                return config;
            });
            ctx.getSource().sendSuccess(() -> Component.literal("Set position to " + layoutPreset.id()), true);
        } catch (IOException e) {
            Constants.LOG.error("Failed to save config", e);
            ctx.getSource().sendFailure(Component.literal("Failed to save config."));
        }
        return 1;
    }

    private static CompletableFuture<com.mojang.brigadier.suggestion.Suggestions> suggestPresets(CommandContext<CommandSourceStack> ctx, SuggestionsBuilder builder) throws CommandSyntaxException {
        return SharedSuggestionProvider.suggest(Arrays.stream(LayoutPreset.values()).map(LayoutPreset::id), builder);
    }

    private enum ToggleTarget {
        MAIN,
        SECONDARY,
        DEBUG
    }
}
