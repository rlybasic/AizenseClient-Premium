//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.opengl.GL11
 */
package ru.aizensense.module.render;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import ru.aizensense.AizenSense;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;
import ru.aizensense.settings.Setting;
import ru.aizensense.utils.FriendManager;

public class Tracers
extends Module {
    public static List<String> listA = new ArrayList<String>();

    public Tracers() {
        super("Tracers", "Show Tracers to players", Category.RENDER);
        AizenSense.instance.settingsManager.rSetting(new Setting("Alpha", this, 0.7, 0.0, 1.0, false));
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
    public void onRender(RenderWorldLastEvent event) {
        if (Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().world != null) {
            boolean old = Tracers.mc.gameSettings.viewBobbing;
            Tracers.mc.gameSettings.viewBobbing = false;
            Tracers.mc.gameSettings.viewBobbing = old;
            GL11.glPushMatrix();
            GL11.glEnable((int)2848);
            GL11.glDisable((int)2929);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glDepthMask((boolean)false);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glEnable((int)3042);
            GL11.glLineWidth((float)1.5f);
            for (Entity entity : Tracers.mc.world.loadedEntityList) {
                if (entity == Tracers.mc.player || !(entity instanceof EntityPlayer)) continue;
                assert (mc.getRenderViewEntity() != null);
                Tracers.mc.player.getDistance(entity);
                double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) - Tracers.mc.getRenderManager().viewerPosX;
                double d2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) - Tracers.mc.getRenderManager().viewerPosY;
                double d3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) - Tracers.mc.getRenderManager().viewerPosZ;
                if (FriendManager.friendsList.contains(entity.getName())) {
                    GL11.glColor4f((float)0.0f, (float)255.0f, (float)0.0f, (float)255.0f);
                } else {
                    GL11.glColor4f((float)255.0f, (float)255.0f, (float)255.0f, (float)255.0f);
                }
                Vec3d vec3d = new Vec3d(0.0, 0.0, 1.0);
                vec3d = vec3d.rotatePitch(-((float)Math.toRadians(Tracers.mc.player.rotationPitch)));
                Vec3d vec3d2 = vec3d.rotateYaw(-((float)Math.toRadians(Tracers.mc.player.rotationYaw)));
                GL11.glBegin((int)2);
                GL11.glVertex3d((double)vec3d2.x, (double)((double)Tracers.mc.player.getEyeHeight() + vec3d2.y), (double)vec3d2.z);
                GL11.glVertex3d((double)d, (double)(d2 + 1.1), (double)d3);
                GL11.glEnd();
            }
            GL11.glDisable((int)3042);
            GL11.glDepthMask((boolean)true);
            GL11.glEnable((int)3553);
            GL11.glEnable((int)2929);
            GL11.glDisable((int)2848);
            GL11.glPopMatrix();
        }
    }
}

