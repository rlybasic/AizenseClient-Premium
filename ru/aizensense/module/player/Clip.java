//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 */
package ru.aizensense.module.player;

import java.util.ArrayList;
import java.util.List;
import ru.aizensense.AizenSense;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;
import ru.aizensense.settings.Setting;

public class Clip
extends Module {
    public static List<String> Modess = new ArrayList<String>();

    public Clip() {
        super("Clip", "Clip", Category.PLAYER);
        AizenSense.instance.settingsManager.rSetting(new Setting("CLIP", this, 90.0, 1.0, 255.0, true));
        Modess.add("UP");
        Modess.add("DOWN");
        AizenSense.instance.settingsManager.rSetting(new Setting("Mode", this, "UP", (ArrayList)Modess));
    }

    @Override
    public void onEnable() {
        String Mode = AizenSense.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        float clip = (float)AizenSense.instance.settingsManager.getSettingByName(this, "CLIP").getValDouble();
        if (Mode == "UP") {
            Clip.mc.player.getRidingEntity().setPosition(Clip.mc.player.getRidingEntity().posX, Clip.mc.player.getRidingEntity().posY + (double)clip, Clip.mc.player.getRidingEntity().posZ);
        } else if (Mode == "DOWN") {
            Clip.mc.player.getRidingEntity().setPosition(Clip.mc.player.getRidingEntity().posX, Clip.mc.player.getRidingEntity().posY - (double)clip, Clip.mc.player.getRidingEntity().posZ);
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}

