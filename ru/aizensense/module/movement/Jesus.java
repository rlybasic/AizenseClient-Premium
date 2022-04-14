//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package ru.aizensense.module.movement;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;

public class Jesus
extends Module {
    public Jesus() {
        super("Jesus", "Ground Water", Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (Jesus.mc.world.getBlockState(new BlockPos(Jesus.mc.player.posX, Jesus.mc.player.posY + 1.0, Jesus.mc.player.posZ)).getBlock() == Block.getBlockById((int)9)) {
            Jesus.mc.player.motionY = 0.006f;
        } else if (Jesus.mc.world.getBlockState(new BlockPos(Jesus.mc.player.posX, Jesus.mc.player.posY + 0.0, Jesus.mc.player.posZ)).getBlock() == Block.getBlockById((int)9)) {
            Jesus.mc.player.fallDistance = 0.0f;
            Jesus.mc.player.motionX *= 0.9;
            Jesus.mc.player.motionY = 0.08f;
            float Speed = 0.14f;
            Jesus.mc.player.jumpMovementFactor = 0.14f;
            Jesus.mc.player.motionZ *= 0.9;
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}

