//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 */
package new_gui.buttons.setting.settings;

import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import ru.aizensense.AizenSense;
import ru.aizensense.settings.Setting;

public class CSSettingComboValue {
    public int x;
    public int y;
    public int width;
    public int height;
    public String displayString;
    public Minecraft mc = Minecraft.getMinecraft();
    public FontRenderer fr;
    public ArrayList<String> values;
    public Setting set;

    public CSSettingComboValue(int x, int y, int width, int height, Setting s, String displayString) {
        this.fr = this.mc.fontRenderer;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.displayString = displayString;
        this.set = s;
        this.values = s.getOptions();
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (this.isHovered(mouseX, mouseY)) {
            this.set.setValString(this.displayString);
        }
    }

    public void drawScreen(int mouseX, int mouseY) {
        Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), (int)Integer.MIN_VALUE);
        Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), (int)Integer.MIN_VALUE);
        int color = this.set.getValString().equalsIgnoreCase(this.displayString) ? AizenSense.getClientColor().getRGB() : Integer.MAX_VALUE;
        this.fr.drawString(this.displayString, this.x + this.width / 2 - this.fr.getStringWidth(this.displayString) / 2, this.y + 1, color);
    }

    public boolean isHovered(int mouseX, int mouseY) {
        boolean hoveredx = mouseX > this.x && mouseX < this.x + this.width;
        boolean hoveredy = mouseY > this.y && mouseY < this.y + this.height;
        return hoveredx && hoveredy;
    }
}

