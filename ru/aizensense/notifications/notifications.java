//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  org.lwjgl.opengl.GL11
 */
package ru.aizensense.notifications;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import ru.aizensense.AizenSense;
import ru.aizensense.notifications.Type;
import ru.aizensense.utils.RenderUtil;

public class notifications {
    public static List<String> Names = new ArrayList<String>();
    public static List<String> Tests = new ArrayList<String>();
    public static List<Type> Types = new ArrayList<Type>();
    public static List<Integer> Times = new ArrayList<Integer>();
    private static float height = 5.0f;
    private static float width = 65.0f;

    public static void add(String main_input, String text_input, Type type_input) {
        Names.add(main_input);
        Tests.add(text_input);
        Types.add(type_input);
        Times.add(0);
    }

    public static void drawnotif(int i, String name, String text, Type type, int time) {
        GlStateManager.enableTexture2D();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        GL11.glPushMatrix();
        if (time >= 70) {
            GL11.glTranslated((double)(sr.getScaledWidth() - 70), (double)((double)sr.getScaledHeight() - (double)time * 1.5 + 15.0), (double)0.0);
        } else {
            GL11.glTranslated((double)(sr.getScaledWidth() - time), (double)((double)sr.getScaledHeight() - (double)time * 1.5 + 15.0), (double)0.0);
        }
        int alpha = (int)(230.0 - ((double)time - 7.6));
        if (alpha >= 0) {
            if (type == Type.Green) {
                RenderUtil.drawSmoothRect(0.0f, 0.0f, width, height, new Color(0, 0, 0, alpha).getRGB());
                RenderUtil.drawSmoothRect(0.0f, 0.0f, 4.0f, height, new Color(30, 200, 0, alpha).getRGB());
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(name, 10.0f, 2.0f, AizenSense.getClientColor().getRGB());
                Minecraft.getMinecraft().fontRenderer.drawString(text, 10, 4 + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT, new Color(255, 255, 255, alpha).getRGB());
            } else if (type == Type.Red) {
                RenderUtil.drawSmoothRect(0.0f, 0.0f, width, height, new Color(0, 0, 0, alpha).getRGB());
                RenderUtil.drawSmoothRect(0.0f, 0.0f, 4.0f, height, new Color(200, 30, 0, alpha).getRGB());
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(name, 10.0f, 2.0f, AizenSense.getClientColor().getRGB());
                Minecraft.getMinecraft().fontRenderer.drawString(text, 10, 4 + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT, new Color(255, 255, 255, alpha).getRGB());
            } else if (type == Type.OK) {
                RenderUtil.drawSmoothRect(0.0f, 0.0f, width, height, new Color(35, 35, 40, alpha).getRGB());
                RenderUtil.drawSmoothRect(0.0f, 0.0f, 4.0f, height, new Color(51, 255, 0, alpha).getRGB());
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(name, 10.0f, 2.0f, AizenSense.getClientColor().getRGB());
                Minecraft.getMinecraft().fontRenderer.drawString(text, 10, 4 + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT, new Color(255, 255, 255, alpha).getRGB());
            }
        }
        GL11.glPopMatrix();
        GlStateManager.disableTexture2D();
    }

    public static void show() {
        int i;
        for (i = 0; i < Names.size(); ++i) {
            if (Times.get(i) != 230) {
                Times.set(i, Times.get(i) + 1);
                continue;
            }
            Names.remove(i);
            Tests.remove(i);
            Types.remove(i);
            Times.remove(i);
        }
        height = 22.0f;
        for (i = 0; i < Names.size(); ++i) {
            String name = Names.get(i);
            String text = Tests.get(i);
            Type type = Types.get(i);
            int time = Times.get(i);
            notifications.drawnotif(i, name, text, type, time);
        }
    }
}

