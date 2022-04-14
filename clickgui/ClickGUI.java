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
package clickgui;

import clickgui.Panel;
import clickgui.elements.Element;
import clickgui.elements.ModuleButton;
import clickgui.elements.menu.ElementSlider;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;
import ru.aizensense.AizenSense;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;
import ru.aizensense.settings.SettingsManager;

public class ClickGUI
extends GuiScreen {
    public static ArrayList<Panel> panels;
    public static ArrayList<Panel> rpanels;
    private ModuleButton mb = null;
    public SettingsManager setmgr;

    public ClickGUI() {
        this.setmgr = AizenSense.instance.settingsManager;
        panels = new ArrayList();
        double d0 = 105.0;
        double d1 = 25.0;
        double d2 = 10.0;
        double d3 = 10.0;
        double d4 = d1 + 3.0;
        for (final Category category : Category.values()) {
            String s = Character.toUpperCase(category.name().toLowerCase().charAt(0)) + category.name().toLowerCase().substring(1);
            panels.add(new Panel(s, d2, d3, d0, d1, true, this){

                @Override
                public void setup() {
                    for (Module function : AizenSense.instance.moduleManager.getModuleList()) {
                        if (!function.getCategory().equals((Object)category)) continue;
                        this.Elements.add(new ModuleButton(function, this));
                    }
                }
            });
            d2 += d0 + 5.0;
        }
        rpanels = new ArrayList();
        for (Panel panel : panels) {
            rpanels.add(panel);
        }
        Collections.reverse(rpanels);
    }

    public void updateScreen() {
        for (Panel panel : panels) {
            for (ModuleButton modulebutton : panel.Elements) {
                for (Element element : modulebutton.menuelements) {
                    element.tick();
                    if (!panel.extended) {
                        element.anim = 0.0f;
                        element.anim2 = 0.0f;
                    }
                    if (modulebutton.extended) continue;
                    element.anim = 0.0f;
                    element.anim2 = 0.0f;
                }
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect((int)0, (int)0, (int)new ScaledResolution(this.mc).getScaledWidth(), (int)new ScaledResolution(this.mc).getScaledHeight(), (int)0x50000000);
        for (Panel panel : panels) {
            panel.drawScreen(mouseX, mouseY, partialTicks);
        }
        this.mb = null;
        block1: for (Panel panel1 : panels) {
            if (panel1 == null || !panel1.visible || !panel1.extended || panel1.Elements == null || panel1.Elements.size() <= 0) continue;
            for (ModuleButton modulebutton : panel1.Elements) {
                if (!modulebutton.listening) continue;
                this.mb = modulebutton;
                break block1;
            }
        }
        for (Panel panel2 : panels) {
            if (!panel2.extended || !panel2.visible || panel2.Elements == null) continue;
            for (ModuleButton modulebutton1 : panel2.Elements) {
                if (!modulebutton1.extended || modulebutton1.menuelements == null || modulebutton1.menuelements.isEmpty()) continue;
                double d0 = 0.0;
                Color color = new Color(-13350562);
                int i = new Color(color.getRed(), color.getGreen(), color.getBlue(), 170).getRGB();
                for (Element element : modulebutton1.menuelements) {
                    element.offset = d0;
                    element.update();
                    element.drawScreen(mouseX, mouseY, partialTicks);
                    d0 += element.height;
                }
            }
        }
        if (this.mb != null) {
            ScaledResolution scaledresolution = new ScaledResolution(this.mc);
            ClickGUI.drawRect((int)0, (int)0, (int)this.width, (int)this.height, (int)-872415232);
            GL11.glPushMatrix();
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Bind Manager", (float)(scaledresolution.getScaledWidth() / 2), (float)(scaledresolution.getScaledHeight() / 2 - 30), -15558688);
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Press any key...", (float)(scaledresolution.getScaledWidth() / 2), (float)(scaledresolution.getScaledHeight() / 2 - 10), -1);
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("'Escape' - unbound", (float)(scaledresolution.getScaledWidth() / 2), (float)(scaledresolution.getScaledHeight() / 2), -1);
            GL11.glPopMatrix();
        }
        for (Panel panel3 : rpanels) {
            if (!panel3.extended || !panel3.visible || panel3.Elements == null) continue;
            for (ModuleButton moduleButton : panel3.Elements) {
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (this.mb == null) {
            for (Panel panel : rpanels) {
                if (!panel.extended || !panel.visible || panel.Elements == null) continue;
                for (ModuleButton modulebutton : panel.Elements) {
                    if (!modulebutton.extended) continue;
                    for (Element element : modulebutton.menuelements) {
                        if (!element.mouseClicked(mouseX, mouseY, mouseButton)) continue;
                        return;
                    }
                }
            }
            for (Panel panel1 : rpanels) {
                if (!panel1.mouseClicked(mouseX, mouseY, mouseButton)) continue;
                return;
            }
            try {
                super.mouseClicked(mouseX, mouseY, mouseButton);
            }
            catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (this.mb == null) {
            for (Panel panel : rpanels) {
                if (!panel.extended || !panel.visible || panel.Elements == null) continue;
                for (ModuleButton modulebutton : panel.Elements) {
                    if (!modulebutton.extended) continue;
                    for (Element element : modulebutton.menuelements) {
                        element.mouseReleased(mouseX, mouseY, state);
                    }
                }
            }
            for (Panel panel1 : rpanels) {
                panel1.mouseReleased(mouseX, mouseY, state);
            }
            super.mouseReleased(mouseX, mouseY, state);
        }
    }

    protected void keyTyped(char typedChar, int keyCode) {
        for (Panel panel : rpanels) {
            if (panel == null || !panel.visible || !panel.extended || panel.Elements == null || panel.Elements.size() <= 0) continue;
            for (ModuleButton modulebutton : panel.Elements) {
                try {
                    if (!modulebutton.keyTyped(typedChar, keyCode)) continue;
                    return;
                }
                catch (IOException ioexception1) {
                    ioexception1.printStackTrace();
                }
            }
        }
        try {
            super.keyTyped(typedChar, keyCode);
        }
        catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }

    public void initGui() {
        for (Panel panel : panels) {
            for (ModuleButton modulebutton : panel.Elements) {
                for (Element element : modulebutton.menuelements) {
                    element.anim = 0.0f;
                    element.anim2 = 0.0f;
                }
            }
        }
    }

    public void onGuiClosed() {
        for (Panel panel : rpanels) {
            if (!panel.extended || !panel.visible || panel.Elements == null) continue;
            for (ModuleButton modulebutton : panel.Elements) {
                if (!modulebutton.extended) continue;
                for (Element element : modulebutton.menuelements) {
                    if (!(element instanceof ElementSlider)) continue;
                    ((ElementSlider)element).dragging = false;
                }
            }
        }
    }

    public void closeAllSettings() {
        for (Panel panel : rpanels) {
            if (panel == null || !panel.visible || !panel.extended || panel.Elements == null || panel.Elements.size() <= 0) continue;
            for (ModuleButton moduleButton : panel.Elements) {
            }
        }
    }
}

