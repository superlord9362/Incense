package superlord.incense.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.ZombifiedPiglin;

public class RelaxedEffect extends MobEffect {
	public RelaxedEffect(MobEffectCategory typeIn, int liquidColorIn) {
		super(typeIn, liquidColorIn);
	}

	public boolean isDurationEffectTick(int duration, int amplifier) {
		int k = 50 >> amplifier;
		if (k > 0) {
			return duration % k == 0;
		} else {
			return true;
		}
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int p_19468_) {
		if (entity instanceof CaveSpider caveSpider) {
			caveSpider.targetSelector.removeAllGoals();
		}
		if (entity instanceof Spider spider) {
			spider.targetSelector.removeAllGoals();
		}
		if (entity instanceof EnderMan enderman) {
			enderman.targetSelector.removeAllGoals();
		}
		if (entity instanceof Llama llama) {
			llama.targetSelector.removeAllGoals();
		}
		if (entity instanceof Ocelot ocelot) {
			ocelot.targetSelector.removeAllGoals();
		}
		if (entity instanceof PolarBear polarBear) {
			polarBear.targetSelector.removeAllGoals();
		}
		if (entity instanceof ZombifiedPiglin piglin) {
			piglin.targetSelector.removeAllGoals();
		}
		if (entity instanceof Panda panda) {
			panda.targetSelector.removeAllGoals();
		}
		if (entity instanceof Wolf wolf) {
			wolf.targetSelector.removeAllGoals();
		}
		if (entity instanceof IronGolem golem) {
			golem.targetSelector.removeAllGoals();
		}
		if (entity instanceof Bee bee) {
			bee.targetSelector.removeAllGoals();
		}
		if (entity instanceof Cat cat) {
			cat.targetSelector.removeAllGoals();
		}
		if (entity instanceof Silverfish silverfish) {
			silverfish.targetSelector.removeAllGoals();
		}
	}
}
