//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 */
package ru.aizensense.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class BlockData {
    public final BlockPos position;
    public final EnumFacing face;

    public BlockData(BlockPos add, EnumFacing up) {
        this.position = add;
        this.face = up;
    }

    public static BlockData getBlockData(BlockPos pos) {
        if (Minecraft.getMinecraft().world.getBlockState(pos.add(0, -1, 0)).getBlock() != Blocks.AIR) {
            return new BlockData(pos.add(0, -1, 0), EnumFacing.UP);
        }
        if (Minecraft.getMinecraft().world.getBlockState(pos.add(-1, 0, 0)).getBlock() != Blocks.AIR) {
            return new BlockData(pos.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (Minecraft.getMinecraft().world.getBlockState(pos.add(1, 0, 0)).getBlock() != Blocks.AIR) {
            return new BlockData(pos.add(1, 0, 0), EnumFacing.WEST);
        }
        if (Minecraft.getMinecraft().world.getBlockState(pos.add(0, 0, -1)).getBlock() != Blocks.AIR) {
            return new BlockData(pos.add(0, 0, -1), EnumFacing.SOUTH);
        }
        if (Minecraft.getMinecraft().world.getBlockState(pos.add(0, 0, 1)).getBlock() != Blocks.AIR) {
            return new BlockData(pos.add(0, 0, 1), EnumFacing.NORTH);
        }
        return null;
    }
}

