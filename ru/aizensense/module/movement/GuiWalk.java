//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiIngameMenu
 *  net.minecraft.client.gui.GuiOptions
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraftforge.client.event.InputUpdateEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.input.Keyboard
 */
package ru.aizensense.module.movement;

import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import new_gui.CSGOGui;
import org.lwjgl.input.Keyboard;
import ru.aizensense.AizenSense;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;
import ru.aizensense.settings.Setting;

public class GuiWalk
extends Module {
    public GuiWalk() {
        super("GuiWalk", "go", Category.MOVEMENT);
        AizenSense.instance.settingsManager.rSetting(new Setting("Sneak", this, false));
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @SubscribeEvent
    public void onKeyUpdate(InputUpdateEvent event) {
        boolean Sneak = AizenSense.instance.settingsManager.getSettingByName(this, "Sneak").getValBoolean();
        if (GuiWalk.mc.world != null && GuiWalk.mc.player != null && (GuiWalk.mc.currentScreen instanceof GuiContainer || GuiWalk.mc.currentScreen instanceof GuiIngameMenu || GuiWalk.mc.currentScreen instanceof GuiOptions || GuiWalk.mc.currentScreen instanceof CSGOGui)) {
            if (Keyboard.isKeyDown((int)GuiWalk.mc.gameSettings.keyBindForward.getKeyCode())) {
                GuiWalk.mc.player.movementInput.moveForward += 1.0f;
                GuiWalk.mc.player.movementInput.forwardKeyDown = true;
            }
            if (Keyboard.isKeyDown((int)GuiWalk.mc.gameSettings.keyBindBack.getKeyCode())) {
                GuiWalk.mc.player.movementInput.moveForward -= 1.0f;
                GuiWalk.mc.player.movementInput.backKeyDown = true;
            }
            if (Keyboard.isKeyDown((int)GuiWalk.mc.gameSettings.keyBindRight.getKeyCode())) {
                GuiWalk.mc.player.movementInput.moveStrafe -= 1.0f;
                GuiWalk.mc.player.movementInput.rightKeyDown = true;
            }
            if (Keyboard.isKeyDown((int)GuiWalk.mc.gameSettings.keyBindLeft.getKeyCode())) {
                GuiWalk.mc.player.movementInput.moveStrafe += 1.0f;
                GuiWalk.mc.player.movementInput.rightKeyDown = true;
            }
            GuiWalk.mc.player.movementInput.jump = Keyboard.isKeyDown((int)GuiWalk.mc.gameSettings.keyBindJump.getKeyCode());
            boolean bl = GuiWalk.mc.player.movementInput.sneak = Sneak && Keyboard.isKeyDown((int)GuiWalk.mc.gameSettings.keyBindSneak.getKeyCode());
            if (GuiWalk.mc.player.movementInput.sneak) {
                GuiWalk.mc.player.movementInput.moveStrafe = (float)((double)GuiWalk.mc.player.movementInput.moveStrafe * 0.3);
                GuiWalk.mc.player.movementInput.moveForward = (float)((double)GuiWalk.mc.player.movementInput.moveForward * 0.3);
            }
        }
    }
}

