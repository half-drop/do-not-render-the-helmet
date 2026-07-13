package io.github.halfdrop.nohat;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public final class NoHatClient implements ClientModInitializer {
    private static boolean helmetRenderingDisabled = true;

    @Override
    public void onInitializeClient() {
        KeyMapping.Category category = KeyMapping.Category.register(Identifier.fromNamespaceAndPath("nohat", "general"));
        KeyMapping toggle = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.nohat.toggle",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggle.consumeClick()) {
                helmetRenderingDisabled = !helmetRenderingDisabled;
                if (client.player != null) {
                    client.player.sendOverlayMessage(Component.translatable(
                            helmetRenderingDisabled ? "message.nohat.disabled" : "message.nohat.enabled"
                    ));
                }
            }
        });
    }

    public static boolean isHelmetRenderingDisabled() {
        return helmetRenderingDisabled;
    }
}
