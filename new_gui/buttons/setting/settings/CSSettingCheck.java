//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 */
package new_gui.buttons.setting.settings;

import java.awt.Color;
import java.io.IOException;
import net.minecraft.client.gui.Gui;
import new_gui.buttons.setting.CSSetting;
import new_gui.util.RenderHelper;
import ru.aizensense.AizenSense;
import ru.aizensense.settings.Setting;

public class CSSettingCheck
extends CSSetting {
    private int animation = 20;

    public CSSettingCheck(int x, int y, int width, int height, Setting s) {
        super(x, y, width, height, s);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.fr.drawString(this.displayString, this.x, this.y, Integer.MAX_VALUE);
        int stringwidth = this.fr.getStringWidth(this.displayString);
        Gui.drawRect((int)(this.x + stringwidth + 20), (int)this.y, (int)(this.x + stringwidth + 30), (int)(this.y + 10), (int)Color.black.getRGB());
        RenderHelper.drawFullCircle(this.x + stringwidth + 20, this.y + 5, 5.0, Color.black.getRGB());
        RenderHelper.drawFullCircle(this.x + stringwidth + 30, this.y + 5, 5.0, Color.black.getRGB());
        if (this.set.getValBoolean()) {
            RenderHelper.drawFullCircle(this.x + stringwidth + this.animation, this.y + 5, 5.0, AizenSense.getClientColor().getRGB());
        } else {
            RenderHelper.drawFullCircle(this.x + stringwidth + this.animation, this.y + 5, 5.0, -2);
        }
        if (this.set.getValBoolean()) {
            if (this.animation < 30) {
                ++this.animation;
            }
        } else if (this.animation > 20) {
            --this.animation;
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (this.isHovered(mouseX, mouseY) && mouseButton == 0) {
            this.set.setValBoolean(!this.set.getValBoolean());
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    private boolean isHovered(int mouseX, int mouseY) {
        int stringwidth = this.fr.getStringWidth(this.displayString);
        boolean hoveredx = mouseX > this.x + stringwidth + 15 && mouseX < this.x + stringwidth + 35;
        boolean hoveredy = mouseY > this.y && mouseY < this.y + 10;
        return hoveredx && hoveredy;
    }
}

