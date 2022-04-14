//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package ru.aizensense.module.movement;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;

public class strafe
extends Module {
    public static void strafe() {
        strafe.strafe(strafe.getSpeed());
    }

    public static void strafe(float f) {
        if (!strafe.isMoving()) {
            return;
        }
        double d = strafe.getDirection();
        strafe.mc.player.motionX = -Math.sin(d) * (double)f;
        strafe.mc.player.motionZ = Math.cos(d) * (double)f;
    }

    public strafe() {
        super("Strafe", "strafing", Category.MOVEMENT);
    }

    public static float getSpeed() {
        return (float)Math.sqrt(strafe.mc.player.motionX * strafe.mc.player.motionX + strafe.mc.player.motionZ * strafe.mc.player.motionZ);
    }

    public static double getDirection() {
        float f = strafe.mc.player.rotationYaw;
        if (strafe.mc.player.moveForward < 0.0f) {
            f += 180.0f;
        }
        float f2 = 1.0f;
        if (strafe.mc.player.moveForward < 0.0f) {
            f2 = -0.9f;
        } else if (strafe.mc.player.moveForward > 0.0f) {
            f2 = 0.9f;
        }
        if (strafe.mc.player.moveStrafing > 0.0f) {
            f -= 90.0f * f2;
        }
        if (strafe.mc.player.moveStrafing < 0.0f) {
            f += 90.0f * f2;
        }
        return Math.toRadians(f);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        strafe.strafe();
    }

    public static boolean isMoving() {
        return strafe.mc.player != null && (strafe.mc.player.movementInput.moveForward != 0.0f || strafe.mc.player.movementInput.moveStrafe != 0.0f);
    }
}

