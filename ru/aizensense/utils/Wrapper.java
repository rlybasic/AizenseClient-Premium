//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package ru.aizensense.utils;

import net.minecraft.client.Minecraft;
import ru.aizensense.AizenSense;

public interface Wrapper {
    public static final Minecraft mc = Minecraft.getMinecraft();

    default public boolean nullCheck() {
        return Wrapper.mc.player != null || Wrapper.mc.world != null;
    }

    default public AizenSense getCosmos() {
        return AizenSense.instance;
    }
}

