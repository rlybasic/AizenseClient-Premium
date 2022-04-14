//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  org.lwjgl.opengl.GL11
 */
package clickgui.elements;

import clickgui.Panel;
import clickgui.elements.Element;
import clickgui.elements.menu.ElementCheckBox;
import clickgui.elements.menu.ElementComboBox;
import clickgui.elements.menu.ElementSlider;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import ru.aizensense.AizenSense;
import ru.aizensense.module.Module;
import ru.aizensense.settings.Setting;
import ru.aizensense.utils.RenderUtil;

public class ModuleButton {
    public Module func;
    public ArrayList<Element> menuelements;
    public Panel parent;
    public double x;
    public double y;
    public double width;
    public double height;
    public boolean extended = false;
    public boolean listening = false;

    public ModuleButton(Module ifunc, Panel pl) {
        this.func = ifunc;
        this.height = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 9;
        this.parent = pl;
        this.menuelements = new ArrayList();
        if (AizenSense.instance.settingsManager.getSettingsByMod(ifunc) != null) {
            for (Setting setting : AizenSense.instance.settingsManager.getSettingsByMod(ifunc)) {
                if (setting.isCheck()) {
                    this.menuelements.add(new ElementCheckBox(this, setting));
                    continue;
                }
                if (setting.isSlider()) {
                    this.menuelements.add(new ElementSlider(this, setting));
                    continue;
                }
                if (!setting.isCombo()) continue;
                this.menuelements.add(new ElementComboBox(this, setting));
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Color color = new Color(-13350562);
        int i = new Color(color.getRed(), color.getGreen(), color.getBlue(), 150).getRGB();
        int j = -5263441;
        if (this.func.isToggled()) {
            j = -1052689;
        }
        if (this.isHovered(mouseX, mouseY)) {
            RenderUtil.drawRect(this.x - 2.0, this.y, this.x + this.width + 2.0, this.y + this.height + 1.0, 572466736);
        }
        Minecraft.getMinecraft().fontRenderer.drawString(this.func.getName(), (int)(this.x + 4.0), (int)(this.y - 2.0 + this.height / 2.0), this.func.isToggled() ? -12895429 : -6513508);
        if (AizenSense.instance.settingsManager.getSettingsByMod(this.func) != null) {
            GL11.glPushMatrix();
            int k = (int)(this.x + this.width - 6.0);
            Minecraft.getMinecraft().fontRenderer.drawString(">", k, (int)(this.y - 2.0 + this.height / 2.0), this.func.isToggled() ? -12895429 : -6513508);
            GL11.glPopMatrix();
        }
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (!this.isHovered(mouseX, mouseY)) {
            return false;
        }
        if (mouseButton == 0) {
            this.func.toggle();
        } else if (mouseButton == 1) {
            if (this.menuelements != null && this.menuelements.size() > 0) {
                boolean flag;
                this.extended = flag = !this.extended;
            }
        } else if (mouseButton == 2) {
            this.listening = true;
        }
        return true;
    }

    public boolean keyTyped(char typedChar, int keyCode) throws IOException {
        if (this.listening) {
            if (keyCode != 1) {
                this.func.setKey(keyCode);
            } else {
                this.func.setKey(0);
            }
            this.listening = false;
            return true;
        }
        return false;
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return (double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= this.y && (double)mouseY <= this.y + this.height;
    }
}

