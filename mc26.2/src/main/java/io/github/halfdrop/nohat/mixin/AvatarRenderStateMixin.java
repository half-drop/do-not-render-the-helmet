package io.github.halfdrop.nohat.mixin;

import io.github.halfdrop.nohat.render.AvatarRenderStateExtension;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AvatarRenderState.class)
abstract class AvatarRenderStateMixin implements AvatarRenderStateExtension {
    @Unique
    private boolean nohat$actualPlayer;

    @Override
    public boolean nohat$isActualPlayer() {
        return nohat$actualPlayer;
    }

    @Override
    public void nohat$setActualPlayer(boolean actualPlayer) {
        nohat$actualPlayer = actualPlayer;
    }
}
