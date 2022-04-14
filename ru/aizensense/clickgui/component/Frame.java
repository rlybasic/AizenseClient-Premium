//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  org.lwjgl.opengl.GL11
 */
package ru.aizensense.clickgui.component;

import java.util.ArrayList;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;
import ru.aizensense.AizenSense;
import ru.aizensense.clickgui.ClickGui;
import ru.aizensense.clickgui.component.Component;
import ru.aizensense.clickgui.component.components.Button;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;

public class Frame {
    public ArrayList<Component> components = new ArrayList();
    public Category category;
    private boolean open;
    private int width;
    private int y;
    private int x;
    private int barHeight;
    private boolean isDragging;
    public int dragX;
    public int dragY;

    public Frame(Category cat) {
        this.category = cat;
        this.width = 88;
        this.x = 5;
        this.y = 5;
        this.barHeight = 13;
        this.dragX = 0;
        this.open = false;
        this.isDragging = false;
        int tY = this.barHeight;
        for (Module mod : AizenSense.instance.moduleManager.getModulesInCategory(this.category)) {
            Button modButton = new Button(mod, this, tY);
            this.components.add(modButton);
            tY += 12;
        }
    }

    public ArrayList<Component> getComponents() {
        return this.components;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public void setDrag(boolean drag) {
        this.isDragging = drag;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void renderFrame(FontRenderer fontRenderer) {
        Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.barHeight), (int)ClickGui.color);
        GL11.glPushMatrix();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        fontRenderer.drawStringWithShadow(this.category.name(), (float)((this.x + 2) * 2 + 5), ((float)this.y + 2.5f) * 2.0f + 5.0f, 15252223);
        fontRenderer.drawStringWithShadow(this.open ? "-" : "+", (float)((this.x + this.width - 10) * 2 + 5), ((float)this.y + 2.5f) * 2.0f + 5.0f, -1);
        GL11.glPopMatrix();
        if (this.open && !this.components.isEmpty()) {
            for (Component component : this.components) {
                component.renderComponent();
            }
        }
    }

    public void refresh() {
        int off = this.barHeight;
        for (Component comp : this.components) {
            comp.setOff(off);
            off += comp.getHeight();
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public void updatePosition(int mouseX, int mouseY) {
        if (this.isDragging) {
            this.setX(mouseX - this.dragX);
            this.setY(mouseY - this.dragY);
        }
    }

    public boolean isWithinHeader(int x, int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight;
    }
}

