//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 */
package ru.aizensense.module.combat;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ru.aizensense.AizenSense;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;
import ru.aizensense.settings.Setting;

public class AntiBot
extends Module {
    public static List<String> BOTS = new ArrayList<String>();
    public static boolean toggle;

    public AntiBot() {
        super("AntiBot", "Prevents you from targetting bots", Category.COMBAT);
        AizenSense.instance.settingsManager.rSetting(new Setting("Remove", this, true));
    }

    @Override
    public void onEnable() {
        super.onEnable();
        toggle = true;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        toggle = false;
        BOTS.clear();
    }

    public static boolean isBot(String nick) {
        if (toggle) {
            for (String friend : BOTS) {
                if (!friend.equalsIgnoreCase(nick)) continue;
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean isBlockMaterial(BlockPos blockPos, Block block) {
        return AntiBot.getBlock(blockPos) == Blocks.AIR;
    }

    public static Block getBlock(BlockPos pos) {
        return AntiBot.getState(pos).getBlock();
    }

    public static IBlockState getState(BlockPos pos) {
        return AntiBot.mc.world.getBlockState(pos);
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        boolean Remove = AizenSense.instance.settingsManager.getSettingByName(this, "Remove").getValBoolean();
        for (Entity e : Minecraft.getMinecraft().world.loadedEntityList) {
            if (e == Minecraft.getMinecraft().player || e.ticksExisted >= 5 || !e.isInvisible() || !(e instanceof EntityOtherPlayerMP)) continue;
            if (((EntityOtherPlayerMP)e).hurtTime > 0 && Minecraft.getMinecraft().player.getDistance(e) <= 25.0f && Minecraft.getMinecraft().getConnection().getPlayerInfo(e.getUniqueID()).getResponseTime() != 0) {
                BOTS.add(e.getName());
                if (Remove) {
                    Minecraft.getMinecraft().world.removeEntity(e);
                }
            }
            if (!e.isInvisible()) continue;
            BOTS.add(e.getName());
            if (!Remove) continue;
            AntiBot.mc.world.removeEntity(e);
        }
    }
}

