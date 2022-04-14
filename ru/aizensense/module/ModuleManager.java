/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.reflect.ClassPath
 *  com.google.common.reflect.ClassPath$ClassInfo
 */
package ru.aizensense.module;

import com.google.common.reflect.ClassPath;
import java.util.ArrayList;
import ru.aizensense.module.Category;
import ru.aizensense.module.HUD.ArmorHUD;
import ru.aizensense.module.HUD.ClickGUI;
import ru.aizensense.module.HUD.HUD;
import ru.aizensense.module.HUD.Notifications;
import ru.aizensense.module.HUD.cartridgesHUD;
import ru.aizensense.module.Module;
import ru.aizensense.module.combat.AimPistol;
import ru.aizensense.module.combat.AntiBot;
import ru.aizensense.module.combat.Velocity;
import ru.aizensense.module.misc.MCF;
import ru.aizensense.module.misc.SelfDestruct;
import ru.aizensense.module.movement.GuiWalk;
import ru.aizensense.module.movement.Jesus;
import ru.aizensense.module.movement.Sprint;
import ru.aizensense.module.movement.strafe;
import ru.aizensense.module.player.Blink;
import ru.aizensense.module.player.Clip;
import ru.aizensense.module.player.TestModule;
import ru.aizensense.module.render.ESP;
import ru.aizensense.module.render.FullBright;
import ru.aizensense.module.render.NameTags;
import ru.aizensense.module.render.ShkafRender;
import ru.aizensense.module.render.Tracers;
import ru.aizensense.module.render.WallHack;
import ru.aizensense.module.render.XRay;

public class ModuleManager {
    public ArrayList<Module> modules = new ArrayList();

    public ModuleManager() {
        this.modules.clear();
        this.modules.add(new AntiBot());
        this.modules.add(new AimPistol());
        this.modules.add(new Velocity());
        this.modules.add(new Sprint());
        this.modules.add(new GuiWalk());
        this.modules.add(new strafe());
        this.modules.add(new Jesus());
        this.modules.add(new ESP());
        this.modules.add(new Tracers());
        this.modules.add(new FullBright());
        this.modules.add(new NameTags());
        this.modules.add(new WallHack());
        this.modules.add(new ShkafRender());
        this.modules.add(new XRay());
        this.modules.add(new Clip());
        this.modules.add(new Blink());
        this.modules.add(new TestModule());
        this.modules.add(new SelfDestruct());
        this.modules.add(new MCF());
        this.modules.add(new ClickGUI());
        this.modules.add(new HUD());
        this.modules.add(new ArmorHUD());
        this.modules.add(new cartridgesHUD());
        this.modules.add(new Notifications());
        for (Module module : this.modules) {
        }
    }

    public static ArrayList<Class<?>> getClasses(String packageName) {
        ArrayList classes = new ArrayList();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            for (ClassPath.ClassInfo info : ClassPath.from((ClassLoader)loader).getAllClasses()) {
                if (!info.getName().startsWith(packageName)) continue;
                classes.add(info.load());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    public Module getModule(String name) {
        for (Module m : this.modules) {
            if (!m.getName().equalsIgnoreCase(name)) continue;
            return m;
        }
        return null;
    }

    public ArrayList<Module> getModuleList() {
        return this.modules;
    }

    public ArrayList<Module> getModulesInCategory(Category c) {
        ArrayList<Module> mods = new ArrayList<Module>();
        for (Module m : this.modules) {
            if (m.getCategory() != c) continue;
            mods.add(m);
        }
        return mods;
    }
}

