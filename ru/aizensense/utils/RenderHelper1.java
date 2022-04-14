//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.shader.Framebuffer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.EXTFramebufferObject
 *  org.lwjgl.opengl.GL11
 */
package ru.aizensense.utils;

import java.awt.Color;
import java.lang.reflect.Method;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

public class RenderHelper1 {
    protected static float zLevel;
    public static Minecraft mc;
    private static final Frustum frustrum;
    private static final FloatBuffer COLOR_BUFFER;
    private static final Vec3d LIGHT0_POS;
    private static final Vec3d LIGHT1_POS;

    public static void drawRoundedRect(float left, float top, float right, float bottom, int smooth, Color color) {
        Gui.drawRect((int)((int)left + smooth), (int)((int)top), (int)((int)right - smooth), (int)((int)bottom), (int)color.getRGB());
        Gui.drawRect((int)((int)left), (int)((int)top + smooth), (int)((int)right), (int)((int)bottom - smooth), (int)color.getRGB());
        RenderHelper1.drawFilledCircle((int)left + smooth, (int)top + smooth, smooth, color);
        RenderHelper1.drawFilledCircle((int)right - smooth, (int)top + smooth, smooth, color);
        RenderHelper1.drawFilledCircle((int)right - smooth, (int)bottom - smooth, smooth, color);
        RenderHelper1.drawFilledCircle((int)left + smooth, (int)bottom - smooth, smooth, color);
    }

