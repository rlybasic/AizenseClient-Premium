//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package ru.aizensense.module.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ru.aizensense.AizenSense;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;

public class SelfDestruct
extends Module {
    public static boolean self = false;

    public SelfDestruct() {
        super("SelfDestruct", "clouse client", Category.OUTHER);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.displayGuiScreen(null);
        for (Module mod : AizenSense.instance.moduleManager.getModuleList()) {
            if (mod.getName() == "SelfDestruct") continue;
            mod.setToggled(false);
        }
        self = true;
        SelfDestruct.mc.player.rotationYaw = 0.0f;
        SelfDestruct.mc.player.rotationPitch = 0.0f;
    }
}

