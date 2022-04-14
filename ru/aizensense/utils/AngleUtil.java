//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 */
package ru.aizensense.utils;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import ru.aizensense.utils.Rotation;
import ru.aizensense.utils.Wrapper;

public class AngleUtil
implements Wrapper {
    public static Rotation calculateAngles(Vec3d to) {
        float yaw = (float)(Math.toDegrees(Math.atan2(to.subtract((Vec3d)AngleUtil.mc.player.getPositionEyes((float)1.0f)).z, to.subtract((Vec3d)AngleUtil.mc.player.getPositionEyes((float)1.0f)).x)) - 90.0);
        float pitch = (float)Math.toDegrees(-Math.atan2(to.subtract((Vec3d)AngleUtil.mc.player.getPositionEyes((float)1.0f)).y, Math.hypot(to.subtract((Vec3d)AngleUtil.mc.player.getPositionEyes((float)1.0f)).x, to.subtract((Vec3d)AngleUtil.mc.player.getPositionEyes((float)1.0f)).z)));
        return new Rotation(MathHelper.wrapDegrees((float)yaw), MathHelper.wrapDegrees((float)pitch));
    }

    public static Vec3d getVectorForRotation(Rotation rotation) {
        float yawCos = MathHelper.cos((float)(-rotation.getYaw() * ((float)Math.PI / 180) - (float)Math.PI));
        float yawSin = MathHelper.sin((float)(-rotation.getYaw() * ((float)Math.PI / 180) - (float)Math.PI));
        float pitchCos = -MathHelper.cos((float)(-rotation.getPitch() * ((float)Math.PI / 180)));
        float pitchSin = MathHelper.sin((float)(-rotation.getPitch() * ((float)Math.PI / 180)));
        return new Vec3d((double)(yawSin * pitchCos), (double)pitchSin, (double)(yawCos * pitchCos));
    }
}

