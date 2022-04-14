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
package ru.aizensense.module.combat;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;

public class Velocity
extends Module {
    public Velocity() {
        super("Velocity", "NO KB", Category.COMBAT);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent playerTickEvent) {
        if (Velocity.mc.world.getBlockState(new BlockPos(Velocity.mc.player.posX, Velocity.mc.player.posY, Velocity.mc.player.posZ)).getBlock() == Block.getBlockById((int)0) && Velocity.mc.player.hurtTime > 0) {
            float ticks = 0.1f;
            Velocity.mc.player.motionY = -ticks;
            float f = ticks + 1.5f;
        }
    }
}

