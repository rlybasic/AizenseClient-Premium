/*
 * Decompiled with CFR 0.150.
 */
package new_gui.setting;

import java.util.ArrayList;
import new_gui.setting.Setting;
import ru.aizensense.module.Module;

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
        for (Setting set : this.getSettings()) {
            if (!set.getName().equalsIgnoreCase(name)) continue;
            return set;
        }
        return null;
    }
}

