/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package ru.aizensense.utils;

import net.minecraft.entity.Entity;
import ru.aizensense.utils.UtilsForFov;

public class FOVUtil {
    public static boolean isInAttackFOV(Entity entity, int fov) {
        return UtilsForFov.getDistanceFromMouse(entity) <= fov;
    }
}

