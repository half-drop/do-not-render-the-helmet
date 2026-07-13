package io.github.halfdrop.nohat.mixin;

import io.github.halfdrop.nohat.render.PlayerRenderStateExtension;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntityRenderState.class)
abstract class PlayerEntityRenderStateMixin implements PlayerRenderStateExtension {
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