    public static void dispose() {
        GL11.glDisable((int)2960);
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    public static void drawGlow(double x, double y, double x1, double y1, int color, double alpha) {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        RenderHelper1.drawVGradientRect((int)x, (int)y, (int)x1, (int)(y + (y1 - y) / 2.0), RenderHelper1.injectAlpha(new Color(color), 0).getRGB(), RenderHelper1.injectAlpha(new Color(color), (int)alpha).getRGB());
        RenderHelper1.drawVGradientRect((int)x, (int)(y + (y1 - y) / 2.0), (int)x1, (int)y1, RenderHelper1.injectAlpha(new Color(color), (int)alpha).getRGB(), RenderHelper1.injectAlpha(new Color(color), 0).getRGB());
        int radius = (int)((y1 - y) / 2.0);
        RenderHelper1.drawPolygonPart(x, y + (y1 - y) / 2.0, radius, 0, RenderHelper1.injectAlpha(new Color(color), (int)alpha).getRGB(), RenderHelper1.injectAlpha(new Color(color), 0).getRGB());
        RenderHelper1.drawPolygonPart(x, y + (y1 - y) / 2.0, radius, 1, RenderHelper1.injectAlpha(new Color(color), (int)alpha).getRGB(), RenderHelper1.injectAlpha(new Color(color), 0).getRGB());
        RenderHelper1.drawPolygonPart(x1, y + (y1 - y) / 2.0, radius, 2, RenderHelper1.injectAlpha(new Color(color), (int)alpha).getRGB(), RenderHelper1.injectAlpha(new Color(color), 0).getRGB());
        RenderHelper1.drawPolygonPart(x1, y + (y1 - y) / 2.0, radius, 3, RenderHelper1.injectAlpha(new Color(color), (int)alpha).getRGB(), RenderHelper1.injectAlpha(new Color(color), 0).getRGB());
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static Color injectAlpha(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
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

    public static void putVertex3d(Vec3d vec) {
        GL11.glVertex3d((double)vec.x, (double)vec.y, (double)vec.z);
    }

    public static Vec3d getRenderPos(double x, double y, double z) {
        return new Vec3d(x -= RenderHelper1.mc.getRenderManager().viewerPosX, y -= RenderHelper1.mc.getRenderManager().viewerPosY, z -= RenderHelper1.mc.getRenderManager().viewerPosZ);
    }

    public static void write(boolean renderClipLayer) {
        RenderHelper1.checkSetupFBO();
        GL11.glClearStencil((int)0);
        GL11.glClear((int)1024);
        GL11.glEnable((int)2960);
        GL11.glStencilFunc((int)519, (int)1, (int)65535);
        GL11.glStencilOp((int)7680, (int)7680, (int)7681);
        if (!renderClipLayer) {
            GlStateManager.colorMask((boolean)false, (boolean)false, (boolean)false, (boolean)false);
        }
    }

    public static final void color(Color color) {
        if (color == null) {
            color = Color.white;
        }
        RenderHelper1.color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
    }

    public static final void color(double red, double green, double blue, double alpha) {
        GL11.glColor4d((double)red, (double)green, (double)blue, (double)alpha);
    }

    public static void erase(boolean invert) {
        GL11.glStencilFunc((int)(invert ? 514 : 517), (int)1, (int)65535);
        GL11.glStencilOp((int)7680, (int)7680, (int)7681);
        GlStateManager.colorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GL11.glAlphaFunc((int)516, (float)0.0f);
    }

    public static void checkSetupFBO() {
        Framebuffer fbo = Minecraft.getMinecraft().getFramebuffer();
        if (fbo != null && fbo.depthBuffer > -1) {
            RenderHelper1.setupFBO(fbo);
            fbo.depthBuffer = -1;
        }
    }

    public static Color setAlpha(Color color, int alpha) {
        alpha = MathHelper.clamp((int)alpha, (int)0, (int)255);
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    public static void setupFBO(Framebuffer fbo) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT((int)fbo.depthBuffer);
        int stencil_depth_buffer_ID = EXTFramebufferObject.glGenRenderbuffersEXT();
        EXTFramebufferObject.glBindRenderbufferEXT((int)36161, (int)stencil_depth_buffer_ID);
        EXTFramebufferObject.glRenderbufferStorageEXT((int)36161, (int)34041, (int)Minecraft.getMinecraft().displayWidth, (int)Minecraft.getMinecraft().displayHeight);
        EXTFramebufferObject.glFramebufferRenderbufferEXT((int)36160, (int)36128, (int)36161, (int)stencil_depth_buffer_ID);
        EXTFramebufferObject.glFramebufferRenderbufferEXT((int)36160, (int)36096, (int)36161, (int)stencil_depth_buffer_ID);
    }

    public static int getHealthColor(EntityLivingBase player) {
        float f = player.getHealth();
        float f1 = player.getMaxHealth();
        float f2 = Math.max(0.0f, Math.min(f, f1) / f1);
        return Color.HSBtoRGB(f2 / 3.0f, 1.0f, 1.0f) | 0xFF000000;
    }

    public static void drawGradientRect(double d, double e, double e2, double g, int startColor, int endColor) {
        float f = (float)(startColor >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(startColor >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(startColor >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(startColor & 0xFF) / 255.0f;
        float f4 = (float)(endColor >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(endColor >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(endColor >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(endColor & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(e2, e, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos(d, e, (double)zLevel).color(f1, f2, f3, f).endVertex();
        bufferbuilder.pos(d, g, (double)zLevel).color(f5, f6, f7, f4).endVertex();
        bufferbuilder.pos(e2, g, (double)zLevel).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawFullCircle(double xPos, double yPos, double r, int c) {
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        r *= 2.0;
        xPos *= 2.0;
        yPos *= 2.0;
        float f = (float)(c >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(c >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(c >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(c & 0xFF) / 255.0f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2848);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glBegin((int)6);
        for (int i = 0; i <= 360; ++i) {
            double x = Math.sin((double)i * Math.PI / 180.0) * r;
            double y = Math.cos((double)i * Math.PI / 180.0) * r;
            GL11.glVertex2d((double)(xPos + x), (double)(yPos + y));
        }
        GL11.glEnd();
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
    }

    public static void drawFullCircle(int cx, int cy, double r, int c) {
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        r *= 2.0;
        cx *= 2;
        cy *= 2;
        float f = (float)(c >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(c >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(c >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(c & 0xFF) / 255.0f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2848);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glBegin((int)6);
        for (int i = 0; i <= 360; ++i) {
            double x = Math.sin((double)i * Math.PI / 180.0) * r;
            double y = Math.cos((double)i * Math.PI / 180.0) * r;
            GL11.glVertex2d((double)((double)cx + x), (double)((double)cy + y));
        }
        GL11.glEnd();
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
    }

    public static int getColorFromPercentage(float current, float max) {
        float percentage = current / max / 3.0f;
        return Color.HSBtoRGB(percentage, 1.0f, 1.0f);
    }

    public static void drawFilledCircle(int xx, int yy, float radius, Color color) {
        int sections = 50;
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

    public static void drawRectWithEdge(double x, double y, double width, double height, Color color, Color color2) {
        RenderHelper1.drawRect(x, y, x + width, y + height, color.getRGB());
        int c = color2.getRGB();
        RenderHelper1.drawRect(x - 1.0, y, x, y + height, c);
        RenderHelper1.drawRect(x + width, y, x + width + 1.0, y + height, c);
        RenderHelper1.drawRect(x - 1.0, y - 1.0, x + width + 1.0, y, c);
        RenderHelper1.drawRect(x - 1.0, y + height, x + width + 1.0, y + height + 1.0, c);
    }

    public static void drawRoundedRect2(int x, int y, int width, int height, int cornerRadius, Color color) {
        Gui.drawRect((int)x, (int)(y + cornerRadius), (int)(x + cornerRadius), (int)(y + height - cornerRadius), (int)color.getRGB());
        Gui.drawRect((int)(x + cornerRadius), (int)y, (int)(x + width - cornerRadius), (int)(y + height), (int)color.getRGB());
        Gui.drawRect((int)(x + width - cornerRadius), (int)(y + cornerRadius), (int)(x + width), (int)(y + height - cornerRadius), (int)color.getRGB());
        RenderHelper1.drawArc(x + cornerRadius, y + cornerRadius, cornerRadius, 0, 90, color);
        RenderHelper1.drawArc(x + width - cornerRadius, y + cornerRadius, cornerRadius, 270, 360, color);
        RenderHelper1.drawArc(x + width - cornerRadius, y + height - cornerRadius, cornerRadius, 180, 270, color);
        RenderHelper1.drawArc(x + cornerRadius, y + height - cornerRadius, cornerRadius, 90, 180, color);
    }

    public static void drawHead(ResourceLocation skin, int width, int height) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(skin);
        Gui.drawScaledCustomSizeModalRect((int)width, (int)height, (float)8.0f, (float)8.0f, (int)8, (int)8, (int)45, (int)45, (float)64.0f, (float)64.0f);
    }

    public static int astofloc(int delay) {
        float hue;
        float speed = 3200.0f;
        for (hue = (float)(System.currentTimeMillis() % (long)((int)speed)) + (float)(delay / 2); hue > speed; hue -= speed) {
        }
        if ((double)(hue /= speed) > 0.5) {
            hue = 0.5f - hue - 0.5f;
        }
        return Color.HSBtoRGB(hue += 0.5f, 0.5f, 1.0f);
    }

    public static void prepareScissorBox(float x, float y, float x2, float y2) {
        ScaledResolution scale = new ScaledResolution(Minecraft.getMinecraft());
        int factor = scale.getScaleFactor();
        GL11.glScissor((int)((int)(x * (float)factor)), (int)((int)(((float)scale.getScaledHeight() - y2) * (float)factor)), (int)((int)((x2 - x) * (float)factor)), (int)((int)((y2 - y) * (float)factor)));
    }

    public static void drawArc(int x, int y, int radius, int startAngle, int endAngle, Color color) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        BufferBuilder worldRenderer = Tessellator.getInstance().getBuffer();
        worldRenderer.begin(6, DefaultVertexFormats.POSITION);
        worldRenderer.pos((double)x, (double)y, 0.0).endVertex();
        for (int i = (int)((double)startAngle / 360.0 * 100.0); i <= (int)((double)endAngle / 360.0 * 100.0); ++i) {
            double angle = Math.PI * 2 * (double)i / 100.0 + Math.toRadians(180.0);
            worldRenderer.pos((double)x + Math.sin(angle) * (double)radius, (double)y + Math.cos(angle) * (double)radius, 0.0).endVertex();
        }
        Tessellator.getInstance().draw();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static Method[] getMethods(Class<GuiIngame> clazz, Class<?> ... args) {
        ArrayList<Method> methods = new ArrayList<Method>();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; ++i) {
            Method m = declaredMethods[i];
            if (!Arrays.equals(m.getParameterTypes(), args)) continue;
            methods.add(m);
        }
        return methods.toArray(new Method[0]);
    }

    public static void drawRect(float x, float y, float x1, float y1, int color) {
        RenderHelper1.enableGL2D();
        RenderHelper1.setColor(color);
        RenderHelper1.drawRect(x, y, x1, y1);
        RenderHelper1.disableGL2D();
    }

    public static void enableGL2D() {
        GL11.glDisable((int)2929);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
    }

    public static void disableGL2D() {
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glHint((int)3154, (int)4352);
        GL11.glHint((int)3155, (int)4352);
    }

    public static void drawRect(double g, double h, double i, double j) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glPushMatrix();
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)i, (double)h);
        GL11.glVertex2d((double)g, (double)h);
        GL11.glVertex2d((double)g, (double)j);
        GL11.glVertex2d((double)i, (double)j);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    public static int setColor(int colorHex) {
        float alpha = (float)(colorHex >> 24 & 0xFF) / 255.0f;
        float red = (float)(colorHex >> 16 & 0xFF) / 255.0f;
        float green = (float)(colorHex >> 8 & 0xFF) / 255.0f;
        float blue = (float)(colorHex & 0xFF) / 255.0f;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)(alpha == 0.0f ? 1.0f : alpha));
        return colorHex;
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
        GL11.glColor4f((float)f, (float)f6, (float)f7, (float)f4);
        GL11.glVertex2d((double)right, (double)bottom);
        GL11.glVertex2d((double)right, (double)top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
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

    public static void drawRect1(float paramXStart, float paramYStart, float paramXEnd, float paramYEnd, int paramColor) {
        float alpha = (float)(paramColor >> 24 & 0xFF) / 255.0f;
        float red = (float)(paramColor >> 16 & 0xFF) / 255.0f;
        float green = (float)(paramColor >> 8 & 0xFF) / 255.0f;
        float blue = (float)(paramColor & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)paramXEnd, (double)paramYStart);
        GL11.glVertex2d((double)paramXStart, (double)paramYStart);
        GL11.glVertex2d((double)paramXStart, (double)paramYEnd);
        GL11.glVertex2d((double)paramXEnd, (double)paramYEnd);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
    }

    public static void drawRoundedRect2(int xCoord, int yCoord, int xSize, int ySize, int colour) {
        int width = xCoord + xSize;
        int height = yCoord + ySize;
        RenderHelper1.drawRect(xCoord + 1, yCoord, width - 1, height, colour);
        RenderHelper1.drawRect(xCoord, yCoord + 1, width, height - 1, colour);
    }

    public static boolean isInViewFrustrum(Entity entity) {
        return RenderHelper1.isInViewFrustrum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
    }

    private static boolean isInViewFrustrum(AxisAlignedBB bb) {
        Entity current = Minecraft.getMinecraft().getRenderViewEntity();
        frustrum.setPosition(current.posX, current.posY, current.posZ);
        return frustrum.isBoundingBoxInFrustum(bb);
    }

    public static double interpolate(double current, double old, double scale) {
        return old + (current - old) * scale;
    }

    public static void drawRoundedRect1(double x, double y, double width, double height, double radius, int color) {
        int i;
        double x1 = x + width;
        double y1 = y + height;
        float f = (float)(color >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(color & 0xFF) / 255.0f;
        GL11.glPushAttrib((int)0);
        GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
        x *= 2.0;
        y *= 2.0;
        x1 *= 2.0;
        y1 *= 2.0;
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glEnable((int)2848);
        GL11.glBegin((int)9);
        for (i = 0; i <= 90; i += 3) {
            GL11.glVertex2d((double)(x + radius + Math.sin((double)i * Math.PI / 180.0) * (radius * -1.0)), (double)(y + radius + Math.cos((double)i * Math.PI / 180.0) * (radius * -1.0)));
        }
        for (i = 90; i <= 180; i += 3) {
            GL11.glVertex2d((double)(x + radius + Math.sin((double)i * Math.PI / 180.0) * (radius * -1.0)), (double)(y1 - radius + Math.cos((double)i * Math.PI / 180.0) * (radius * -1.0)));
        }
        for (i = 0; i <= 90; i += 3) {
            GL11.glVertex2d((double)(x1 - radius + Math.sin((double)i * Math.PI / 180.0) * radius), (double)(y1 - radius + Math.cos((double)i * Math.PI / 180.0) * radius));
        }
        for (i = 90; i <= 180; i += 3) {
            GL11.glVertex2d((double)(x1 - radius + Math.sin((double)i * Math.PI / 180.0) * radius), (double)(y + radius + Math.cos((double)i * Math.PI / 180.0) * radius));
        }
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glScaled((double)2.0, (double)2.0, (double)2.0);
        GL11.glPopAttrib();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
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

    public static void pre3D() {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glShadeModel((int)7425);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2848);
        GL11.glDisable((int)2929);
        GL11.glDisable((int)2896);
        GL11.glDepthMask((boolean)false);
        GL11.glHint((int)3154, (int)4354);
    }

    public static void post3D() {
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static Color effect(long offset, float brightness, int speed) {
        float hue = (float)(System.nanoTime() + offset * (long)speed) / 1.0E10f % 1.0f;
        long color = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, brightness, 1.0f)), 16);
        Color c = new Color((int)color);
        return new Color((float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f, (float)c.getAlpha() / 255.0f);
    }

    public static void drawOutlinedBoundingBox(AxisAlignedBB aa) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldRenderer = tessellator.getBuffer();
        worldRenderer.begin(3, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(3, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(1, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        tessellator.draw();
    }

    public static void drawFilledBox(AxisAlignedBB axisAlignedBB) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbufferer = tessellator.getBuffer();
        vertexbufferer.begin(7, DefaultVertexFormats.POSITION);
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        tessellator.draw();
        vertexbufferer.begin(7, DefaultVertexFormats.POSITION);
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        tessellator.draw();
        vertexbufferer.begin(7, DefaultVertexFormats.POSITION);
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        vertexbufferer.begin(7, DefaultVertexFormats.POSITION);
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        vertexbufferer.begin(7, DefaultVertexFormats.POSITION);
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        vertexbufferer.begin(7, DefaultVertexFormats.POSITION);
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        vertexbufferer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        tessellator.draw();
    }

    public static void drawOutlinedBox(AxisAlignedBB boundingBox) {
        if (boundingBox == null) {
            return;
        }
        GL11.glBegin((int)3);
        GL11.glVertex3d((double)boundingBox.minX, (double)boundingBox.minY, (double)boundingBox.minZ);
        GL11.glVertex3d((double)boundingBox.maxX, (double)boundingBox.minY, (double)boundingBox.minZ);
        GL11.glVertex3d((double)boundingBox.maxX, (double)boundingBox.minY, (double)boundingBox.maxZ);
        GL11.glVertex3d((double)boundingBox.minX, (double)boundingBox.minY, (double)boundingBox.maxZ);
        GL11.glVertex3d((double)boundingBox.minX, (double)boundingBox.minY, (double)boundingBox.minZ);
        GL11.glEnd();
        GL11.glBegin((int)3);
        GL11.glVertex3d((double)boundingBox.minX, (double)boundingBox.maxY, (double)boundingBox.minZ);
        GL11.glVertex3d((double)boundingBox.maxX, (double)boundingBox.maxY, (double)boundingBox.minZ);
        GL11.glVertex3d((double)boundingBox.maxX, (double)boundingBox.maxY, (double)boundingBox.maxZ);
        GL11.glVertex3d((double)boundingBox.minX, (double)boundingBox.maxY, (double)boundingBox.maxZ);
        GL11.glVertex3d((double)boundingBox.minX, (double)boundingBox.maxY, (double)boundingBox.minZ);
        GL11.glEnd();
        GL11.glBegin((int)1);
        GL11.glVertex3d((double)boundingBox.minX, (double)boundingBox.minY, (double)boundingBox.minZ);
        GL11.glVertex3d((double)boundingBox.minX, (double)boundingBox.maxY, (double)boundingBox.minZ);
        GL11.glVertex3d((double)boundingBox.maxX, (double)boundingBox.minY, (double)boundingBox.minZ);
        GL11.glVertex3d((double)boundingBox.maxX, (double)boundingBox.maxY, (double)boundingBox.minZ);
        GL11.glVertex3d((double)boundingBox.maxX, (double)boundingBox.minY, (double)boundingBox.maxZ);
        GL11.glVertex3d((double)boundingBox.maxX, (double)boundingBox.maxY, (double)boundingBox.maxZ);
        GL11.glVertex3d((double)boundingBox.minX, (double)boundingBox.minY, (double)boundingBox.maxZ);
        GL11.glVertex3d((double)boundingBox.minX, (double)boundingBox.maxY, (double)boundingBox.maxZ);
        GL11.glEnd();
    }

    public static void startSmooth() {
        GL11.glEnable((int)2848);
        GL11.glEnable((int)2881);
        GL11.glEnable((int)2832);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
        GL11.glHint((int)3153, (int)4354);
    }

    public static void endSmooth() {
        GL11.glDisable((int)2848);
        GL11.glDisable((int)2881);
        GL11.glEnable((int)2832);
    }

    public static void renderItem(ItemStack itemStack, int x, int y) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.enableDepth();
        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, x, y);
        mc.getRenderItem().renderItemOverlays(RenderHelper1.mc.fontRenderer, itemStack, x, y);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.disableDepth();
    }

    public static void enableGUIStandardItemLighting() {
        GlStateManager.pushMatrix();
        GlStateManager.rotate((float)-30.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)165.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        RenderHelper1.enableStandardItemLighting();
        GlStateManager.popMatrix();
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

    public static void disableStandardItemLighting() {
        GlStateManager.disableLighting();
        GlStateManager.disableLight((int)0);
        GlStateManager.disableLight((int)1);
        GlStateManager.disableColorMaterial();
    }

    public static void enableStandardItemLighting() {
        GlStateManager.enableLighting();
        GlStateManager.enableLight((int)0);
        GlStateManager.enableLight((int)1);
        GlStateManager.enableColorMaterial();
        GlStateManager.colorMaterial((int)1032, (int)5634);
        GlStateManager.glLight((int)16384, (int)4611, (FloatBuffer)RenderHelper1.setColorBuffer(RenderHelper1.LIGHT0_POS.x, RenderHelper1.LIGHT0_POS.y, RenderHelper1.LIGHT0_POS.z, 0.0));
        float f = 0.6f;
        GlStateManager.glLight((int)16384, (int)4609, (FloatBuffer)RenderHelper1.setColorBuffer(0.6f, 0.6f, 0.6f, 1.0f));
        GlStateManager.glLight((int)16384, (int)4608, (FloatBuffer)RenderHelper1.setColorBuffer(0.0f, 0.0f, 0.0f, 1.0f));
        GlStateManager.glLight((int)16384, (int)4610, (FloatBuffer)RenderHelper1.setColorBuffer(0.0f, 0.0f, 0.0f, 1.0f));
        GlStateManager.glLight((int)16385, (int)4611, (FloatBuffer)RenderHelper1.setColorBuffer(RenderHelper1.LIGHT1_POS.x, RenderHelper1.LIGHT1_POS.y, RenderHelper1.LIGHT1_POS.z, 0.0));
        GlStateManager.glLight((int)16385, (int)4609, (FloatBuffer)RenderHelper1.setColorBuffer(0.6f, 0.6f, 0.6f, 1.0f));
        GlStateManager.glLight((int)16385, (int)4608, (FloatBuffer)RenderHelper1.setColorBuffer(0.0f, 0.0f, 0.0f, 1.0f));
        GlStateManager.glLight((int)16385, (int)4610, (FloatBuffer)RenderHelper1.setColorBuffer(0.0f, 0.0f, 0.0f, 1.0f));
        GlStateManager.shadeModel((int)7424);
        float f1 = 0.4f;
        GlStateManager.glLightModel((int)2899, (FloatBuffer)RenderHelper1.setColorBuffer(0.4f, 0.4f, 0.4f, 1.0f));
    }

    private static FloatBuffer setColorBuffer(double p_74517_0_, double p_74517_2_, double p_74517_4_, double p_74517_6_) {
        return RenderHelper1.setColorBuffer((float)p_74517_0_, (float)p_74517_2_, (float)p_74517_4_, (float)p_74517_6_);
    }

    public static void drawOutline(AxisAlignedBB axisAlignedBB, float width, Color color) {
        GL11.glPushMatrix();
        GL11.glLineWidth((float)width);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        buffer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        buffer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(color.getRed(), color.getGreen(), color.getBlue(), 100).endVertex();
        tessellator.draw();
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glPopMatrix();
    }

    public static FloatBuffer setColorBuffer(float p_74521_0_, float p_74521_1_, float p_74521_2_, float p_74521_3_) {
        COLOR_BUFFER.clear();
        COLOR_BUFFER.put(p_74521_0_).put(p_74521_1_).put(p_74521_2_).put(p_74521_3_);
        COLOR_BUFFER.flip();
        return COLOR_BUFFER;
    }

    static {
        mc = Minecraft.getMinecraft();
        frustrum = new Frustum();
        COLOR_BUFFER = GLAllocation.createDirectFloatBuffer((int)4);
        LIGHT0_POS = new Vec3d((double)0.2f, 1.0, (double)-0.7f).normalize();
        LIGHT1_POS = new Vec3d((double)-0.2f, 1.0, (double)0.7f).normalize();
    }
}

