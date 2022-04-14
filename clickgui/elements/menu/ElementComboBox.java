//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package clickgui.elements.menu;

import clickgui.elements.Element;
import clickgui.elements.ModuleButton;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import ru.aizensense.settings.Setting;
import ru.aizensense.utils.RenderUtil;

public class ElementComboBox
extends Element {
    public ElementComboBox(ModuleButton iparent, Setting iset) {
        this.parent = iparent;
        this.set = iset;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Color color = new Color(-13350562);
        int i = new Color(color.getRed(), color.getGreen(), color.getBlue(), 150).getRGB();
        RenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -2039584);
        RenderUtil.drawGradientSideways(this.x, this.y, this.x + this.width - 100.0, this.y + this.height, -5460820, 0xE0E0E0);
        RenderUtil.drawOctagon((float)this.x + 8.0f, (float)this.y + 1.0f, (float)this.width - 16.0f, 14.0f, 1.0f, 6.0f, -16746560);
        if (this.comboextended) {
            RenderUtil.drawRect(this.x + 12.0, this.y + 15.0, this.x + this.width - 12.0, this.y + this.height, -2236963);
        }
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.setstrg, (float)((int)(this.x + this.width / 2.0 - (double)(Minecraft.getMinecraft().fontRenderer.getStringWidth(this.setstrg) / 2))), (float)((int)(this.y + 4.0)), -1);
        int j = color.getRGB();
        if (this.comboextended) {
            double d0 = this.y + 16.0;
            for (String s : this.set.getOptions()) {
                String s1 = s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
                Minecraft.getMinecraft().fontRenderer.drawString(s1, (int)(this.x + this.width / 2.0 - (double)(Minecraft.getMinecraft().fontRenderer.getStringWidth(s1) / 2)), (int)(d0 + 1.0), s.equalsIgnoreCase(this.set.getValString()) ? -16746560 : -12105913);
                if (s.equalsIgnoreCase(this.set.getValString())) {
                    // empty if block
                }
                if (!((double)mouseX >= this.x) || !((double)mouseX <= this.x + this.width) || !((double)mouseY >= d0) || (double)mouseY < d0 + (double)Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 2.0) {
                    // empty if block
                }
                d0 += (double)(Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 4);
            }
        }
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0) {
            if (this.isButtonHovered(mouseX, mouseY)) {
                this.comboextended = !this.comboextended;
                return true;
            }
            if (!this.comboextended) {
                return false;
            }
            double d0 = this.y + 16.0;
            for (String s : this.set.getOptions()) {
                if ((double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= d0 && (double)mouseY <= d0 + (double)Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 2.0) {
                    if (this.clickgui != null && this.clickgui.setmgr != null) {
                        this.clickgui.setmgr.getSettingByName(this.set.getName()).setValString(s.toLowerCase());
                    }
                    return true;
                }
                d0 += (double)(Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 6);
            }
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public boolean isButtonHovered(int mouseX, int mouseY) {
        return (double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= this.y && (double)mouseY <= this.y + 15.0;
    }
}

