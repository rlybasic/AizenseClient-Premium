//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package ru.aizensense.utils;

import net.minecraft.client.Minecraft;
import ru.aizensense.utils.RotationTask;
import ru.aizensense.utils.RotationTaskFactory;

public final class RotationManager {
    private final RotationTaskFactory factory = new RotationTaskFactory();
    private float yaw;
    private float pitch;

    public void addTask(RotationTask rotationTask) {
        this.factory.addTask(rotationTask);
    }

    public void removeTask(RotationTask rotationTask) {
        this.factory.removeTask(rotationTask);
    }

    public void removeTask(String rotationTaskName) {
        this.factory.removeTask(rotationTaskName);
    }

    public void startTask(RotationTask rotationTask) {
        this.factory.startTask(rotationTask);
    }

    public void finishTask(RotationTask rotationTask) {
        this.factory.finishTask(rotationTask);
    }

    public void updateRotations() {
        this.yaw = Minecraft.getMinecraft().player.rotationYaw;
        this.pitch = Minecraft.getMinecraft().player.rotationPitch;
    }

    public void restoreRotations() {
        Minecraft.getMinecraft().player.rotationYaw = this.yaw;
        Minecraft.getMinecraft().player.rotationYawHead = this.yaw;
        Minecraft.getMinecraft().player.rotationPitch = this.pitch;
    }

    public void setPlayerRotations(float yaw, float pitch) {
        Minecraft.getMinecraft().player.rotationYaw = yaw;
        Minecraft.getMinecraft().player.rotationYawHead = yaw;
        Minecraft.getMinecraft().player.rotationPitch = pitch;
    }

    public void setPlayerYaw(float yaw) {
        Minecraft.getMinecraft().player.rotationYaw = yaw;
        Minecraft.getMinecraft().player.rotationYawHead = yaw;
    }

    public void setPlayerPitch(float pitch) {
        Minecraft.getMinecraft().player.rotationPitch = pitch;
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public RotationTaskFactory getFactory() {
        return this.factory;
    }
}

