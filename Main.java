/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 */
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import ru.aizensense.AizenSense;

@Mod(modid="aizensense", version="V0.1", name="AizenSense")
public class Main {
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        AizenSense.instance = new AizenSense();
        AizenSense.instance.init();
    }
}

