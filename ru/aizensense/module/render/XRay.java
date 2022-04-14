//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package ru.aizensense.module.render;

import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.aizensense.AizenSense;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;
import ru.aizensense.settings.Setting;
import ru.aizensense.utils.BlockPosUtils;
import ru.aizensense.utils.RenderTracerUtils;

public class XRay
extends Module {
    public XRay() {
        super("XRay", "rendering ruds", Category.RENDER);
        AizenSense.instance.settingsManager.rSetting(new Setting("Range", this, 100.0, 1.0, 250.0, true));
        AizenSense.instance.settingsManager.rSetting(new Setting("StoneOre", this, false));
        AizenSense.instance.settingsManager.rSetting(new Setting("IronOre", this, false));
        AizenSense.instance.settingsManager.rSetting(new Setting("SulfurOre", this, false));
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        boolean Stone = AizenSense.instance.settingsManager.getSettingByName(this, "StoneOre").getValBoolean();
        boolean Iron = AizenSense.instance.settingsManager.getSettingByName(this, "IronOre").getValBoolean();
        boolean Sulfur = AizenSense.instance.settingsManager.getSettingByName(this, "SulfurOre").getValBoolean();
        float range = (float)AizenSense.instance.settingsManager.getSettingByName(this, "Range").getValDouble();
        if (Stone) {
            BlockPos stone = BlockPosUtils.getBlockLocation(Block.getBlockById((int)159), (int)range);
            if (stone == null) {
                return;
            }
            RenderTracerUtils.blockesp(stone, Color.DARK_GRAY, true);
        }
        if (Iron) {
            BlockPos iron = BlockPosUtils.getBlockLocation(Block.getBlockById((int)159), (int)range);
            if (iron == null) {
                return;
            }
            RenderTracerUtils.blockesp(iron, Color.PINK, true);
        }
        if (Sulfur) {
            BlockPos sulfur = BlockPosUtils.getBlockLocation(Blocks.STONE, (int)range);
            if (sulfur == null) {
                return;
            }
            RenderTracerUtils.blockesp(sulfur, Color.ORANGE, true);
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}

