package io.github.halfdrop.nohat.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.halfdrop.nohat.NoHatClient;
import io.github.halfdrop.nohat.render.AvatarRenderStateExtension;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CustomHeadLayer.class)
abstract class CustomHeadLayerMixin {
    @Inject(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;FF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void nohat$blockPlayerHeadItem(
            PoseStack poseStack,
            SubmitNodeCollector collector,
            int light,
            LivingEntityRenderState state,
            float limbAngle,
            float limbDistance,
            CallbackInfo ci
    ) {
        if (NoHatClient.isHelmetRenderingDisabled()
                && state instanceof AvatarRenderStateExtension extension
                && extension.nohat$isActualPlayer()) {
            ci.cancel();
        }
    }
}
