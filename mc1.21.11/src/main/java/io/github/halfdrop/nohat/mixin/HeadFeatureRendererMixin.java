package io.github.halfdrop.nohat.mixin;

import io.github.halfdrop.nohat.NoHatClient;
import io.github.halfdrop.nohat.render.PlayerRenderStateExtension;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeadFeatureRenderer.class)
abstract class HeadFeatureRendererMixin {
    @Inject(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;ILnet/minecraft/client/render/entity/state/LivingEntityRenderState;FF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void nohat$blockPlayerHeadItem(
            MatrixStack matrices,
            OrderedRenderCommandQueue queue,
            int light,
            LivingEntityRenderState state,
            float limbAngle,
            float limbDistance,
            CallbackInfo ci
    ) {
        if (NoHatClient.isHelmetRenderingDisabled()
                && state instanceof PlayerRenderStateExtension extension
                && extension.nohat$isActualPlayer()) {
            ci.cancel();
        }
    }
}
