//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.math.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package clickgui.elements.menu;

import clickgui.elements.Element;
import clickgui.elements.ModuleButton;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import ru.aizensense.settings.Setting;
import ru.aizensense.utils.MathUtils;
import ru.aizensense.utils.RenderUtil;

public class ElementSlider
extends Element {
    public boolean dragging;

    public ElementSlider(ModuleButton iparent, Setting iset) {
        this.parent = iparent;
        this.set = iset;
        this.dragging = false;
    }

    @Override
    public void tick() {
        double d0 = (this.set.getValDouble() - this.set.getMin()) / (this.set.getMax() - this.set.getMin());
        if (d0 < 0.0) {
            d0 = 0.0;
        }
        this.anim = (float)((double)this.anim + (d0 - (double)this.anim) / 4.0);
        double d1 = this.set.onlyInt() ? 1000.0 : 100.0;
        this.anim2 = (float)((double)this.anim2 + ((double)Math.round(this.set.getValDouble() * d1) / d1 - (double)this.anim2) / 2.0);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        double d0 = this.set.onlyInt() ? (double)((int)Math.round((double)this.anim2 * 1000.0)) / 1000.0 : (double)Math.round((double)this.anim2 * 100.0) / 100.0;
        String s = "";
        s = this.set.onlyInt() ? "" + (int)d0 : "" + MathUtils.getNormalDouble(d0, 1);
        boolean flag = this.isSliderHovered(mouseX, mouseY) || this.dragging;
        Color color = new Color(-13350562);
        int i = new Color(color.getRed(), color.getGreen(), color.getBlue(), flag ? 250 : 200).getRGB();
        int j = new Color(color.getRed(), color.getGreen(), color.getBlue(), flag ? 255 : 230).getRGB();
        double d3 = (this.set.getValDouble() - this.set.getMin()) / (this.set.getMax() - this.set.getMin());
        RenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -2039584);
        RenderUtil.drawGradientSideways(this.x, this.y, this.x + this.width - 100.0, this.y + this.height, -5460820, 0xE0E0E0);
        Minecraft.getMinecraft().fontRenderer.drawString(this.setstrg + " (" + s + ")", (int)(this.x + 8.0), (int)(this.y + 3.0), -12105913);
        RenderUtil.drawRect(this.x + 8.0, this.y + 11.0, this.x + 8.0 + (this.width - 16.0), this.y + 12.5, -15724528);
        RenderUtil.drawRect(this.x + 8.0, this.y + 11.0, (float)(this.x + 11.0 + (double)this.anim * (this.width - 21.0)), this.y + 12.5, -15558688);
        GL11.glPushMatrix();
        RenderUtil.drawBorderedCircle((float)(this.x + 11.0 + (double)this.anim * (this.width - 21.0)), (float)(this.y + 12.0), 3.0f, 1, -15558688, -15558688);
        GL11.glPopMatrix();
        if (this.dragging) {
            double d1 = this.set.getMax() - this.set.getMin();
            double d2 = this.set.getMin() + MathHelper.clamp((double)(((double)mouseX - (this.x + 8.0)) / (this.width - 16.0)), (double)0.0, (double)1.0) * d1;
            this.set.setValDouble(d2);
        }
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && this.isSliderHovered(mouseX, mouseY)) {
            this.dragging = true;
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        this.dragging = false;
    }

    public boolean isSliderHovered(int mouseX, int mouseY) {
        return (double)mouseX >= this.x + 6.0 && (double)mouseX <= this.x + 8.0 + (this.width - 13.0) && (double)mouseY >= this.y + 9.0 && (double)mouseY <= this.y + 15.0;
    }
}

