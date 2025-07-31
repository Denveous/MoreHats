package com.morehats.commands;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.morehats.items.BaseHat;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import dev.emi.trinkets.api.TrinketsApi;
public class HatAdjustCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("hatadjust")
            .then(Commands.literal("scale").then(Commands.argument("value", FloatArgumentType.floatArg()).executes(context -> adjustHat(context.getSource().getPlayerOrException(), hat -> hat.setScale(FloatArgumentType.getFloat(context, "value")), "scale", FloatArgumentType.getFloat(context, "value")))))
            .then(Commands.literal("height").then(Commands.argument("value", FloatArgumentType.floatArg()).executes(context -> adjustHat(context.getSource().getPlayerOrException(), hat -> hat.setHeight(FloatArgumentType.getFloat(context, "value")), "height", FloatArgumentType.getFloat(context, "value")))))
            .then(Commands.literal("forward").then(Commands.argument("value", FloatArgumentType.floatArg()).executes(context -> adjustHat(context.getSource().getPlayerOrException(), hat -> hat.setForward(FloatArgumentType.getFloat(context, "value")), "forward", FloatArgumentType.getFloat(context, "value")))))
            .then(Commands.literal("side").then(Commands.argument("value", FloatArgumentType.floatArg()).executes(context -> adjustHat(context.getSource().getPlayerOrException(), hat -> hat.setSide(FloatArgumentType.getFloat(context, "value")), "side", FloatArgumentType.getFloat(context, "value")))))
            .then(Commands.literal("print").executes(context -> printHat(context.getSource().getPlayerOrException()))));
    }
    private static int adjustHat(Player player, java.util.function.Consumer<BaseHat> adjuster, String property, Object value) {
        BaseHat hat = getEquippedHat(player);
        if (hat != null) { adjuster.accept(hat); player.sendSystemMessage(Component.literal("Hat " + property + " set to: " + value)); }
        else { player.sendSystemMessage(Component.literal("No hat equipped!")); }
        return 1;
    }
    private static int printHat(Player player) {
        BaseHat hat = getEquippedHat(player);
        if (hat != null) { player.sendSystemMessage(Component.literal("Scale: " + hat.getScale() + ", Height: " + hat.getHeight() + ", Forward: " + hat.getForward() + ", Side: " + hat.getSide())); }
        else { player.sendSystemMessage(Component.literal("No hat equipped!")); }
        return 1;
    }
    private static BaseHat getEquippedHat(Player player) {
        var trinkets = TrinketsApi.getTrinketComponent(player);
        if (trinkets.isPresent()) { var equipped = trinkets.get().getAllEquipped();
            for (var pair: equipped) { ItemStack stack = pair.getB(); if (stack.getItem() instanceof BaseHat) { return (BaseHat) stack.getItem(); } }
        }
        return null;
    }
}