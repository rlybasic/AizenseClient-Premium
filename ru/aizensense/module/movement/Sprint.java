//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package ru.aizensense.module.movement;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;

public class Sprint
extends Module {
    public Sprint() {
        super("Sprint", "i like sprinting", Category.MOVEMENT);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        KeyBinding.setKeyBindState((int)Sprint.mc.gameSettings.keyBindSprint.getKeyCode(), (boolean)true);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        KeyBinding.setKeyBindState((int)Sprint.mc.gameSettings.keyBindSprint.getKeyCode(), (boolean)false);
    }
}

