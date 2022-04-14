//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  org.lwjgl.input.Mouse
 */
package ru.aizensense.module.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Mouse;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;
import ru.aizensense.notifications.Type;
import ru.aizensense.notifications.notifications;
import ru.aizensense.utils.FriendManager;
import ru.aizensense.utils.UtilsForFov;

public class MCF
extends Module {
    public MCF() {
        super("MCF", "add friends", Category.OUTHER);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        Entity entity;
        RayTraceResult object = Minecraft.getMinecraft().objectMouseOver;
        if (object == null) {
            return;
        }
        if (object.typeOfHit == RayTraceResult.Type.ENTITY && (entity = object.entityHit) instanceof EntityPlayer && !(entity instanceof EntityArmorStand) && !Minecraft.getMinecraft().player.isDead && Minecraft.getMinecraft().player.canEntityBeSeen(entity)) {
            EntityPlayer player = (EntityPlayer)entity;
            String ID = UtilsForFov.getPlayerName(player);
            if (Mouse.isButtonDown((int)1) && Minecraft.getMinecraft().currentScreen == null) {
                FriendManager.addFriend(ID);
                notifications.add((Object)TextFormatting.GREEN + ID, "Add Friend", Type.OK);
            } else if (Mouse.isButtonDown((int)2) && Minecraft.getMinecraft().currentScreen == null) {
                FriendManager.removeFriend(ID);
                notifications.add((Object)TextFormatting.RED + ID, "Delete Friend", Type.OK);
            }
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }
}

