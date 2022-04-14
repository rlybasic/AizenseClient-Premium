//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 */
package ru.aizensense.utils;

import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import ru.aizensense.utils.RenderUtil;
import ru.aizensense.utils.Wrapper;

public class RenderTracerUtils {
    public static void drawtracers(BlockPos block, float red, float green, float blue, float alpha, float ticks) {
        double renderPosX = Wrapper.mc.getRenderManager().viewerPosX;
        double renderPosY = Wrapper.mc.getRenderManager().viewerPosY;
        double renderPosZ = Wrapper.mc.getRenderManager().viewerPosZ;
        double xPos = (double)block.getX() - renderPosX;
        double yPos = (double)block.getY() - renderPosY;
        double zPos = (double)block.getZ() - renderPosZ;
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glEnable((int)2848);
        GL11.glDisable((int)2896);
        GL11.glLineWidth((float)0.5f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
        Vec3d eyes = null;
        eyes = new Vec3d(0.0, 0.0, 1.0).rotatePitch(-((float)Math.toRadians(Wrapper.mc.player.rotationPitch))).rotateYaw(-((float)Math.toRadians(Wrapper.mc.player.rotationYaw)));
        GL11.glBegin((int)1);
        GL11.glVertex3d((double)eyes.x, (double)((double)Wrapper.mc.player.getEyeHeight() + eyes.y), (double)eyes.z);
        GL11.glVertex3d((double)xPos, (double)yPos, (double)zPos);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)2896);
        GL11.glDepthMask((boolean)true);
    }

    public static void blockesp(BlockPos block, Color color, boolean outline) {
        double x = (double)block.getX() - Wrapper.mc.getRenderManager().viewerPosX;
        double y = (double)block.getY() - Wrapper.mc.getRenderManager().viewerPosY;
        double z = (double)block.getZ() - Wrapper.mc.getRenderManager().viewerPosZ;
        GL11.glPushMatrix();
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GlStateManager.color((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)1.5f);
        RenderUtil.drawColorBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0), 0.0f, 0.0f, 0.0f, 0.0f);
        if (outline) {
            GlStateManager.color((float)0.0f, (float)0.0f, (float)0.0f, (float)0.5f);
            RenderUtil.drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        }
        GL11.glLineWidth((float)1.0f);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }
}

