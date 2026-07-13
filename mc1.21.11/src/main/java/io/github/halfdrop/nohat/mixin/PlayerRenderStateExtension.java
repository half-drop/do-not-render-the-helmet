package io.github.halfdrop.nohat.mixin;

public interface PlayerRenderStateExtension {
    boolean nohat$isActualPlayer();

    void nohat$setActualPlayer(boolean actualPlayer);
}

