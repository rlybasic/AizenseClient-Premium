//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.math.BlockPos
 */
package ru.aizensense.utils;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import ru.aizensense.utils.MinecraftHelper;

public class BlockPosUtils
implements MinecraftHelper {
    public static BlockPos getBlockLocation(Block blocks, int range) {
        Minecraft mc = Minecraft.getMinecraft();
        BlockPos block = null;
        int bestRange = range;
        for (int x = -range; x < range; ++x) {
            for (int y = range; y > -range; --y) {
                for (int z = -range; z < range; ++z) {
                    int rangeFromBlockToPlayer;
                    BlockPos blockPos = new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ).add(x, y, z);
                    if (mc.world.getBlockState(blockPos).getBlock() != blocks || (rangeFromBlockToPlayer = (int)blockPos.getDistance((int)mc.player.posX, (int)mc.player.posY, (int)mc.player.posZ)) >= bestRange) continue;
                    bestRange = rangeFromBlockToPlayer;
                    block = blockPos;
                }
            }
        }
        return block;
    }
}

