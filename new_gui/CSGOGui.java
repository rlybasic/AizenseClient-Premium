//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  org.lwjgl.opengl.GL11
 */
package new_gui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import new_gui.buttons.CSCategoryButton;
import org.lwjgl.opengl.GL11;
import ru.aizensense.AizenSense;
import ru.aizensense.module.Category;
import ru.aizensense.utils.RenderUtil;

public class CSGOGui
extends GuiScreen {
    private float curAlpha;
    private float anim;
    public ArrayList<CSCategoryButton> buttons = new ArrayList();
    public CSCategoryButton currentCategory;
    public int x = 100;
    public int y = 100;
    public int width;
    public int height;
    public ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

    public CSGOGui() {
        this.width = this.sr.getScaledWidth() - 100;
        this.height = this.sr.getScaledHeight() - 100;
        this.width = this.sr.getScaledWidth() - 100;
        this.height = this.sr.getScaledHeight() - 100;
    }

    private void initButtons() {
        this.buttons.clear();
        int x = 110;
        int y = 110;
        for (Category c : Category.values()) {
            String categoryname = c.name().substring(0, 1).toUpperCase() + c.name().substring(1, c.name().length()).toLowerCase();
            CSCategoryButton cscb = new CSCategoryButton(x, y, this.mc.fontRenderer.getStringWidth(categoryname), this.mc.fontRenderer.FONT_HEIGHT, -1, categoryname, c);
            this.buttons.add(cscb);
            y += 15;
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.anim -= 1.0f;
        float alpha = 150.0f;
        int step = (int)(alpha / 100.0f);
        if (this.curAlpha < alpha - (float)step) {
            this.curAlpha += (float)step;
        } else if (this.curAlpha > alpha - (float)step && this.curAlpha != alpha) {
            this.curAlpha = (int)alpha;
        } else if (this.curAlpha != alpha) {
            this.curAlpha = (int)alpha;
        }
        Color c = new Color(AizenSense.getClientColor().getRed(), AizenSense.getClientColor().getGreen(), AizenSense.getClientColor().getBlue(), (int)this.curAlpha);
        Color none = new Color(0, 0, 0, 0);
        RenderUtil.drawNewRect(this.x, this.height, this.width, (double)this.height + 0.5, AizenSense.getClientColor().getRGB());
        RenderUtil.drawNewRect(this.width, this.y - this.fontRenderer.FONT_HEIGHT * 2, (double)this.width + 0.5, this.height, AizenSense.getClientColor().getRGB());
        Gui.drawRect((int)this.x, (int)(this.y - this.fontRenderer.FONT_HEIGHT * 2), (int)this.width, (int)this.height, (int)new Color(0, 0, 0, 240).getRGB());
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.fontRenderer.drawStringWithShadow("Nemezida V0.1 (owned by @blastedclient)", (float)(this.x + 3), (float)(this.y - this.fontRenderer.FONT_HEIGHT - 3), Color.white.getRGB());
        RenderUtil.drawGlow(this.x + 2, this.y - this.fontRenderer.FONT_HEIGHT - 2, this.x + 2 + this.fontRenderer.getStringWidth("AizenSense V0.1") + 3, this.y + this.fontRenderer.FONT_HEIGHT, AizenSense.getClientColor().getRGB(), 70.0);
        for (CSCategoryButton cb : this.buttons) {
            cb.drawScreen(mouseX, mouseY, partialTicks);
        }
        Gui.drawRect((int)(this.x + 65), (int)this.y, (int)(this.x + 67), (int)this.height, (int)AizenSense.getClientColor().getRGB());
        Gui.drawRect((int)(this.x + 165), (int)this.y, (int)(this.x + 167), (int)this.height, (int)AizenSense.getClientColor().getRGB());
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        for (CSCategoryButton cb : this.buttons) {
            cb.keyTyped(typedChar, keyCode);
        }
        super.keyTyped(typedChar, keyCode);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        boolean anyhovered = true;
        for (CSCategoryButton cb : this.buttons) {
            if (cb.isHovered(mouseX, mouseY) && mouseButton == 0) {
                anyhovered = true;
                this.currentCategory = cb;
            }
            cb.mouseClicked(mouseX, mouseY, mouseButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for (CSCategoryButton cb : this.buttons) {
            cb.mouseReleased(mouseX, mouseY, state);
        }
        super.mouseReleased(mouseX, mouseY, state);
    }

    public void initGui() {
        this.initButtons();
        this.anim = 0.0f;
        this.x = 100;
        this.y = 100;
        this.width = 500;
        this.height = 350;
        for (CSCategoryButton cb : this.buttons) {
            cb.initButton();
        }
        super.initGui();
    }
}

