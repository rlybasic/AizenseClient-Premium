//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 */
package new_gui.buttons;

import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import new_gui.buttons.CSButton;
import new_gui.buttons.setting.CSSetting;
import new_gui.buttons.setting.settings.CSSettingCheck;
import new_gui.buttons.setting.settings.CSSettingCombo;
import new_gui.buttons.setting.settings.CSSettingDouble;
import new_gui.buttons.setting.settings.KeyBind;
import ru.aizensense.AizenSense;
import ru.aizensense.module.Module;
import ru.aizensense.settings.Setting;

public class CSModButton
extends CSButton {
    public static String old_name;
    public Module mod;
    public static boolean first;
    public static boolean binding;
    public ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    public ArrayList<CSSetting> settings = new ArrayList();

    public CSModButton(int x, int y, int width, int height, int color, String displayString, Module mod) {
        super(x, y, width, height, color, displayString);
        this.mod = mod;
        this.initSettings();
    }

    private void initSettings() {
        int y = 110;
        int x = this.x + 100;
        for (int i = 0; i < AizenSense.instance.settingsManager.getSettings().size(); ++i) {
            Setting s = AizenSense.instance.settingsManager.getSettings().get(i);
            if (s.getParentMod() != this.mod) continue;
            if (s.isCheck()) {
                CSSettingCheck check = new CSSettingCheck(x, y, y, x, s);
                this.settings.add(check);
                y += 13;
            }
            if (s.isSlider()) {
                CSSettingDouble doubleset = new CSSettingDouble(x, y, 0, 0, s);
                this.settings.add(doubleset);
                y += 15;
            }
            if (s.isCombo()) {
                int yplus = y;
                CSSettingCombo combo = new CSSettingCombo(x, y, 70, this.mc.fontRenderer.FONT_HEIGHT + 2, s);
                this.settings.add(combo);
                for (int i1 = 0; i1 < s.getOptions().size(); ++i1) {
                    if ((y += this.fr.FONT_HEIGHT + 2) <= 100 + this.sr.getScaledWidth() - 220) continue;
                    y = 0;
                    x += this.mc.fontRenderer.getStringWidth(s.getName()) + 50;
                }
                y += this.fr.FONT_HEIGHT + 5;
            }
            if (y <= 100 + this.sr.getScaledWidth() - 220) continue;
            y = 0;
            x += this.mc.fontRenderer.getStringWidth(s.getName()) + 50;
        }
        KeyBind key = new KeyBind(x, y, 70, this.mc.fontRenderer.FONT_HEIGHT + 2, this.mod);
        this.settings.add(key);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int color;
        int n = color = this.isHovered(mouseX, mouseY) ? AizenSense.getClientColor().getRGB() : -1;
        if (this.mod.isToggled()) {
            color = AizenSense.getClientColor().darker().getRGB();
        }
        if (this.isCurrentMod()) {
            color = AizenSense.getClientColor().getRGB();
        }
        this.fr.drawString(this.displayString, this.x, this.y, color);
        for (CSSetting cs : this.settings) {
            if (!this.isCurrentMod()) continue;
            cs.drawScreen(mouseX, mouseY, partialTicks);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void setBinding(boolean binding) {
        CSModButton.binding = binding;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        block5: {
            block6: {
                if (!this.isHovered(mouseX, mouseY)) break block5;
                if (mouseButton != 0 || !this.isHovered(mouseX, mouseY)) break block6;
                if (AizenSense.csgui.currentCategory == null) break block6;
                if (AizenSense.csgui.currentCategory.category != this.mod.getCategory()) break block6;
                this.mod.toggle();
                break block5;
            }
            if (mouseButton != 2 || !this.isHovered(mouseX, mouseY)) ** GOTO lbl-1000
            if (AizenSense.csgui.currentCategory == null) ** GOTO lbl-1000
            if (AizenSense.csgui.currentCategory.category == this.mod.getCategory()) {
                this.setBinding(true);
            } else if (mouseButton == 1) {
                // empty if block
            }
        }
        var4_4 = this.settings.iterator();
        while (true) {
            if (!var4_4.hasNext()) {
                super.mouseClicked(mouseX, mouseY, mouseButton);
                return;
            }
            cs = var4_4.next();
            if (!this.isCurrentMod()) continue;
            cs.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    public boolean isHovered(int mouseX, int mouseY) {
        boolean hoveredx = mouseX > this.x && mouseX < this.x + this.width;
        boolean hoveredy = mouseY > this.y && mouseY < this.y + this.height;
        return hoveredx && hoveredy;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean isCurrentMod() {
        if (AizenSense.csgui.currentCategory == null) return false;
        if (AizenSense.csgui.currentCategory.currentMod == null) return false;
        if (AizenSense.csgui.currentCategory.currentMod != this) return false;
        return true;
    }

    @Override
    public void initButton() {
        this.initSettings();
        super.initButton();
    }

    static {
        first = false;
    }
}

