//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 */
package ru.aizensense.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class UtilsForFov {
    Minecraft mc = Minecraft.getMinecraft();

    public static Vec3d getEyesPos() {
        return new Vec3d(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY + (double)Minecraft.getMinecraft().player.getEyeHeight(), Minecraft.getMinecraft().player.posZ);
    }

    public static float[] getNeededRotations(Entity vec) {
        Vec3d eyesPos = UtilsForFov.getEyesPos();
        double diffX = vec.posX - eyesPos.x;
        double diffY = vec.posY - eyesPos.y;
        double diffZ = vec.posZ - eyesPos.z;
        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        float pitch = (float)(-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        return new float[]{Minecraft.getMinecraft().player.rotationYaw + MathHelper.wrapDegrees((float)(yaw - Minecraft.getMinecraft().player.rotationYaw)), Minecraft.getMinecraft().player.rotationPitch + MathHelper.wrapDegrees((float)(pitch - Minecraft.getMinecraft().player.rotationPitch))};
    }

    public static int getDistanceFromMouse(Entity entity) {
        float[] neededRotations = UtilsForFov.getNeededRotations(entity);
        if (neededRotations != null) {
            float neededYaw = Minecraft.getMinecraft().player.rotationYaw - neededRotations[0];
            float neededPitch = Minecraft.getMinecraft().player.rotationPitch - neededRotations[1];
            float distanceFromMouse = MathHelper.sqrt((float)(neededYaw * neededYaw + neededPitch * neededPitch * 2.0f));
            return (int)distanceFromMouse;
        }
        return -1;
    }

    public static String getPlayerName(EntityPlayer player) {
        return player.getGameProfile() != null ? player.getGameProfile().getName() : "null";
    }
}

