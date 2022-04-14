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
import ru.aizensense.module.Module;

public class KeyBind
extends CSSetting {
    private boolean binding;

    public KeyBind(int x, int y, int width, int height, Module s) {
        super(x, y, width, height, s);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    public void setBinding(boolean binding) {
        this.binding = binding;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        String displayString = "KeyBind: " + Keyboard.getKeyName((int)this.mod.getKey());
        if (this.binding) {
            displayString = "Listen... ";
            this.fr.drawString(displayString, this.x, this.y, Integer.MAX_VALUE);
        }
        this.fr.drawString(displayString, this.x, this.y, Integer.MAX_VALUE);
        int stringwidth = this.fr.getStringWidth(displayString);
        if (this.binding && Keyboard.getEventKey() != 0 && Keyboard.getEventKeyState()) {
            this.mod.setKey(Keyboard.getEventKey());
            this.binding = false;
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (this.isHovered(mouseX, mouseY) && mouseButton == 0) {
            this.setBinding(true);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    private boolean isHovered(int mouseX, int mouseY) {
        int stringwidth = this.fr.getStringWidth(this.displayString);
        boolean hoveredx = mouseX > this.x + 15 && mouseX < this.x + stringwidth + 35;
        boolean hoveredy = mouseY > this.y && mouseY < this.y + 10;
        return hoveredx && hoveredy;
    }
}

