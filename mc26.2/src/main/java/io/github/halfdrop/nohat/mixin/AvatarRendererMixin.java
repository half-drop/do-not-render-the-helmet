package io.github.halfdrop.nohat.mixin;

import io.github.halfdrop.nohat.NoHatClient;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AvatarRenderer.class)
abstract class AvatarRendererMixin {
    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;F)V", at = @At("TAIL"))
    private void nohat$hideHeadSlotItem(
            Avatar player,
            AvatarRenderState state,
            float tickDelta,
            CallbackInfo ci
    ) {
        if (NoHatClient.isHelmetRenderingDisabled()) {
            state.headEquipment = ItemStack.EMPTY;
        }
    }
}

