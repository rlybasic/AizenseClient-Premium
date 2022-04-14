//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package ru.aizensense.module.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.aizensense.AizenSense;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;
import ru.aizensense.settings.Setting;

public class ShkafRender
extends Module {
    public ShkafRender() {
        super("ShkafRender", "ShkafRender", Category.RENDER);
        AizenSense.instance.settingsManager.rSetting(new Setting("Wallhack", this, true));
        AizenSense.instance.settingsManager.rSetting(new Setting("Glowing", this, false));
    }

    @Override
    public void onDisable() {
        super.onDisable();
        for (Entity entity : ShkafRender.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityArmorStand) || !entity.isGlowing()) continue;
            entity.setGlowing(false);
        }
    }

    void render(Entity entity, float ticks) {
        try {
            if (entity == null || entity == Minecraft.getMinecraft().player) {
                return;
            }
            if (entity != Minecraft.getMinecraft().getRenderViewEntity() || Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
                // empty if block
            }
            Minecraft.getMinecraft().getRenderManager().renderEntityStatic(entity, ticks, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        GlStateManager.clear((int)256);
        RenderHelper.enableStandardItemLighting();
        boolean Wallhack = AizenSense.instance.settingsManager.getSettingByName(this, "Wallhack").getValBoolean();
        boolean Glowing = AizenSense.instance.settingsManager.getSettingByName(this, "Glowing").getValBoolean();
        for (Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (!(entity instanceof EntityArmorStand) || entity == Minecraft.getMinecraft().getRenderViewEntity()) continue;
            if (Wallhack) {
                this.render(entity, event.getPartialTicks());
            }
            if (!Glowing || entity.isGlowing()) continue;
            entity.setGlowing(true);
        }
    }
}

