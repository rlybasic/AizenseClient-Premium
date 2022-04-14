//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package ru.aizensense.module.player;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;

public class Blink
extends Module {
    private double[] oldPosition;
    private EntityOtherPlayerMP freecam = null;

    public Blink() {
        super("Blink", "My ass", Category.PLAYER);
    }

    @SubscribeEvent
    public void onPlayerTickEvent(TickEvent.PlayerTickEvent playerTickEvent) {
        Blink.mc.player.capabilities.isFlying = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Blink.mc.player.setPositionAndRotation(this.oldPosition[0], this.oldPosition[1], this.oldPosition[2], Blink.mc.player.rotationYaw, Blink.mc.player.rotationPitch);
        Blink.mc.world.removeEntityFromWorld(-420);
        this.freecam = null;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.clonePositions();
        this.freecam.copyLocationAndAnglesFrom((Entity)Blink.mc.player);
        this.freecam.noClip = true;
        this.freecam.rotationYawHead = Blink.mc.player.rotationYawHead;
        Blink.mc.world.addEntityToWorld(-420, (Entity)this.freecam);
    }

    private void clonePositions() {
        this.oldPosition = new double[]{Blink.mc.player.posX, Blink.mc.player.posY, Blink.mc.player.posZ};
    }
}

