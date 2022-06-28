package net.ethansk13s.symphocraft.core;

import java.util.ArrayList;

import net.ethansk13s.symphocraft.Symphocraft;
import net.ethansk13s.symphocraft.core.rhythm.BaseCombo;
import net.ethansk13s.symphocraft.core.symphogears.BaseSymphogear;
import net.ethansk13s.symphocraft.core.symphogears.DarkGungnir;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;

public class SymphogearForm {
    public static ArrayList<ItemStack> previousInventory = new ArrayList<>();
    public static BaseSymphogear defaultSymphogear = new DarkGungnir();
    public static ItemStack previousHand;
    private int clienticks;
    private PlayerEntity player;
    private BaseSymphogear currentSymphogear;
    private ArrayList<ItemStack> preTransformArmor;
    private SongMode songMode;
    private ItemStack preTransformStack;

    private static final int SYNC_LIMIT = 200;

    public SymphogearForm(PlayerEntity player) {
        this.player = player;
        this.currentSymphogear = new DarkGungnir();
        this.preTransformArmor = new ArrayList<>();
        this.songMode = new SongMode(null);
    }

    @Environment(EnvType.CLIENT)
    public void tick() {
        if (MinecraftClient.getInstance().isInSingleplayer() && MinecraftClient.getInstance().isPaused()) {
            return;
        }

        if (this.player != null) {
            if (this.clienticks == 0) {
                this.sendPacket(SymphogearPacket.SYMPHOGEAR_TRANSFORM_PACKET, this.equipPlayer());
                this.songMode.resetCombos((ArrayList<BaseCombo>) this.currentSymphogear.getCombos().clone());
            }
            this.clienticks++;
        }

        if (this.clienticks == SYNC_LIMIT) {
            Symphocraft.LOGGER.info(this.player.getEntityName() + " is not in symphogear form anymore");
            this.sendPacket(SymphogearPacket.SYMPHOGEAR_TRANSFORM_PACKET, this.unEquipPlayer());
            this.player = null;
            this.songMode.resetCombos((ArrayList<BaseCombo>) this.currentSymphogear.getCombos().clone());
            this.clienticks = 0;
        }
    }

    public boolean equipPlayer() {
        if (this.player != null) {
            ArmorItem[] symphogearParts = this.currentSymphogear.getSymphogearParts();
            for (int i = 0; i < symphogearParts.length; i++) {
                this.preTransformArmor.add(this.player.getInventory().getArmorStack(i));
                this.player.getInventory().armor.set(i, symphogearParts[i].getDefaultStack());

                this.player.getInventory().markDirty();
            }

            this.preTransformStack = this.player.getInventory().getMainHandStack();

            this.player.setStackInHand(this.player.getActiveHand(), this.currentSymphogear.getArmedGear().getDefaultStack());
            this.player.getInventory().markDirty();

            return true;
        } else {
            return false;
        }
    }

    private boolean unEquipPlayer() {
        if (this.player != null) {
            PlayerInventory playerInventory = this.player.getInventory();
            for (int i = 0; i < this.preTransformArmor.size(); i++) {
                playerInventory.armor.set(i, this.preTransformArmor.get(i));
                playerInventory.markDirty();
            }

            if (playerInventory.contains(this.currentSymphogear.getArmedGear().getDefaultStack())) {
                playerInventory.removeOne(this.currentSymphogear.getArmedGear().getDefaultStack());
            }

            this.preTransformArmor.clear();
            playerInventory.markDirty();

            this.preTransformStack = null;

            return false;
        } else {
            return true;
        }
    }

    private void sendPacket(Identifier channelName, boolean status) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBoolean(status);

        ClientPlayNetworking.send(channelName, buf);
    }

    public static void serverEquipPlayer(PlayerEntity player) {
        if (player != null) {
            ArmorItem[] symphogearParts = defaultSymphogear.getSymphogearParts();
            for (int i = 0; i < symphogearParts.length; i++) {
                player.getInventory().armor.set(i, symphogearParts[i].getDefaultStack());

                player.getInventory().markDirty();
            }

            previousHand = player.getInventory().getMainHandStack();
            player.setStackInHand(player.getActiveHand(), defaultSymphogear.getArmedGear().getDefaultStack());
            player.getInventory().markDirty();
        }
    }

    public static void serverUnEquipPlayer(PlayerEntity player) {
        if (player != null) {
            PlayerInventory playerInventory = player.getInventory();
            for (int i = 0; i < previousInventory.size(); i++) {
                playerInventory.armor.set(i, previousInventory.get(i));

                playerInventory.markDirty();
            }

            if (playerInventory.contains(defaultSymphogear.getArmedGear().getDefaultStack())) {
                for (int i = 0; i < player.getInventory().size(); i++) {
                    if (playerInventory.getStack(i).isItemEqual(defaultSymphogear.getArmedGear().getDefaultStack())) {
                        playerInventory.removeStack(i, 1);
                    }
                }
            }

            if (playerInventory.getMainHandStack().isEmpty()) {
                player.setStackInHand(player.getActiveHand(), previousHand);
            } else {
                ItemStack item = playerInventory.getMainHandStack();

                playerInventory.setStack(playerInventory.getEmptySlot(), item);
                player.setStackInHand(player.getActiveHand(), previousHand);
            }

            previousInventory.clear();
            playerInventory.markDirty();
            previousHand = null;

            previousInventory.clear();
        }
    }

    public SongMode getSongMode() {
        return this.songMode;
    }

    public PlayerEntity getPlayer() {
        return this.player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
        Symphocraft.LOGGER.info(this.player.getEntityName() + " is in symphogear form now!");
    }
}
