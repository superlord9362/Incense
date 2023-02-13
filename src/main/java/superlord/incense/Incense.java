package superlord.incense;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import superlord.incense.config.ConfigHolder;
import superlord.incense.config.IncenseConfig;
import superlord.incense.init.BlockEntityInit;
import superlord.incense.init.BlockInit;
import superlord.incense.init.EffectInit;
import superlord.incense.init.ItemInit;

@Mod(Incense.MOD_ID)
@Mod.EventBusSubscriber(modid = Incense.MOD_ID)
public class Incense {

    public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "incense";
	
	public Incense() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
		BlockInit.REGISTER.register(bus);
		ItemInit.REGISTER.register(bus);
		EffectInit.EFFECTS.register(bus);
		BlockEntityInit.REGISTER.register(bus);
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.COMMON_SPEC, "incense.toml");
	}
	
	@SubscribeEvent
    public void onModConfigEvent(final ModConfigEvent event) {
        final ModConfig config = event.getConfig();
        if (config.getSpec() == ConfigHolder.COMMON_SPEC) {
            IncenseConfig.bake(config);
        }
    }

}
