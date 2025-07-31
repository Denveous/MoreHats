package com.morehats.items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import java.util.List;
public class AquaHat extends BaseHat {
    private static final float SCALE = 0.6F;
    private static final float HEIGHT = -0.4F; 
    private static final float FORWARD = 0.0F;
    private static final float SIDE = 0.0F;
    private static final String TOOLTIP = "Team Aqua Hat";
    public AquaHat(Item.Properties settings) { super(settings, SCALE, HEIGHT, FORWARD, SIDE); }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) { super.appendHoverText(stack, context, tooltip, type); tooltip.add(Component.literal(TOOLTIP)); }
}
