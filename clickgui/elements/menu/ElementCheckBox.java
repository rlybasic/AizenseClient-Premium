//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  org.lwjgl.opengl.GL11
 */
package clickgui.elements.menu;

import clickgui.elements.Element;
import clickgui.elements.ModuleButton;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import ru.aizensense.settings.Setting;
import ru.aizensense.utils.RenderUtil;

public class ElementCheckBox
extends Element {
    public ElementCheckBox(ModuleButton iparent, Setting iset) {
        this.parent = iparent;
        this.set = iset;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Color color = new Color(-13350562);
        int i = new Color(color.getRed(), color.getGreen(), color.getBlue(), 200).getRGB();
        RenderUtil.drawRect((float)this.x, (float)this.y, (float)this.x + (float)this.width, (float)this.y + (float)this.height, -2039584);
        RenderUtil.drawGradientSideways(this.x, this.y, this.x + this.width - 100.0, this.y + this.height, -5460820, 0xE0E0E0);
        GL11.glPushMatrix();
        RenderUtil.setColor(new Color(-12105913));
        int j = (int)(this.x + 28.0);
        int k = (int)(this.y + 5.5);
        Minecraft.getMinecraft().fontRenderer.drawString(this.setstrg, j, k, -12105913);
        GL11.glPopMatrix();
        RenderUtil.drawBorderedRect((float)this.x + 11.2f, (float)this.y + 7.0f, (float)this.x + 17.0f, (float)this.y + 11.0f, 1.0f, 0x25C5C5C5, this.anim < 4.5f ? -10197916 : -15558688);
        GL11.glPushMatrix();
        RenderUtil.drawBorderedCircle((int)this.x + 11, (int)this.y + 9, 2.0f, 1, 0x25C5C5C5, -15558688);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        RenderUtil.drawBorderedCircle((int)this.x + 17, (int)this.y + 9, 2.0f, 1, 0x25C5C5C5, -10197916);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        if (this.anim > 0.0f) {
            GL11.glPushMatrix();
            RenderUtil.drawBorderedCircle((float)this.x + 11.0f + this.anim, (float)this.y + 9.0f, 3.0f, 1, -4408132, -4342339);
            GL11.glPopMatrix();
            RenderUtil.drawCircle228((float)this.x + 11.0f + this.anim, (float)this.y + 9.0f, 3.0f, 5, -15558688, -15558688, (int)(this.anim * 60.0f));
        } else {
            RenderUtil.drawBorderedCircle((float)this.x + 11.0f + this.anim, (float)this.y + 9.0f, 3.0f, 1, -4408132, -4342339);
        }
        GL11.glPopMatrix();
    }

    @Override
    public void tick() {
        this.anim = this.set.getValBoolean() ? (float)((double)this.anim + 0.55) : (float)((double)this.anim - 0.55);
        if (this.anim < 0.0f) {
            this.anim = 0.0f;
        }
        if (this.anim > 6.0f) {
            this.anim = 6.0f;
        }
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && this.isCheckHovered(mouseX, mouseY)) {
            this.set.setValBoolean(!this.set.getValBoolean());
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public boolean isCheckHovered(int mouseX, int mouseY) {
        return (double)mouseX >= this.x + 7.5 && (double)mouseX <= this.x + 21.0 && (double)mouseY >= this.y + 7.0 && (double)mouseY <= this.y + 14.5;
    }
}

