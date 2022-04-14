//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package new_gui.buttons.setting.settings;

import java.io.IOException;
import new_gui.buttons.setting.CSSetting;
import org.lwjgl.input.Keyboard;
import ru.aizensense.AizenSense;
import ru.aizensense.settings.Setting;

public class CSSettingDouble
extends CSSetting {
    public CSSettingDouble(int x, int y, int width, int height, Setting s) {
        super(x, y, width, height, s);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        double reach = this.set.getValDouble();
        double reach1 = reach * 100.0;
        double reach2 = Math.round(reach1);
        double round = reach2 / 100.0;
        this.mc.fontRenderer.drawString("<", this.x + 1, this.y + 1, this.isHoveredLeft(mouseX, mouseY) ? AizenSense.getClientColor().getRGB() : Integer.MAX_VALUE);
        this.mc.fontRenderer.drawString(">", this.x + 1 + this.fr.getStringWidth(this.set.getName() + " " + round) + 15, this.y + 1, this.isHoveredRight(mouseX, mouseY) ? AizenSense.getClientColor().getRGB() : Integer.MAX_VALUE);
        this.mc.fontRenderer.drawString(this.set.getName() + " " + round, this.x + 10, this.y + 1, Integer.MAX_VALUE);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public boolean isHoveredLeft(int mouseX, int mouseY) {
        boolean hoveredx = mouseX > this.x + 1 && mouseX < this.x + 1 + 5;
        boolean hoveredy = mouseY > this.y + 1 && mouseY < this.y + this.mc.fontRenderer.FONT_HEIGHT;
        return hoveredx && hoveredy;
    }

    public boolean isHoveredRight(int mouseX, int mouseY) {
        double round = Math.round(this.set.getValDouble() * 10.0) / 10L;
        boolean hoveredx = mouseX > this.x + 1 + this.fr.getStringWidth(this.set.getName() + " " + round) + 15 && mouseX < this.x + 1 + this.fr.getStringWidth(this.set.getName() + " " + round) + 20;
        boolean hoveredy = mouseY > this.y + 1 && mouseY < this.y + this.mc.fontRenderer.FONT_HEIGHT;
        return hoveredx && hoveredy;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        Setting s = this.set;
        if (mouseButton == 0) {
            if (this.isHoveredLeft(mouseX, mouseY)) {
                boolean more = Keyboard.isKeyDown((int)42);
                double plus = 0.0;
                if (!Keyboard.isKeyDown((int)42) && !Keyboard.isKeyDown((int)29)) {
                    plus = s.onlyInt() ? 1.0 : 0.1;
                } else if (Keyboard.isKeyDown((int)42) && !Keyboard.isKeyDown((int)29)) {
                    plus = s.onlyInt() ? 10.0 : 1.0;
                } else if (Keyboard.isKeyDown((int)42) && Keyboard.isKeyDown((int)29)) {
                    plus = s.onlyInt() ? 1.0 : 0.01;
                } else if (!Keyboard.isKeyDown((int)42) && Keyboard.isKeyDown((int)29)) {
                    plus = 0.0;
                    s.setValDouble(s.getMin());
                }
                if (s.getValDouble() - plus < s.getMin() || s.getValDouble() - plus == s.getMin()) {
                    s.setValDouble(s.getMin());
                } else if (s.getValDouble() - plus > s.getMin()) {
                    s.setValDouble(s.getValDouble() - plus);
                }
                if (Keyboard.isKeyDown((int)29)) {
                    s.setValDouble(s.getMin());
                }
            } else if (this.isHoveredRight(mouseX, mouseY)) {
                double plus = 0.0;
                if (!Keyboard.isKeyDown((int)42) && !Keyboard.isKeyDown((int)29)) {
                    plus = s.onlyInt() ? 1.0 : 0.1;
                } else if (Keyboard.isKeyDown((int)42) && !Keyboard.isKeyDown((int)29)) {
                    plus = s.onlyInt() ? 10.0 : 1.0;
                } else if (Keyboard.isKeyDown((int)42) && Keyboard.isKeyDown((int)29)) {
                    plus = s.onlyInt() ? 1.0 : 0.01;
                } else if (!Keyboard.isKeyDown((int)42) && Keyboard.isKeyDown((int)29)) {
                    plus = 0.0;
                    s.setValDouble(s.getMax());
                }
                if (s.getValDouble() + plus > s.getMax() || s.getValDouble() + plus == s.getMax()) {
                    s.setValDouble(s.getMax());
                } else if (s.getValDouble() + plus < s.getMax()) {
                    s.setValDouble(s.getValDouble() + plus);
                }
            }
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}

