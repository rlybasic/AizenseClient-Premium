//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package ru.aizensense.utils.animation;

import net.minecraft.client.Minecraft;

public class AnimationUtil {
    public static float speedTarget = 0.125f;

    public static float animation(float current, float targetAnimation, float speed) {
        return AnimationUtil.animation(current, targetAnimation, speedTarget, speed);
    }

    public static float animation(float animation, float target, float poxyi, float speedTarget) {
        float da = (target - animation) / Math.max((float)Minecraft.getDebugFPS(), 5.0f) * 15.0f;
        if (da > 0.0f) {
            da = Math.max(speedTarget, da);
            da = Math.min(target - animation, da);
        } else if (da < 0.0f) {
            da = Math.min(-speedTarget, da);
            da = Math.max(target - animation, da);
        }
        return animation + da;
    }

    public static double animate(double target, double current, double speed) {
        boolean larger = target > current;
        boolean bl = larger;
        if (speed < 0.0) {
            speed = 0.0;
        } else if (speed > 1.0) {
            speed = 1.0;
        }
        double dif = Math.max(target, current) - Math.min(target, current);
        double factor = dif * speed;
        if (factor < 0.1) {
            factor = 0.1;
        }
        current = larger ? (current = current + factor) : (current = current - factor);
        return current;
    }
}

