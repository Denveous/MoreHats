package com.morehats.items;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import com.mojang.math.Axis;
public abstract class BaseHat extends TrinketItem implements TrinketRenderer {
    public float hatScale;
    public float hatHeight;
    public float hatForward;
    public float hatSide;
    public BaseHat(Item.Properties settings, float scale, float height, float forward, float side) { super(settings); this.hatScale = scale; this.hatHeight = height; this.hatForward = forward; this.hatSide = side; }
    public float getScale() { return hatScale; }
    public float getHeight() { return hatHeight; }
    public float getForward() { return hatForward; }
    public float getSide() { return hatSide; }
    public void setScale(float scale) { this.hatScale = scale; }
    public void setHeight(float height) { this.hatHeight = height; }
    public void setForward(float forward) { this.hatForward = forward; }
    public void setSide(float side) { this.hatSide = side; }
    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, PoseStack matrixStack, MultiBufferSource vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        matrixStack.pushPose();
        if (entity.isSwimming() || entity.isFallFlying()) { 
            if (contextModel instanceof PlayerModel) { PlayerModel<Player> playerModel = (PlayerModel)contextModel; matrixStack.mulPose(Axis.ZP.rotationDegrees(playerModel.head.zRot)); }
            matrixStack.mulPose(Axis.YP.rotationDegrees(headYaw)); matrixStack.mulPose(Axis.XP.rotationDegrees(-45.0F)); 
        } else { 
            if (entity.isCrouching() && !contextModel.young) matrixStack.translate(0.0F, 0.25F, 0.0F); 
            matrixStack.mulPose(Axis.YP.rotationDegrees(headYaw)); matrixStack.mulPose(Axis.XP.rotationDegrees(headPitch)); 
        }
        matrixStack.translate(hatSide, hatHeight, hatForward); matrixStack.scale(-hatScale, -hatScale, hatScale); matrixStack.translate(0.0F, -0.25F, 0.0F); 
        itemRenderer.renderStatic(entity, stack, ItemDisplayContext.HEAD, false, matrixStack, vertexConsumers, entity.level(), light, net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY, 0);
        matrixStack.popPose();
    }
}
