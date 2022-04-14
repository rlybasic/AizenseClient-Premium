//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package clickgui.elements;

import clickgui.ClickGUI;
import clickgui.elements.ModuleButton;
import net.minecraft.client.Minecraft;
import ru.aizensense.settings.Setting;

public class Element {
    public ClickGUI clickgui;
    public ModuleButton parent;
    public Setting set;
    public double offset;
    public double x;
    public double y;
    public double width;
    public double height;
    public float anim;
    public float anim2;
    public String setstrg;
    public boolean comboextended;

    public void setup() {
        this.clickgui = this.parent.parent.clickgui;
        this.anim = 0.0f;
        this.anim2 = 0.0f;
    }

    public void tick() {
    }

    public void update() {
        this.x = this.parent.x + this.parent.width + 2.0;
        this.y = this.parent.y + this.offset;
        this.width = this.parent.width;
        this.height = 16.0;
        String s = this.set.getName();
        if (this.set.isCheck()) {
            this.setstrg = s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
            double d0 = this.x + this.width - (double)Minecraft.getMinecraft().fontRenderer.getStringWidth(this.setstrg);
            if (d0 < this.x + 13.0) {
                this.width += this.x + 13.0 - d0 + 1.0;
            }
        } else if (this.set.isCombo()) {
            this.height = this.comboextended ? (double)(this.set.getOptions().size() * (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 3)) + this.height : this.height;
            this.setstrg = s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
            int j = Minecraft.getMinecraft().fontRenderer.getStringWidth(this.setstrg);
            for (String s1 : this.set.getOptions()) {
                int i = Minecraft.getMinecraft().fontRenderer.getStringWidth(s1);
                if (i <= j) continue;
                j = i;
            }
            double d1 = this.x + this.width - (double)j;
            if (d1 < this.x) {
                this.width += this.x - d1 + 1.0;
            }
        } else if (this.set.isSlider()) {
            this.setstrg = s.substring(0, 1).toUpperCase() + s.substring(1, s.length());
            String s2 = "" + (double)Math.round(this.set.getValDouble() * 100.0) / 100.0;
            String s3 = "" + (double)Math.round(this.set.getMax() * 100.0) / 100.0;
            double d2 = this.x + this.width - (double)Minecraft.getMinecraft().fontRenderer.getStringWidth(this.setstrg) - (double)Minecraft.getMinecraft().fontRenderer.getStringWidth(s3) - 4.0;
            if (d2 < this.x) {
                this.width += this.x - d2 + 1.0;
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        return this.isHovered(mouseX, mouseY);
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return (double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= this.y && (double)mouseY <= this.y + this.height;
    }
}

