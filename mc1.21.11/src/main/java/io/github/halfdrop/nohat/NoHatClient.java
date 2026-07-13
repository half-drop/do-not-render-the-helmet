package io.github.halfdrop.nohat;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public final class NoHatClient implements ClientModInitializer {
    private static boolean helmetRenderingDisabled = true;

    @Override
    public void onInitializeClient() {
        KeyBinding.Category category = KeyBinding.Category.create(Identifier.of("nohat", "general"));
        KeyBinding toggle = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.nohat.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggle.wasPressed()) {
                helmetRenderingDisabled = !helmetRenderingDisabled;
                if (client.player != null) {
                    client.player.sendMessage(Text.translatable(
                            helmetRenderingDisabled ? "message.nohat.disabled" : "message.nohat.enabled"
                    ), true);
                }
            }
        });
    }

    public static boolean isHelmetRenderingDisabled() {
        return helmetRenderingDisabled;
    }
}

