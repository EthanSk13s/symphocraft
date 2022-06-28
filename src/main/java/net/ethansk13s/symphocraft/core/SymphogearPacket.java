package net.ethansk13s.symphocraft.core;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.util.Identifier;

public class SymphogearPacket {
    public static final Identifier SYMPHOGEAR_TRANSFORM_PACKET = new Identifier("symphocraft", "sypmphogear_transform_packet");

    public static void registerReceivePacket() {
        ServerPlayNetworking.registerGlobalReceiver(SYMPHOGEAR_TRANSFORM_PACKET, ((server, player, handler, buf, responseSender) -> {
            boolean transformStatus = buf.readBoolean();
            
            server.execute(() -> {
                if (SymphogearForm.previousInventory.size() == 0) {
                    for (int i = 0; i < player.getInventory().armor.size(); i++) {
                        SymphogearForm.previousInventory.add(player.getInventory().armor.get(i));
                    }
                }
                if (transformStatus) {
                    SymphogearForm.serverEquipPlayer(player);
                } else {
                    SymphogearForm.serverUnEquipPlayer(player);
                }
            });
        }));
    }
}
