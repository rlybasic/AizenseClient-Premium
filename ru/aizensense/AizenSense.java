//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  org.lwjgl.input.Keyboard
 */
package ru.aizensense;

import clickgui.ClickGUI;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import new_gui.CSGOGui;
import org.lwjgl.input.Keyboard;
import ru.aizensense.clickgui.ClickGui;
import ru.aizensense.module.Module;
import ru.aizensense.module.ModuleManager;
import ru.aizensense.settings.SettingsManager;
import ru.aizensense.utils.RotationManager;

public class AizenSense {
    public static AizenSense instance;
    public ModuleManager moduleManager;
    public SettingsManager settingsManager;
    public ClickGui clickGui;
    public ClickGUI new_gui;
    private RotationManager rotationManager;
    public static Color color;
    public static CSGOGui csgui;

    public RotationManager getRotationManager() {
        if (this.rotationManager == null) {
            this.rotationManager = new RotationManager();
        }
        return this.rotationManager;
    }

    public void init() {
        MinecraftForge.EVENT_BUS.register((Object)instance);
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.settingsManager = new SettingsManager();
        this.moduleManager = new ModuleManager();
        this.rotationManager = new RotationManager();
        this.clickGui = new ClickGui();
        this.new_gui = new ClickGUI();
        csgui = new CSGOGui();
    }

    public static Color getClientColor() {
        return color;
    }

    @SubscribeEvent
    public void key(InputEvent.KeyInputEvent e) {
        if (Minecraft.getMinecraft().world == null || Minecraft.getMinecraft().player == null) {
            return;
        }
        try {
            if (Keyboard.isCreated() && Keyboard.getEventKeyState()) {
                int keyCode = Keyboard.getEventKey();
                if (keyCode <= 0) {
                    return;
                }
                for (Module m : this.moduleManager.modules) {
                    if (m.getKey() != keyCode || keyCode <= 0) continue;
                    m.toggle();
                }
            }
        }
        catch (Exception q) {
            q.printStackTrace();
        }
    }

    static {
        color = Color.LIGHT_GRAY;
    }
}

