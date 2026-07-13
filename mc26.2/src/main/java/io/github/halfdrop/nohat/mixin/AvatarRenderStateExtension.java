package io.github.halfdrop.nohat.mixin;

public interface AvatarRenderStateExtension {
    boolean nohat$isActualPlayer();

    void nohat$setActualPlayer(boolean actualPlayer);
}

