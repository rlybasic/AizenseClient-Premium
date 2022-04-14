//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 */
package ru.aizensense.utils;

import net.minecraft.util.math.BlockPos;

public class Location {
    private int x;
    private int y;
    private int z;
    private int id;
    private BlockPos pos;

    public Location(int x, int y, int z, int id) {
        this.pos = new BlockPos(x, y, z);
        this.id = id;
    }

    public Location(BlockPos pos) {
        this(pos, -1);
    }

    public Location(BlockPos pos, int id) {
        this(pos.getX(), pos.getY(), pos.getZ(), id);
        this.pos = pos;
        this.id = id;
    }

    public int hashCode() {
        return (this.pos.getY() + this.pos.getZ() * 859433) * 859433 + this.pos.getX();
    }

    public BlockPos getPos() {
        return this.pos;
    }

    public int getX() {
        return this.pos.getX();
    }

    public int getY() {
        return this.pos.getY();
    }

    public int getZ() {
        return this.pos.getZ();
    }

    public int getId() {
        return this.id;
    }

    public static Location readBytes(byte[] b) {
        byte id = b[0];
        int x = b[1] & 0xFF | b[2] << 8 & 0x7FFF;
        if ((b[2] & 0x80) == 128) {
            x = -x;
        }
        int y = b[3] & 0xFF | b[4] << 8 & 0x7FFF;
        if ((b[4] & 0x80) == 128) {
            y = -y;
        }
        int z = b[5] & 0xFF | b[6] << 8 & 0x7FFF;
        if ((b[6] & 0x80) == 128) {
            z = -z;
        }
        return new Location(x, y, z, id);
    }

    public Location add(int x, int y, int z) {
        return new Location(new BlockPos(this.pos.getX() + x, this.pos.getY() + y, this.pos.getZ() + z), this.id);
    }

    public void setPos(BlockPos p) {
        this.pos = p;
    }

    public byte[] toBytes() {
        byte[] b = new byte[7];
        int x = this.getX();
        int y = this.getY();
        int z = this.getZ();
        b[0] = (byte)(this.id & 0xFF);
        b[1] = (byte)(Math.abs(x) & 0xFF);
        b[2] = (byte)(Math.abs(x) >> 8 & 0xFF | (x < 0 ? 128 : 0));
        b[3] = (byte)(Math.abs(y) & 0xFF);
        b[4] = (byte)(Math.abs(y) >> 8 & 0x7F | (y < 0 ? 128 : 0));
        b[5] = (byte)(Math.abs(z) & 0xFF);
        b[6] = (byte)(Math.abs(z) >> 8 & 0x7F | (z < 0 ? 128 : 0));
        return b;
    }
}

