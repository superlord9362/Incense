package superlord.incense.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.incense.Incense;
import superlord.incense.common.effect.RelaxedEffect;

public class EffectInit {
	
	public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Incense.MOD_ID);

	public static final RegistryObject<MobEffect> RELAXED = EFFECTS.register("relaxed", () -> new RelaxedEffect(MobEffectCategory.NEUTRAL, 0xB77BAB));

}
