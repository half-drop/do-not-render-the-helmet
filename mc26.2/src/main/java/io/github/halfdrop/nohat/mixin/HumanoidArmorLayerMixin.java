package io.github.halfdrop.nohat.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.halfdrop.nohat.NoHatClient;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
abstract class HumanoidArmorLayerMixin {
    @Inject(method = "renderArmorPiece", at = @At("HEAD"), cancellable = true)
    private void nohat$blockPlayerHeadArmor(
            PoseStack poseStack,
            SubmitNodeCollector collector,
            ItemStack stack,
            EquipmentSlot slot,
            int light,
            HumanoidRenderState state,
            CallbackInfo ci
    ) {
        if (NoHatClient.isHelmetRenderingDisabled()
                && slot == EquipmentSlot.HEAD
                && state instanceof AvatarRenderState) {
            ci.cancel();
        }
    }
}

