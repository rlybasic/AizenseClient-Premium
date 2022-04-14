//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package ru.aizensense.module.combat;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.aizensense.AizenSense;
import ru.aizensense.module.Category;
import ru.aizensense.module.Module;
import ru.aizensense.settings.Setting;
import ru.aizensense.utils.FOVUtil;
import ru.aizensense.utils.FriendManager;
import ru.aizensense.utils.TimerUtils;

public class AimPistol
extends Module {
    public float[] facing;
    public TimerUtils timer = new TimerUtils();
    public Entity target;
    public static List<String> Modesss = new ArrayList<String>();
    public static List<String> AimPos = new ArrayList<String>();

    public AimPistol() {
        super("AimPistol", "aim to players", Category.COMBAT);
        Modesss.add("Client");
        Modesss.add("PacketAir");
        AimPos.add("Head");
        AizenSense.instance.settingsManager.rSetting(new Setting("Mode", this, "Client", (ArrayList)Modesss));
        AizenSense.instance.settingsManager.rSetting(new Setting("AimPos", this, "Head", (ArrayList)AimPos));
        AizenSense.instance.settingsManager.rSetting(new Setting("Range", this, 300.0, 0.0, 300.0, true));
        AizenSense.instance.settingsManager.rSetting(new Setting("Predict", this, 4.0, 0.1, 8.0, false));
        AizenSense.instance.settingsManager.rSetting(new Setting("FOV", this, 90.0, 1.0, 360.0, true));
        AizenSense.instance.settingsManager.rSetting(new Setting("Walls", this, false));
        AizenSense.instance.settingsManager.rSetting(new Setting("AutoAim", this, false));
        AizenSense.instance.settingsManager.rSetting(new Setting("AutoShoot", this, false));
        AizenSense.instance.settingsManager.rSetting(new Setting("AutoShootDeley", this, 100.0, 0.0, 500.0, true));
    }

    public boolean isTarget(EntityPlayer entity) {
        float Range = (float)AizenSense.instance.settingsManager.getSettingByName(this, "Range").getValDouble();
        return entity != Minecraft.getMinecraft().player && Minecraft.getMinecraft().player.getDistanceSq((Entity)entity) <= Math.pow(Range, 0.0) && entity.getHealth() > 0.0f;
    }

    public static float[] faceCords(float posX, float posY, float posZ, float p_706252, float p_706253, boolean miss) {
        double var4 = (double)posX - Minecraft.getMinecraft().player.posX;
        double var5 = (double)posZ - Minecraft.getMinecraft().player.posZ;
        double var7 = (double)posY + 1.66 - (Minecraft.getMinecraft().player.posY + (double)Minecraft.getMinecraft().player.getEyeHeight());
        double var8 = MathHelper.sqrt((double)(var4 * var4 + var5 * var5));
        float var9 = (float)(Math.atan2(var5, var4) * 180.0 / Math.PI) - 90.0f;
        float var10 = (float)(-(Math.atan2(var7 - 0.24, var8) * 180.0 / Math.PI));
        float f = Minecraft.getMinecraft().gameSettings.mouseSensitivity * 0.6f + 0.2f;
        float gcd = f * f * f * 1.2f;
        float pitch = AimPistol.updateRotation(Minecraft.getMinecraft().player.rotationPitch, var10, p_706253);
        float yaw = AimPistol.updateRotation(Minecraft.getMinecraft().player.rotationYaw, var9, p_706252);
        yaw -= yaw % gcd;
        pitch -= pitch % gcd;
        return new float[]{yaw, pitch};
    }

    public static float updateRotation(float current, float intended, float speed) {
        float f = MathHelper.wrapDegrees((float)(intended - current));
        if (f > speed) {
            f = speed;
        }
        if (f < -speed) {
            f = -speed;
        }
        return current + f;
    }

    public float[] getPredict(Entity e) {
        float Predict = (float)AizenSense.instance.settingsManager.getSettingByName(this, "Predict").getValDouble();
        float Range = (float)AizenSense.instance.settingsManager.getSettingByName(this, "Range").getValDouble();
        double xDiff = e.posX - e.lastTickPosX;
        double zDiff = e.posZ - e.lastTickPosZ;
        float predict = Predict + AimPistol.mc.player.getDistance(e) / Range;
        double WillPosX = e.posX + xDiff * (double)predict;
        double WillPosZ = e.posZ + zDiff * (double)predict;
        return new float[]{(float)WillPosX, (float)WillPosZ};
    }

    public boolean attackCheck(Entity target) {
        boolean Walls = AizenSense.instance.settingsManager.getSettingByName(this, "Walls").getValBoolean();
        if (Walls) {
            return target instanceof EntityPlayer && !FriendManager.friendsList.contains(target.getName());
        }
        if (!Walls && AimPistol.mc.player.canEntityBeSeen(target)) {
            return target instanceof EntityPlayer && !FriendManager.friendsList.contains(target.getName());
        }
        return false;
    }

    private boolean lambdagetTarget(Entity entity) {
        return this.attackCheck(entity);
    }

    public Entity getTarget() {
        if (AimPistol.mc.player == null || AimPistol.mc.player.isDead) {
            return null;
        }
        List list = AimPistol.mc.world.loadedEntityList.stream().filter(entity -> entity != AimPistol.mc.player).filter(entity -> AimPistol.mc.player.getDistance(entity) <= 200.0f).filter(entity -> !entity.isDead).filter(this::lambdagetTarget).sorted(Comparator.comparing(entity -> Float.valueOf(AimPistol.mc.player.getDistance(entity)))).collect(Collectors.toList());
        if (list.size() > 0) {
            return (Entity)list.get(0);
        }
        return null;
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent e) {
        String Mode = AizenSense.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        String AimPos = AizenSense.instance.settingsManager.getSettingByName(this, "AimPos").getValString();
        float Range = (float)AizenSense.instance.settingsManager.getSettingByName(this, "Range").getValDouble();
        float FOV = (float)AizenSense.instance.settingsManager.getSettingByName(this, "FOV").getValDouble();
        boolean AutoShoot = AizenSense.instance.settingsManager.getSettingByName(this, "AutoShoot").getValBoolean();
        float AutoShootDeley = (float)AizenSense.instance.settingsManager.getSettingByName(this, "AutoShootDeley").getValDouble();
        boolean AutoAim = AizenSense.instance.settingsManager.getSettingByName(this, "AutoAim").getValBoolean();
        boolean Walls = AizenSense.instance.settingsManager.getSettingByName(this, "Walls").getValBoolean();
        this.target = null;
        this.target = this.getTarget();
        if (this.target != null) {
            if (!FriendManager.friendsList.contains(this.target.getName()) && FOVUtil.isInAttackFOV(this.target, (int)AizenSense.instance.settingsManager.getSettingByName(this, "FOV").getValDouble())) {
                float f2;
                float f;
                if (Mode == "Client") {
                    this.facing = this.getPredict(this.target);
                    this.facing = AimPistol.faceCords(this.facing[0], (float)this.target.posY, this.facing[1], 360.0f, 360.0f, false);
                    f = this.facing[0];
                    f2 = this.facing[1];
                    AimPistol.mc.player.rotationYaw = this.facing[0];
                    AimPistol.mc.player.rotationPitch = this.facing[1];
                }
                if (Mode == "PacketAir") {
                    this.facing = this.getPredict(this.target);
                    this.facing = AimPistol.faceCords(this.facing[0], (float)this.target.posY, this.facing[1], 360.0f, 360.0f, false);
                    f = this.facing[0];
                    f2 = this.facing[1];
                    AimPistol.mc.player.rotationYaw = this.facing[0];
                    AimPistol.mc.player.rotationPitch = this.facing[1];
                    if (AimPistol.mc.gameSettings.keyBindJump.isKeyDown()) {
                        AimPistol.mc.player.inventory.currentItem = 0;
                    }
                }
            }
            if (AutoShoot && AimPistol.mc.player.canEntityBeSeen(this.target)) {
                if (this.timer.isDelay((long)AutoShootDeley)) {
                    if (AutoAim) {
                        AimPistol.clickMouse(1);
                    }
                    AimPistol.mc.player.swingArm(EnumHand.MAIN_HAND);
                    if (AutoAim) {
                        AimPistol.clickMouse(1);
                    }
                }
                this.timer.setLastMS();
            }
        } else {
            this.timer.setLastMS();
        }
    }

    public static void clickMouse(int button) {
        block4: {
            try {
                Robot bot = new Robot();
                if (button == 0) {
                    bot.mousePress(16);
                    bot.mouseRelease(16);
                    break block4;
                }
                if (button == 1) {
                    bot.mousePress(4096);
                    bot.mouseRelease(4096);
                    break block4;
                }
                return;
            }
            catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }
}

