//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 */
package ru.aizensense.utils;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class ChatUtil {
    public static void type(String text) {
        if (Minecraft.getMinecraft().ingameGUI != null || Minecraft.getMinecraft().player != null) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage((ITextComponent)new TextComponentString(String.format((Object)ChatFormatting.BLACK + "[" + (Object)ChatFormatting.WHITE + "%s" + (Object)ChatFormatting.BLACK + "]:" + (Object)ChatFormatting.RESET + " %s", "BOBRCLIENT", text)));
        }
    }
}

