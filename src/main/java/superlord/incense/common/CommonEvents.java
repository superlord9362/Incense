package superlord.incense.common;

import java.util.function.Predicate;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.DefendVillageTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.Bee.BeeBecomeAngryTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Spider.SpiderTargetGoal;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import superlord.incense.Incense;
import superlord.incense.common.entity.ai.BeeHurtByOtherGoal;
import superlord.incense.common.entity.ai.PolarBearAttackPlayersGoal;
import superlord.incense.common.entity.ai.PolarBearHurtByTargetGoal;
import superlord.incense.init.EffectInit;

@Mod.EventBusSubscriber(modid = Incense.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {

	@SuppressWarnings("static-access")
	@SubscribeEvent
	public static void onEntityEndRelaxation(LivingUpdateEvent event) {
		if (event.getEntity() instanceof Spider spider) {
			if (!spider.hasEffect(EffectInit.RELAXED.get())) {
				spider.targetSelector.addGoal(1, new HurtByTargetGoal(spider));
				spider.targetSelector.addGoal(2, new Spider.SpiderTargetGoal<>(spider, Player.class));
				spider.targetSelector.addGoal(3, new Spider.SpiderTargetGoal<>(spider, IronGolem.class));
			}
		}
		if (event.getEntity() instanceof CaveSpider cavespider) {
			if (!cavespider.hasEffect(EffectInit.RELAXED.get())) {
				cavespider.targetSelector.addGoal(1, new HurtByTargetGoal(cavespider));
				cavespider.targetSelector.addGoal(2, new SpiderTargetGoal<>(cavespider, Player.class));
				cavespider.targetSelector.addGoal(3, new SpiderTargetGoal<>(cavespider, IronGolem.class));
			}
		}
		if (event.getEntity() instanceof EnderMan enderman) {
			if (!enderman.hasEffect(EffectInit.RELAXED.get())) {
				enderman.targetSelector.addGoal(1, new EnderMan.EndermanLookForPlayerGoal(enderman, enderman::isAngryAt));
				enderman.targetSelector.addGoal(2, new HurtByTargetGoal(enderman));
				enderman.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(enderman, Endermite.class, true, false));
				enderman.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(enderman, false));
			}
		}
		if (event.getEntity() instanceof Llama llama) {
			if (!llama.hasEffect(EffectInit.RELAXED.get())) {
				llama.targetSelector.addGoal(1, new Llama.LlamaHurtByTargetGoal(llama));
				llama.targetSelector.addGoal(2, new Llama.LlamaAttackWolfGoal(llama));
			}
		}
		if (event.getEntity() instanceof Ocelot ocelot) {
			if (!ocelot.hasEffect(EffectInit.RELAXED.get())) {
				ocelot.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(ocelot, Chicken.class, false));
				ocelot.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(ocelot, Turtle.class, 10, false, false, Turtle.BABY_ON_LAND_SELECTOR));
			}
		}
		if (event.getEntity() instanceof PolarBear bear) {
			if (!bear.hasEffect(EffectInit.RELAXED.get())) {
				bear.targetSelector.addGoal(1, new PolarBearHurtByTargetGoal(bear));
				bear.targetSelector.addGoal(2, new PolarBearAttackPlayersGoal(bear));
				bear.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(bear, Player.class, 10, true, false, bear::isAngryAt));
				bear.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(bear, Fox.class, 10, true, true, (Predicate<LivingEntity>)null));
				bear.targetSelector.addGoal(5, new ResetUniversalAngerTargetGoal<>(bear, false));
			}
		}
		if (event.getEntity() instanceof ZombifiedPiglin piglin) {
			if (!piglin.hasEffect(EffectInit.RELAXED.get())) {
				piglin.targetSelector.addGoal(1, (new HurtByTargetGoal(piglin)).setAlertOthers());
				piglin.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(piglin, Player.class, 10, true, false, piglin::isAngryAt));
				piglin.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(piglin, true));
			}
		}
		if (event.getEntity() instanceof Panda panda) {
			if (!panda.hasEffect(EffectInit.RELAXED.get())) {
				panda.targetSelector.addGoal(1, (new Panda.PandaHurtByTargetGoal(panda)).setAlertOthers(new Class[0]));
			}
		}
		if (event.getEntity() instanceof Wolf wolf) {
			if (!wolf.hasEffect(EffectInit.RELAXED.get())) {
				wolf.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(wolf));
				wolf.targetSelector.addGoal(2, new OwnerHurtTargetGoal(wolf));
				wolf.targetSelector.addGoal(3, (new HurtByTargetGoal(wolf)).setAlertOthers());
				wolf.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(wolf, Player.class, 10, true, false, wolf::isAngryAt));
				wolf.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(wolf, Animal.class, false, wolf.PREY_SELECTOR));
				wolf.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(wolf, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
				wolf.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(wolf, AbstractSkeleton.class, false));
				wolf.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(wolf, true));
			}
		}
		if (event.getEntity() instanceof IronGolem golem) {
			if (!golem.hasEffect(EffectInit.RELAXED.get())) {
				golem.targetSelector.addGoal(1, new DefendVillageTargetGoal(golem));
				golem.targetSelector.addGoal(2, new HurtByTargetGoal(golem));
				golem.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(golem, Player.class, 10, true, false, golem::isAngryAt));
				golem.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(golem, Mob.class, 5, false, false, (p_28879_) -> {
					return p_28879_ instanceof Enemy && !(p_28879_ instanceof Creeper);
				}));
				golem.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(golem, false));
			}
		}
		if (event.getEntity() instanceof Bee bee) {
			if (!bee.hasEffect(EffectInit.RELAXED.get())) {
				bee.targetSelector.addGoal(1, (new BeeHurtByOtherGoal(bee)).setAlertOthers(new Class[0]));
				bee.targetSelector.addGoal(2, new BeeBecomeAngryTargetGoal(bee));
				bee.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(bee, true));
			}
		}
		if (event.getEntity() instanceof Cat cat) {
			if (!cat.hasEffect(EffectInit.RELAXED.get())) {
				cat.targetSelector.addGoal(1, new NonTameRandomTargetGoal<>(cat, Rabbit.class, false, (Predicate<LivingEntity>)null));
				cat.targetSelector.addGoal(1, new NonTameRandomTargetGoal<>(cat, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
			}
		}
		if (event.getEntity() instanceof Silverfish silverfish) {
			if (!silverfish.hasEffect(EffectInit.RELAXED.get())) {
				silverfish.targetSelector.addGoal(1, (new HurtByTargetGoal(silverfish)).setAlertOthers());
				silverfish.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(silverfish, Player.class, true));
			}
		}
	}
	
	@SubscribeEvent
	public static void onWorldTick(WorldTickEvent event) {
		//System.out.println("Hello!");
	}

}
