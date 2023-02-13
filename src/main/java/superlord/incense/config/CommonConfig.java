package superlord.incense.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
	
	public final ForgeConfigSpec.IntValue incenseRadius;
	public final ForgeConfigSpec.BooleanValue showPotionBubblesOnPlayer;
	public final ForgeConfigSpec.BooleanValue showPotionBubblesOnNonPlayerEntities;
	public final ForgeConfigSpec.BooleanValue superSecretSettings;
	
	public CommonConfig(final ForgeConfigSpec.Builder builder) {
		builder.push("general");
		incenseRadius = buildInt(builder, "incenseRadius", "all", IncenseConfig.incenseRadius, 0, Integer.MAX_VALUE, "How many blocks away from Lit Incense can entities be affected by the Relaxed Effect. Default is 4.");
		showPotionBubblesOnPlayer = buildBoolean(builder, "showPotionBubblesOnPlayer", "all", false, "Whether players affected by the Relaxed Effect will have potion bubbles around them. Default is false.");
		showPotionBubblesOnNonPlayerEntities = buildBoolean(builder, "showPotionBubblesOnNonPlayerEntities", "all", false, "Whether non-player entities affected by the Relaxed Effect will have potion bubbles around them. Default is false.");
		builder.push("misc");
		superSecretSettings = buildBoolean(builder, "superSecretSettings", "all", false, "Even I don't know what it does. Default is false.");
	}
	
	private static ForgeConfigSpec.BooleanValue buildBoolean(ForgeConfigSpec.Builder builder, String name, String catagory, boolean defaultValue, String comment) {
        return builder.comment(comment).translation(name).define(name, defaultValue);
    }

    private static ForgeConfigSpec.IntValue buildInt(ForgeConfigSpec.Builder builder, String name, String catagory, int defaultValue, int min, int max, String comment) {
        return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
    }

}
