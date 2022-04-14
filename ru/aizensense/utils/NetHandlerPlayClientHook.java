//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.util.text.ITextComponent
 */
package ru.aizensense.utils;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.util.text.ITextComponent;
import ru.aizensense.notifications.Type;
import ru.aizensense.notifications.notifications;

public class NetHandlerPlayClientHook
extends NetHandlerPlayClient {
    public NetHandlerPlayClientHook(Minecraft mcIn, GuiScreen p_i46300_2_, NetworkManager networkManagerIn, GameProfile profileIn) {
        super(mcIn, p_i46300_2_, networkManagerIn, profileIn);
    }

    public void sendPacket(Packet<?> packetIn) {
        notifications.add("rotation", "work", Type.OK);
        super.sendPacket(packetIn);
    }

    public void onDisconnect(ITextComponent reason) {
        super.onDisconnect(reason);
    }
}

