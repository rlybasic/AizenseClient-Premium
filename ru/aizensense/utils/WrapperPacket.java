//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.network.Packet
 */
package ru.aizensense.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.Packet;

public class WrapperPacket {
    public static volatile WrapperPacket INSTANCE = new WrapperPacket();

    public Minecraft mc() {
        return Minecraft.getMinecraft();
    }

    public EntityPlayerSP player() {
        return WrapperPacket.INSTANCE.mc().player;
    }

    public WorldClient world() {
        return WrapperPacket.INSTANCE.mc().world;
    }

    public void sendPacket(Packet packet) {
        this.player().connection.sendPacket(packet);
    }

    public InventoryPlayer inventory() {
        return this.player().inventory;
    }

    public PlayerControllerMP controller() {
        return WrapperPacket.INSTANCE.mc().playerController;
    }
}

