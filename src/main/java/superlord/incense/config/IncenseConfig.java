package superlord.incense.config;

import net.minecraftforge.fml.config.ModConfig;
import superlord.incense.Incense;

public class IncenseConfig {
	
	public static int incenseRadius = 4;
	public static boolean showPotionBubblesOnPlayer = false;
	public static boolean showPotionBubblesOnNonPlayerEntities = false;
	public static boolean superSecretSettings = false;

	public static void bake(ModConfig config) {
		try {
			incenseRadius = ConfigHolder.COMMON.incenseRadius.get();
			showPotionBubblesOnPlayer = ConfigHolder.COMMON.showPotionBubblesOnPlayer.get();
			showPotionBubblesOnNonPlayerEntities = ConfigHolder.COMMON.showPotionBubblesOnNonPlayerEntities.get();
			superSecretSettings = ConfigHolder.COMMON.superSecretSettings.get();
		} catch (Exception e) {
			Incense.LOGGER.warn("An exeption was caused trying to load the config for Incense.");
			e.printStackTrace();
		}
	}

}
