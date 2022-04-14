//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.opengl.GL11
 */
package ru.aizensense.module.render;

import java.awt.Color;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;
import ru.aizensense.utils.FriendManager;

public class NameTags
extends Module {
    private FontRenderer fontRenderer;

    public NameTags() {
        super("NameTags", "Show nick of player", Category.RENDER);
        this.fontRenderer = Minecraft.getMinecraft().fontRenderer;
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        for (Entity e : NameTags.mc.world.loadedEntityList) {
            if (!(e instanceof EntityPlayer) || e == NameTags.mc.player) continue;
            double x = e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)event.getPartialTicks() - NameTags.mc.getRenderManager().viewerPosX;
            double y = e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)event.getPartialTicks() - NameTags.mc.getRenderManager().viewerPosY;
            double z = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)event.getPartialTicks() - NameTags.mc.getRenderManager().viewerPosZ;
            GL11.glPushMatrix();
            GL11.glDisable((int)2929);
            GL11.glDisable((int)3553);
            GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            float size = Math.min(Math.max(1.2f * (NameTags.mc.player.getDistance(e) * 0.15f), 1.25f), 6.0f) * 0.015f;
            GL11.glTranslatef((float)((float)x), (float)((float)y + e.height + 0.7f), (float)((float)z));
            GlStateManager.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)(-NameTags.mc.getRenderManager().playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)NameTags.mc.getRenderManager().playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glScalef((float)(-size), (float)(-size), (float)(-size));
            int health = (int)(((EntityPlayer)e).getHealth() / ((EntityPlayer)e).getMaxHealth() * 100.0f);
            Gui.drawRect((int)(-NameTags.mc.fontRenderer.getStringWidth(e.getName() + " " + health + "%") / 2 - 2), (int)-2, (int)(NameTags.mc.fontRenderer.getStringWidth(e.getName()) / 2 + 16), (int)10, (int)(!FriendManager.friendsList.contains(e.getName()) ? Integer.MIN_VALUE : Color.green.getRGB()));
            NameTags.mc.fontRenderer.drawString(e.getName() + " " + (Object)TextFormatting.GREEN + health + "%", 0 - this.getcenter(e.getName() + " " + (Object)TextFormatting.DARK_GREEN + health + "%"), 1, -1);
            int posX = -NameTags.mc.fontRenderer.getStringWidth(e.getName()) / 2 - 8;
            if (Item.getIdFromItem((Item)((EntityPlayer)e).inventory.getCurrentItem().getItem()) != 0) {
                NameTags.mc.getRenderItem().zLevel = -100.0f;
                mc.getRenderItem().renderItemIntoGUI(new ItemStack(((EntityPlayer)e).inventory.getCurrentItem().getItem()), posX - 2, -20);
                NameTags.mc.getRenderItem().zLevel = 0.0f;
                int posY = -30;
                Map enchantments = EnchantmentHelper.getEnchantments((ItemStack)((EntityPlayer)e).inventory.getCurrentItem());
                for (Enchantment enchantment : enchantments.keySet()) {
                    int level = EnchantmentHelper.getEnchantmentLevel((Enchantment)enchantment, (ItemStack)((EntityPlayer)e).inventory.getCurrentItem());
                    NameTags.mc.fontRenderer.drawStringWithShadow(String.valueOf(enchantment.getName().substring(12).charAt(0)).toUpperCase() + level, (float)(posX + 6 - this.getcenter(String.valueOf(enchantment.getName().substring(12).charAt(0)).toUpperCase() + level)), (float)posY, -1);
                    posY -= 12;
                }
                posX += 15;
            }
            for (ItemStack item : e.getArmorInventoryList()) {
                NameTags.mc.getRenderItem().zLevel = -100.0f;
                mc.getRenderItem().renderItemIntoGUI(new ItemStack(item.getItem()), posX, -20);
                NameTags.mc.getRenderItem().zLevel = 0.0f;
                int posY = -30;
                Map enchantments = EnchantmentHelper.getEnchantments((ItemStack)item);
                for (Enchantment enchantment : enchantments.keySet()) {
                    int level = EnchantmentHelper.getEnchantmentLevel((Enchantment)enchantment, (ItemStack)item);
                    NameTags.mc.fontRenderer.drawStringWithShadow(String.valueOf(enchantment.getName().substring(12).charAt(0)).toUpperCase() + level, (float)(posX + 9 - this.getcenter(enchantment.getName().substring(12).charAt(0) + level)), (float)posY, -1);
                    posY -= 12;
                }
                posX += 17;
            }
            GL11.glEnable((int)2929);
            GL11.glPopMatrix();
        }
    }

    public int getcenter(String text) {
        return NameTags.mc.fontRenderer.getStringWidth(text) / 2;
    }

    public int getcenter(int text) {
        return NameTags.mc.fontRenderer.getStringWidth(String.valueOf(text)) / 2;
    }
}

