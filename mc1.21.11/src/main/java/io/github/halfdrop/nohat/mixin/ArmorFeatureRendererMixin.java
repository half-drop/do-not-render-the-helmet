package io.github.halfdrop.nohat.mixin;

import io.github.halfdrop.nohat.NoHatClient;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
abstract class ArmorFeatureRendererMixin {
    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private void nohat$skipPlayerHelmet(
            MatrixStack matrices,
            OrderedRenderCommandQueue queue,
            ItemStack stack,
            EquipmentSlot slot,
            int light,
            BipedEntityRenderState state,
            CallbackInfo ci
    ) {
        if (NoHatClient.isHelmetRenderingDisabled()
                && slot == EquipmentSlot.HEAD
                && state instanceof PlayerEntityRenderState) {
            ci.cancel();
        }
    }
}

