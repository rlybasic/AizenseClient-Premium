//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 */
package ru.aizensense.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class Utils {
    public static float getPitch(Entity entity) {
        double x = entity.posX - Minecraft.getMinecraft().player.posX;
        double y = entity.posY - Minecraft.getMinecraft().player.posY;
        double z = entity.posZ - Minecraft.getMinecraft().player.posZ;
        double pitch = Math.asin(y /= (double)Minecraft.getMinecraft().player.getDistance(entity)) * 57.29577951308232;
        pitch = -pitch;
        return (float)pitch;
    }

    public static float getYaw(Entity entity) {
        double x = entity.posX - Minecraft.getMinecraft().player.posX;
        double y = entity.posY - Minecraft.getMinecraft().player.posY;
        double z = entity.posZ - Minecraft.getMinecraft().player.posZ;
        double yaw = Math.atan2(x, z) * 57.29577951308232;
        yaw = -yaw;
        return (float)yaw;
    }
}

