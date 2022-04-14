//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Basic\Desktop\projects\java\deof\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.client.event.EntityViewRenderEvent$CameraSetup
 *  net.minecraftforge.common.MinecraftForge
 */
package ru.aizensense.module;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.MinecraftForge;
import ru.aizensense.module.Category;
import ru.aizensense.module.misc.SelfDestruct;
import ru.aizensense.notifications.Type;
import ru.aizensense.notifications.notifications;

public abstract class Module {
    protected static Minecraft mc = Minecraft.getMinecraft();
    private String name;
    private String description;
    private int key;
    private Category category;
    private boolean toggled;
    public boolean visible = true;

    public Module(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.key = 0;
        this.category = category;
        this.toggled = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
        if (!SelfDestruct.self) {
            if (this.toggled) {
                this.onEnable();
            } else {
                this.onDisable();
            }
        }
    }

    public void toggle() {
        boolean bl = this.toggled = !this.toggled;
        if (!SelfDestruct.self) {
            if (this.toggled) {
                this.onEnableR();
            } else {
                this.onDisableR();
                this.onDisable();
            }
        }
    }

    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        notifications.add(this.name, (Object)TextFormatting.GREEN + "Enable!", Type.Green);
    }

    public void onEnableR() {
        this.onEnable();
    }

    public void onDisableR() {
        this.onDisable();
    }

    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        notifications.add(this.name, (Object)TextFormatting.RED + "Disable!", Type.Red);
    }

    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }

    protected void onCameraSetup(EntityViewRenderEvent.CameraSetup event) {
    }
}

