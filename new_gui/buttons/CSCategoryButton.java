//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 */
package new_gui.buttons;

import java.io.IOException;
import java.util.ArrayList;
import new_gui.buttons.CSButton;
import new_gui.buttons.CSModButton;
import ru.aizensense.AizenSense;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;

public class CSCategoryButton
extends CSButton {
    public static boolean binding;
    public Category category;
    public CSModButton currentMod;
    public ArrayList<CSModButton> buttons = new ArrayList();

    public CSCategoryButton(int x, int y, int width, int height, int color, String displayString, Category category) {
        super(x, y, width, height, color, displayString);
        this.category = category;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (this.isHovered(mouseX, mouseY)) ** GOTO lbl5
        if (AizenSense.csgui.currentCategory == this) {
lbl5:
            // 2 sources

            v0 = AizenSense.getClientColor().getRGB();
        } else {
            v0 = this.color;
        }
        color = v0;
        this.fr.drawString(this.displayString, this.x, this.y, color);
        var5_5 = this.buttons.iterator();
        while (true) {
            if (!var5_5.hasNext()) {
                super.drawScreen(mouseX, mouseY, partialTicks);
                return;
            }
            csm = var5_5.next();
            if (AizenSense.csgui.currentCategory != this) continue;
            csm.drawScreen(mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void onKeyPress(int typedChar, int keyCode) {
    }

    public boolean isHovered(int mouseX, int mouseY) {
        boolean hoveredx = mouseX > this.x && mouseX < this.x + this.width;
        boolean hoveredy = mouseY > this.y && mouseY < this.y + this.height;
        return hoveredx && hoveredy;
    }

    public void setBinding(boolean binding) {
        CSCategoryButton.binding = binding;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (CSModButton cmb : this.buttons) {
            cmb.mouseClicked(mouseX, mouseY, mouseButton);
            if (mouseButton == 1 && cmb.isHovered(mouseX, mouseY)) {
                this.currentMod = cmb;
            }
            if (mouseButton != 2 || !cmb.isHovered(mouseX, mouseY)) continue;
            this.setBinding(true);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void initButton() {
        this.buttons.clear();
        int x = this.x + 65;
        int y = 110;
        for (int i = 0; i < AizenSense.instance.moduleManager.modules.size(); ++i) {
            Module m = AizenSense.instance.moduleManager.modules.get(i);
            if (m.getCategory() != this.category) continue;
            CSModButton csm = new CSModButton(x, y, this.fr.getStringWidth(m.getName()), this.fr.FONT_HEIGHT, -1, m.getName(), m);
            this.buttons.add(csm);
            y += 10;
        }
        super.initButton();
    }

    private boolean isCurrentPanel() {
        return AizenSense.csgui.currentCategory == this;
    }
}

