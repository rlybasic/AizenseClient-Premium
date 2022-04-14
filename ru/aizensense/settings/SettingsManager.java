/*
 * Decompiled with CFR 0.150.
 */
package ru.aizensense.settings;

import java.util.ArrayList;
import ru.aizensense.module.Module;
import ru.aizensense.settings.Setting;

public class SettingsManager {
    private ArrayList<Setting> settings = new ArrayList();

    public void rSetting(Setting in) {
        this.settings.add(in);
    }

    public ArrayList<Setting> getSettings() {
        return this.settings;
    }

    public ArrayList<Setting> getSettingsByMod(Module mod) {
        ArrayList<Setting> out = new ArrayList<Setting>();
        for (Setting s : this.getSettings()) {
            if (!s.getParentMod().equals(mod)) continue;
            out.add(s);
        }
        if (out.isEmpty()) {
            return null;
        }
        return out;
    }

    public Setting getSettingByName(String name) {
        for (Setting setting : this.getSettings()) {
            if (!setting.getName().equalsIgnoreCase(name)) continue;
            return setting;
        }
        return null;
    }

    public Setting getSettingByName(Module mod, String name) {
        for (Setting set : this.getSettings()) {
            if (!set.getName().equalsIgnoreCase(name) || set.getParentMod() != mod) continue;
            return set;
        }
        return null;
    }
}

