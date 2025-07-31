package com.morehats;
import com.morehats.items.*;
import com.morehats.commands.HatAdjustCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceKey;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
public class MoreHats implements ModInitializer {
    // ----------------------------------------------------------------------------------------
    public static final BaseHat[] ALL_HATS = {
        new AceHat(new Item.Properties()),
        new NolanGreenHat(new Item.Properties()),
        new GangsterRedHat(new Item.Properties()),
        new SkullHat(new Item.Properties()),
        new CharmanderHat(new Item.Properties()),
        new GengarHat(new Item.Properties()),
        new AquaHat(new Item.Properties()),
        new SamuraiHelmet(new Item.Properties())//,
        //new MewtwoHat(new Item.Properties()),
        //new AceGengar(new Item.Properties())
    };
    
    public static final BaseHat ACE_HAT = ALL_HATS[0];
    public static final BaseHat NOLAN_GREEN_HAT = ALL_HATS[1];
    public static final BaseHat GANGSTER_RED_HAT = ALL_HATS[2];
    public static final BaseHat SKULL_HAT = ALL_HATS[3];
    public static final BaseHat CHARMANDER_HAT = ALL_HATS[4];
    public static final BaseHat GENGAR_HAT = ALL_HATS[5];
    public static final BaseHat AQUA_HAT = ALL_HATS[6];
    public static final BaseHat SAMURAI_HELMET = ALL_HATS[7];
    //public static final BaseHat MEWTWO_HAT = ALL_HATS[8];
    //public static final BaseHat ACE_GENGAR = ALL_HATS[9];

    private static final String[] HAT_NAMES = {
        "ace_hat",
        "nolan_green",
        "ace_gangsterhatred",
        "ace_skullhat",
        "charmanderhat",
        "gengarhat",
        "pkmnaquahat",
        "samuraihelmet"//,
        //"mewtwohat",
        //"ace_gengarhat"
    };
    // ----------------------------------------------------------------------------------------
    public static final String MOD_ID = "morehats";
    public static final ResourceKey<CreativeModeTab> MORE_HATS_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), ResourceLocation.fromNamespaceAndPath(MOD_ID, "more_hats"));
    public static final CreativeModeTab MORE_HATS_TAB = FabricItemGroup.builder().icon(() -> new ItemStack(ACE_HAT)).title(Component.literal("More Hats")).build();
    @Override
    public void onInitialize() { Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MORE_HATS_TAB_KEY.location(), MORE_HATS_TAB); registerItems(); registerCommands(); System.out.println("More Hats loaded!"); }
    private void registerCommands() { CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> { HatAdjustCommand.register(dispatcher); }); }
    private void registerItems() { for (int i = 0; i < ALL_HATS.length; i++) { BaseHat hat = ALL_HATS[i]; String name = HAT_NAMES[i]; Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(MOD_ID, name), hat); TrinketRendererRegistry.registerRenderer(hat, hat); } ItemGroupEvents.modifyEntriesEvent(MORE_HATS_TAB_KEY).register(content -> { for (BaseHat hat : ALL_HATS) content.accept(hat); }); }
}