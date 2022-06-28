package net.ethansk13s.symphocraft;

import org.lwjgl.glfw.GLFW;

import net.ethansk13s.symphocraft.core.SymphogearForm;
import net.ethansk13s.symphocraft.items.renderer.DarkGungnirArmedGearRenderer;
import net.ethansk13s.symphocraft.items.renderer.DarkGungnirRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class SymphocraftClient implements ClientModInitializer {
    private static KeyBinding TRANSFORM_KEY = KeyBindingHelper.registerKeyBinding(
        new KeyBinding(
            "key.symphocraft.transform",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            "category.symphocraft.general"
        )
    );

    private static KeyBinding SONG_KEY = KeyBindingHelper.registerKeyBinding(
        new KeyBinding(
            "key.symphocraft.song",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F,
            "category.symphocraft.general")
    );

    @Override
    public void onInitializeClient() {
        SymphogearForm symphogearForm = new SymphogearForm(null);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            PlayerEntity player = client.player;
            while (TRANSFORM_KEY.wasPressed()) {
                if (player.getInventory().getArmorStack(2).isIn(RegisterItems.RELIC_AMULETS)) {
                    symphogearForm.setPlayer(player);
                }
            }

            while (SONG_KEY.wasPressed()) {
                if (symphogearForm.getPlayer() != null) {
                    symphogearForm.getSongMode().setIsInSongMode(true);
                }
            }
            
            symphogearForm.getSongMode().tick();
            symphogearForm.tick();
        });
        
        GeoItemRenderer.registerItemRenderer(RegisterItems.DARK_GUNGNIR_ARMED_GEAR, new DarkGungnirArmedGearRenderer());

        GeoArmorRenderer.registerArmorRenderer(new DarkGungnirRenderer(), 
            RegisterItems.DARK_GUNGNIR_VISOR,
            RegisterItems.DARK_GUNGNIR_CHEST,
            RegisterItems.DARK_GUNGNIR_LEGGINGS,
            RegisterItems.DARK_GUNGNIR_HEELS);
    }
}
