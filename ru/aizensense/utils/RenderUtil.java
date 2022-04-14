//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  org.lwjgl.opengl.GL11
 */
package ru.aizensense.utils;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;
import ru.aizensense.utils.MinecraftHelper;

public class RenderUtil
implements MinecraftHelper {
    private static final Frustum frustrum = new Frustum();
    private static final double DOUBLE_PI = Math.PI * 2;

    public static double interpolate(double current, double old, double scale) {
        return old + (current - old) * scale;
    }

    public static void glRenderStart() {
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)2884);
        GL11.glDisable((int)3553);
    }

    public static void glRenderStop() {
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2884);
        GL11.glDisable((int)3042);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public static void drawCircle(float x, float y, float radius, int color) {
        float f = (float)(color >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(color & 0xFF) / 255.0f;
        boolean flag = GL11.glIsEnabled((int)3042);
        boolean flag1 = GL11.glIsEnabled((int)2848);
        boolean flag2 = GL11.glIsEnabled((int)3553);
        if (!flag) {
            GL11.glEnable((int)3042);
        }
        if (!flag1) {
            GL11.glEnable((int)2848);
        }
        if (flag2) {
            GL11.glDisable((int)3553);
        }
        GL11.glEnable((int)2848);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glBegin((int)9);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d((double)((double)x + Math.sin((double)i * Math.PI / 180.0) * (double)radius), (double)((double)y + Math.cos((double)i * Math.PI / 180.0) * (double)radius));
        }
        GL11.glEnd();
        GL11.glDisable((int)2848);
        if (flag2) {
            GL11.glEnable((int)3553);
        }
        if (!flag1) {
            GL11.glDisable((int)2848);
        }
        if (!flag) {
            GL11.glDisable((int)3042);
        }
    }

    public static void drawCircle228(float x, float y, float radius, int color, int jopaSlona) {
        float f = (float)(color >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(color & 0xFF) / 255.0f;
        boolean flag = GL11.glIsEnabled((int)3042);
        boolean flag1 = GL11.glIsEnabled((int)2848);
        boolean flag2 = GL11.glIsEnabled((int)3553);
        if (!flag) {
            GL11.glEnable((int)3042);
        }
        if (!flag1) {
            GL11.glEnable((int)2848);
        }
        if (flag2) {
            GL11.glDisable((int)3553);
        }
        GL11.glEnable((int)2848);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glLineWidth((float)2.5f);
        GL11.glBegin((int)3);
        for (int i = 0; i <= jopaSlona; ++i) {
            GL11.glVertex2d((double)((double)x + Math.sin((double)i * Math.PI / 180.0) * (double)radius), (double)((double)y + Math.cos((double)i * Math.PI / 180.0) * (double)radius));
        }
        GL11.glEnd();
        GL11.glDisable((int)2848);
        if (flag2) {
            GL11.glEnable((int)3553);
        }
        if (!flag1) {
            GL11.glDisable((int)2848);
        }
        if (!flag) {
            GL11.glDisable((int)3042);
        }
    }

    public static void drawCircle228(float x, float y, float radius, int lineWidth, int outsideC, int insideC, int jopaSlona) {
        RenderUtil.drawCircle228(x, y, radius, insideC, jopaSlona);
    }

    public static void drawBorderedRect(float xPos, float yPos, float width, float height, float lineWidth, int lineColor, int bgColor) {
        RenderUtil.drawRect(xPos, yPos, width, height, bgColor);
        float f = (float)(lineColor >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(lineColor >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(lineColor >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(lineColor & 0xFF) / 255.0f;
        RenderUtil.glRenderStart();
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glLineWidth((float)lineWidth);
        GL11.glEnable((int)2848);
        GL11.glBegin((int)1);
        GL11.glVertex2d((double)xPos, (double)yPos);
        GL11.glVertex2d((double)width, (double)yPos);
        GL11.glVertex2d((double)width, (double)yPos);
        GL11.glVertex2d((double)width, (double)height);
        GL11.glVertex2d((double)width, (double)height);
        GL11.glVertex2d((double)xPos, (double)height);
        GL11.glVertex2d((double)xPos, (double)height);
        GL11.glVertex2d((double)xPos, (double)yPos);
        GL11.glEnd();
        RenderUtil.glRenderStop();
    }

    public static void setColor(Color c) {
        GL11.glColor4d((double)((float)c.getRed() / 255.0f), (double)((float)c.getGreen() / 255.0f), (double)((float)c.getBlue() / 255.0f), (double)((float)c.getAlpha() / 255.0f));
    }

    public static void drawUnfilledCircle(float x, float y, float radius, float lineWidth, int color) {
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        GL11.glEnable((int)2848);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
        GlStateManager.enableBlend();
        GL11.glColor4f((float)f, (float)f1, (float)f2, (float)f3);
        GL11.glLineWidth((float)lineWidth);
        GL11.glBegin((int)2);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d((double)((double)x + Math.sin((double)i * Math.PI / 180.0) * (double)radius), (double)((double)y + Math.cos((double)i * Math.PI / 180.0) * (double)radius));
        }
        GL11.glEnd();
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glHint((int)3154, (int)4352);
        GL11.glHint((int)3155, (int)4352);
        GlStateManager.disableBlend();
    }

    public static void drawBorderedCircle(float x, float y, float radius, int lineWidth, int outsideC, int insideC) {
        RenderUtil.drawCircle(x, y, radius, insideC);
        RenderUtil.drawUnfilledCircle(x, y, radius, lineWidth, outsideC);
    }

    public static float convertColor(int count, int color) {
        return (float)(color >> count & 0xFF) / 255.0f;
    }

    public static void drawOctagon(float xPos, float yPos, float width, float height, float length, float angle, int color) {
        float f = RenderUtil.convertColor(24, color);
        float f1 = RenderUtil.convertColor(16, color);
        float f2 = RenderUtil.convertColor(8, color);
        float f3 = RenderUtil.convertColor(0, color);
        RenderUtil.glRenderStart();
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glBegin((int)9);
        GL11.glVertex2d((double)(xPos + length), (double)yPos);
        GL11.glVertex2d((double)(xPos + width - length), (double)yPos);
        GL11.glVertex2d((double)(xPos + width - length), (double)yPos);
        GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0f - angle));
        GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0f - angle));
        GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0f + angle));
        GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0f + angle));
        GL11.glVertex2d((double)(xPos + width - length), (double)(yPos + height));
        GL11.glVertex2d((double)(xPos + width - length), (double)(yPos + height));
        GL11.glVertex2d((double)(xPos + length), (double)(yPos + height));
        GL11.glVertex2d((double)(xPos + length), (double)(yPos + height));
        GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0f + angle));
        GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0f + angle));
        GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0f - angle));
        GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0f - angle));
        GL11.glVertex2d((double)(xPos + length), (double)yPos);
        GL11.glEnd();
        RenderUtil.glRenderStop();
    }

    public static void drawLinesAroundPlayer(Entity entity, double radius, float partialTicks, int points, double width, int color, float hight) {
        GL11.glPushMatrix();
        RenderUtil.enableGL2D3();
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glDisable((int)2929);
        GL11.glLineWidth((float)((float)width));
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2929);
        GL11.glBegin((int)3);
        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks - renderManager.viewerPosX;
        double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks - renderManager.viewerPosY + (double)hight;
        double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks - renderManager.viewerPosZ;
        RenderUtil.color228(color);
        for (int i = 0; i <= points; ++i) {
            GL11.glVertex3d((double)(x + radius * Math.cos((double)i * (Math.PI * 2) / (double)points)), (double)y, (double)(z + radius * Math.sin((double)i * (Math.PI * 2) / (double)points)));
        }
        GL11.glEnd();
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        RenderUtil.disableGL2D3();
        GL11.glPopMatrix();
    }

    public static void renderItem(ItemStack itemStack, int x, int y) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.enableDepth();
        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, x, y);
        mc.getRenderItem().renderItemOverlays(RenderUtil.mc.fontRenderer, itemStack, x, y);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.disableDepth();
    }

    public static void drawRectWithEdge(double x, double y, double width, double height, Color color, Color color2) {
        RenderUtil.drawRect(x, y, x + width, y + height, color.getRGB());
        int c = color2.getRGB();
        RenderUtil.drawRect(x - 1.0, y, x, y + height, c);
        RenderUtil.drawRect(x + width, y, x + width + 1.0, y + height, c);
        RenderUtil.drawRect(x - 1.0, y - 1.0, x + width + 1.0, y, c);
        RenderUtil.drawRect(x - 1.0, y + height, x + width + 1.0, y + height + 1.0, c);
    }

    public static void drawRoundedRect(double x, double y, double x1, double y1, int borderC, int insideC) {
        RenderUtil.drawRect(x + 0.5, y, x1 - 0.5, y + 0.5, insideC);
        RenderUtil.drawRect(x + 0.5, y1 - 0.5, x1 - 0.5, y1, insideC);
        RenderUtil.drawRect(x, y + 0.5, x1, y1 - 0.5, insideC);
    }

    public static void drawRoundedRect(int xCoord, int yCoord, int xSize, int ySize, int colour) {
        int width = xCoord + xSize;
        int height = yCoord + ySize;
        RenderUtil.drawRect(xCoord + 1, yCoord, width - 1, height, colour);
        RenderUtil.drawRect(xCoord, yCoord + 1, width, height - 1, colour);
    }

    public static void drawBoundingBox(AxisAlignedBB axisalignedbb) {
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.minY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.maxY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.minY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.maxY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.minY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.maxY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.minY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.maxY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.maxY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.minY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.maxY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.minY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.maxY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.minY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.maxY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.minY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.maxY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.maxY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.maxY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.maxY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.maxY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.maxY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.maxY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.maxY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.minY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.minY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.minY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.minY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.minY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.minY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.minY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.minY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.minY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.maxY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.minY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.maxY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.minY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.maxY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.minY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.maxY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.maxY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.minY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.maxY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.minX, (double)axisalignedbb.minY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.maxY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.minY, (double)axisalignedbb.minZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.maxY, (double)axisalignedbb.maxZ);
        GL11.glVertex3d((double)axisalignedbb.maxX, (double)axisalignedbb.minY, (double)axisalignedbb.maxZ);
        GL11.glEnd();
    }

    public static void drawFilledCircle(int xx, int yy, float radius, Color color) {
        int sections = 6;
        double dAngle = Math.PI * 2 / (double)sections;
        GL11.glPushAttrib((int)8192);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glBegin((int)6);
        for (int i = 0; i < sections; ++i) {
            float x = (float)((double)radius * Math.sin((double)i * dAngle));
            float y = (float)((double)radius * Math.cos((double)i * dAngle));
            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
            GL11.glVertex2f((float)((float)xx + x), (float)((float)yy + y));
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnd();
        GL11.glPopAttrib();
    }

    public static final void drawSmoothRect(float left, float top, float right, float bottom, int color) {
        GL11.glEnable((int)3042);
        GL11.glEnable((int)2848);
        RenderUtil.drawRect(left, top, right, bottom, color);
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        RenderUtil.drawRect(left * 2.0f - 1.0f, top * 2.0f, left * 2.0f, bottom * 2.0f - 1.0f, color);
        RenderUtil.drawRect(left * 2.0f, top * 2.0f - 1.0f, right * 2.0f, top * 2.0f, color);
        RenderUtil.drawRect(right * 2.0f, top * 2.0f, right * 2.0f + 1.0f, bottom * 2.0f - 1.0f, color);
        RenderUtil.drawRect(left * 2.0f, bottom * 2.0f - 1.0f, right * 2.0f, bottom * 2.0f, color);
        GL11.glDisable((int)3042);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
    }

    public static void drawImage(ResourceLocation image, int x, int y, int width, int height) {
        GL11.glEnable((int)2848);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture((int)x, (int)y, (float)0.0f, (float)0.0f, (int)width, (int)height, (float)width, (float)height);
        RenderUtil.disableGL2D();
        GL11.glPopMatrix();
    }

    public static int raindbow(int deley) {
        double raindbowState = Math.ceil((System.currentTimeMillis() + (long)deley) / 20L);
        return Color.getHSBColor((float)((raindbowState %= 360.0) / 360.0), 0.5f, 1.0f).getRGB();
    }

    public static void drawCircle(float cx, double cy, float r, int minus, int c) {
        GL11.glPushMatrix();
        cx *= 2.0f;
        cy *= 2.0;
        GlStateManager.glLineWidth((float)6.0f);
        float f = (float)(c >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(c >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(c >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(c & 0xFF) / 255.0f;
        float theta = (float)Math.PI / 180;
        float p = (float)Math.cos(theta);
        float s = (float)Math.sin(theta);
        float x = r *= 2.0f;
        float y = 0.0f;
        GL11.glEnable((int)2848);
        RenderUtil.enableGL2D();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glPointSize((float)6.0f);
        GL11.glBegin((int)3);
        int[] counter = new int[]{1};
        for (int ii = 0; ii < 360 - minus; ++ii) {
            c = RenderUtil.raindbow(counter[0] * 10);
            f = (float)(c >> 24 & 0xFF) / 255.0f;
            f1 = (float)(c >> 16 & 0xFF) / 255.0f;
            f2 = (float)(c >> 8 & 0xFF) / 255.0f;
            f3 = (float)(c & 0xFF) / 255.0f;
            GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
            GL11.glVertex2f((float)(x + cx), (float)((float)((double)y + cy)));
            float t = x;
            x = p * x - s * y;
            y = s * t + p * y;
            counter[0] = counter[0] + 1;
        }
        GL11.glEnd();
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        RenderUtil.disableGL2D();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.glLineWidth((float)1.0f);
        GL11.glPopMatrix();
    }

    public static void prepareScissorBox(float x, float y, float x2, float y2) {
        ScaledResolution scale = new ScaledResolution(Minecraft.getMinecraft());
        int factor = scale.getScaleFactor();
        GL11.glScissor((int)((int)(x * (float)factor)), (int)((int)(((float)scale.getScaledHeight() - y2) * (float)factor)), (int)((int)((x2 - x) * (float)factor)), (int)((int)((y2 - y) * (float)factor)));
    }

    public static void drawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
        float f = (float)(col1 >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(col1 >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(col1 >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(col1 & 0xFF) / 255.0f;
        float f4 = (float)(col2 >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(col2 >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(col2 >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(col2 & 0xFF) / 255.0f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glShadeModel((int)7425);
        GL11.glPushMatrix();
        GL11.glBegin((int)7);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glVertex2d((double)left, (double)top);
        GL11.glVertex2d((double)left, (double)bottom);
        GL11.glColor4f((float)f5, (float)f6, (float)f7, (float)f4);
        GL11.glVertex2d((double)right, (double)bottom);
        GL11.glVertex2d((double)right, (double)top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
    }

    public static void drawPolygonPart(double x, double y, int radius, int part, int color, int endcolor) {
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        float alpha2 = (float)(endcolor >> 24 & 0xFF) / 255.0f;
        float red2 = (float)(endcolor >> 16 & 0xFF) / 255.0f;
        float green2 = (float)(endcolor >> 8 & 0xFF) / 255.0f;
        float blue2 = (float)(endcolor & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(x, y, 0.0).color(red, green, blue, alpha).endVertex();
        double TWICE_PI = Math.PI * 2;
        for (int i = part * 90; i <= part * 90 + 90; ++i) {
            double angle = Math.PI * 2 * (double)i / 360.0 + Math.toRadians(180.0);
            bufferbuilder.pos(x + Math.sin(angle) * (double)radius, y + Math.cos(angle) * (double)radius, 0.0).color(red2, green2, blue2, alpha2).endVertex();
        }
        tessellator.draw();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawVGradientRect(float left, float top, float right, float bottom, int startColor, int endColor) {
        float f = (float)(startColor >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(startColor >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(startColor >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(startColor & 0xFF) / 255.0f;
        float f5 = (float)(endColor >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(endColor >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(endColor >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(endColor & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)right, (double)top, 0.0).color(f2, f3, f4, f).endVertex();
        bufferbuilder.pos((double)left, (double)top, 0.0).color(f2, f3, f4, f).endVertex();
        bufferbuilder.pos((double)left, (double)bottom, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, 0.0).color(f6, f7, f8, f5).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static Color injectAlpha(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    public static void drawGlow(double x, double y, double x1, double y1, int color, double alpha) {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        RenderUtil.drawVGradientRect((int)x, (int)y, (int)x1, (int)(y + (y1 - y) / 2.0), RenderUtil.injectAlpha(new Color(color), 0).getRGB(), RenderUtil.injectAlpha(new Color(color), (int)alpha).getRGB());
        RenderUtil.drawVGradientRect((int)x, (int)(y + (y1 - y) / 2.0), (int)x1, (int)y1, RenderUtil.injectAlpha(new Color(color), (int)alpha).getRGB(), RenderUtil.injectAlpha(new Color(color), 0).getRGB());
        int radius = (int)((y1 - y) / 2.0);
        RenderUtil.drawPolygonPart(x, y + (y1 - y) / 2.0, radius, 0, RenderUtil.injectAlpha(new Color(color), (int)alpha).getRGB(), RenderUtil.injectAlpha(new Color(color), 0).getRGB());
        RenderUtil.drawPolygonPart(x, y + (y1 - y) / 2.0, radius, 1, RenderUtil.injectAlpha(new Color(color), (int)alpha).getRGB(), RenderUtil.injectAlpha(new Color(color), 0).getRGB());
        RenderUtil.drawPolygonPart(x1, y + (y1 - y) / 2.0, radius, 2, RenderUtil.injectAlpha(new Color(color), (int)alpha).getRGB(), RenderUtil.injectAlpha(new Color(color), 0).getRGB());
        RenderUtil.drawPolygonPart(x1, y + (y1 - y) / 2.0, radius, 3, RenderUtil.injectAlpha(new Color(color), (int)alpha).getRGB(), RenderUtil.injectAlpha(new Color(color), 0).getRGB());
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void enableGL2D() {
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
    }

    public static void disableGL2D() {
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glHint((int)3154, (int)4352);
        GL11.glHint((int)3155, (int)4352);
    }

    public static void relativeRect(float left, float top, float right, float bottom, int color) {
        if (left < right) {
            float i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            float j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.color((float)f, (float)f1, (float)f2, (float)f3);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos((double)left, (double)bottom, 0.0).endVertex();
        bufferBuilder.pos((double)right, (double)bottom, 0.0).endVertex();
        bufferBuilder.pos((double)right, (double)top, 0.0).endVertex();
        bufferBuilder.pos((double)left, (double)top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawBorderedRect(double left, double top, double right, double bottom, double borderWidth, int insideColor, int borderColor, boolean borderIncludedInBounds) {
        RenderUtil.drawRect(left - (!borderIncludedInBounds ? borderWidth : 0.0), top - (!borderIncludedInBounds ? borderWidth : 0.0), right + (!borderIncludedInBounds ? borderWidth : 0.0), bottom + (!borderIncludedInBounds ? borderWidth : 0.0), borderColor);
        RenderUtil.drawRect(left + (borderIncludedInBounds ? borderWidth : 0.0), top + (borderIncludedInBounds ? borderWidth : 0.0), right - (borderIncludedInBounds ? borderWidth : 0.0), bottom - (borderIncludedInBounds ? borderWidth : 0.0), insideColor);
    }

    public static void drawRect(double left, double top, double right, double bottom, int color) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.color((float)f, (float)f1, (float)f2, (float)f3);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(left, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, top, 0.0).endVertex();
        bufferBuilder.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawRectOpacity(double left, double top, double right, double bottom, float opacity) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.color((float)0.1f, (float)0.1f, (float)0.1f, (float)opacity);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(left, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, bottom, 0.0).endVertex();
        bufferBuilder.pos(right, top, 0.0).endVertex();
        bufferBuilder.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawNewRect(double left, double top, double right, double bottom, int color) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f, (float)f1, (float)f2, (float)f3);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(left, bottom, 0.0).endVertex();
        vertexbuffer.pos(right, bottom, 0.0).endVertex();
        vertexbuffer.pos(right, top, 0.0).endVertex();
        vertexbuffer.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static boolean isInViewFrustrum(Entity entity) {
        return RenderUtil.isInViewFrustrum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
    }

    public static void drawLinesAroundPlayer(Entity entity, double radius, float partialTicks, int points, float width, int color) {
        GL11.glPushMatrix();
        RenderUtil.enableGL2D3();
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glDisable((int)2929);
        GL11.glLineWidth((float)width);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2929);
        GL11.glBegin((int)3);
        RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
        double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks - renderManager.viewerPosX;
        double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks - renderManager.viewerPosY;
        double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks - renderManager.viewerPosZ;
        RenderUtil.color228(color);
        for (int i = 0; i <= points; ++i) {
            GL11.glVertex3d((double)(x + radius * Math.cos((double)i * (Math.PI * 2) / (double)points)), (double)y, (double)(z + radius * Math.sin((double)i * (Math.PI * 2) / (double)points)));
        }
        GL11.glEnd();
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        RenderUtil.disableGL2D3();
        GL11.glPopMatrix();
    }

    public static void enableGL2D3() {
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
    }

    public static void disableGL2D3() {
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glHint((int)3154, (int)4352);
        GL11.glHint((int)3155, (int)4352);
    }

    public static void color228(int color) {
        GL11.glColor4ub((byte)((byte)(color >> 16 & 0xFF)), (byte)((byte)(color >> 8 & 0xFF)), (byte)((byte)(color & 0xFF)), (byte)((byte)(color >> 24 & 0xFF)));
    }

    private static boolean isInViewFrustrum(AxisAlignedBB bb) {
        Entity current = mc.getRenderViewEntity();
        frustrum.setPosition(current.posX, current.posY, current.posZ);
        return frustrum.isBoundingBoxInFrustum(bb);
    }

    public static void glColor(int hex) {
        float alpha = (float)(hex >> 24 & 0xFF) / 255.0f;
        float red = (float)(hex >> 16 & 0xFF) / 255.0f;
        float green = (float)(hex >> 8 & 0xFF) / 255.0f;
        float blue = (float)(hex & 0xFF) / 255.0f;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
    }

    public static void blockEsp(BlockPos blockPos, Color c, double length, double length2) {
        double d = blockPos.getX();
        double x = d - RenderUtil.mc.getRenderManager().viewerPosX;
        double d2 = blockPos.getY();
        Minecraft.getMinecraft().getRenderManager();
        double y = d2 - RenderUtil.mc.getRenderManager().viewerPosY;
        double d3 = blockPos.getZ();
        Minecraft.getMinecraft().getRenderManager();
        double z = d3 - RenderUtil.mc.getRenderManager().viewerPosZ;
        GL11.glPushMatrix();
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glColor4d((double)((float)c.getRed() / 255.0f), (double)((float)c.getGreen() / 255.0f), (double)((float)c.getBlue() / 255.0f), (double)0.15);
        RenderUtil.drawColorBox(new AxisAlignedBB(x, y, z, x + length2, y + 1.0, z + length), 0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glColor4d((double)0.0, (double)0.0, (double)0.0, (double)0.5);
        RenderUtil.drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + length2, y + 1.0, z + length));
        GL11.glLineWidth((float)2.0f);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static void blockEspFrame(BlockPos blockPos, double red, double green, double blue) {
        double d = blockPos.getX();
        double x = d - RenderUtil.mc.getRenderManager().viewerPosX;
        double d2 = blockPos.getY();
        Minecraft.getMinecraft().getRenderManager();
        double y = d2 - RenderUtil.mc.getRenderManager().viewerPosY;
        double d3 = blockPos.getZ();
        Minecraft.getMinecraft().getRenderManager();
        double z = d3 - RenderUtil.mc.getRenderManager().viewerPosZ;
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glLineWidth((float)1.0f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glColor4d((double)red, (double)green, (double)blue, (double)0.5);
        RenderUtil.drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
    }

    public static void drawSelectionBoundingBox(AxisAlignedBB boundingBox) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
        tessellator.draw();
        vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
        tessellator.draw();
        vertexbuffer.begin(1, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        tessellator.draw();
    }

    public static void drawColorBox(AxisAlignedBB axisalignedbb, float red, float green, float blue, float alpha) {
        Tessellator ts = Tessellator.getInstance();
        BufferBuilder vb = ts.getBuffer();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        vb.pos(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ).color(red, green, blue, alpha).endVertex();
        ts.draw();
    }

    public static void rectangleBordered(double x, double y, double x1, double y1, double width, int internalColor, int borderColor) {
        RenderUtil.drawRect(x + width, y + width, x1 - width, y1 - width, internalColor);
        RenderUtil.drawRect(x + width, y, x1 - width, y + width, borderColor);
        RenderUtil.drawRect(x, y, x + width, y1, borderColor);
        RenderUtil.drawRect(x1 - width, y, x1, y1, borderColor);
        RenderUtil.drawRect(x + width, y1 - width, x1 - width, y1, borderColor);
    }
}

