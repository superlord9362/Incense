package superlord.incense.common.entity.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import superlord.incense.config.IncenseConfig;
import superlord.incense.init.BlockEntityInit;
import superlord.incense.init.EffectInit;

public class LitIncenseBlockEntity extends BlockEntity {

	public LitIncenseBlockEntity(BlockPos pos, BlockState state) {
		super(BlockEntityInit.LIT_INCENSE.get(), pos, state);
	}

	@Override
	public BlockEntityType<?> getType() {
		return BlockEntityInit.LIT_INCENSE.get();
	}

	public static void tick(Level level, BlockPos pos, BlockState state, LitIncenseBlockEntity litIncense) {
		MobEffect effect = EffectInit.RELAXED.get();
		for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, new AABB(litIncense.worldPosition).inflate(IncenseConfig.incenseRadius))) {
			MobEffectInstance activeEffect = entity.getEffect(effect);
			if (IncenseConfig.showPotionBubblesOnPlayer && IncenseConfig.showPotionBubblesOnNonPlayerEntities) {
				if (activeEffect == null || activeEffect.getDuration() <= 25) {
					entity.addEffect(new MobEffectInstance(effect, 600, 0, false, true, true));
				}
			}
			else if (IncenseConfig.showPotionBubblesOnNonPlayerEntities && !IncenseConfig.showPotionBubblesOnPlayer) {
				if (entity instanceof Player) {
					if (activeEffect == null || activeEffect.getDuration() <= 25) {
						entity.addEffect(new MobEffectInstance(effect, 600, 0, false, false, true));
					}
				} else {
					if (activeEffect == null || activeEffect.getDuration() <= 25) {
						entity.addEffect(new MobEffectInstance(effect, 600, 0, false, true, true));
					}
				}
			}
			else if (IncenseConfig.showPotionBubblesOnPlayer && !IncenseConfig.showPotionBubblesOnNonPlayerEntities) {
				if (entity instanceof Player) {
					if (activeEffect == null || activeEffect.getDuration() <= 25) {
						entity.addEffect(new MobEffectInstance(effect, 600, 0, false, true, true));
					}
				} else {
					if (activeEffect == null || activeEffect.getDuration() <= 25) {
						entity.addEffect(new MobEffectInstance(effect, 600, 0, false, false, true));
					}
				}
			} else if (!IncenseConfig.showPotionBubblesOnNonPlayerEntities && !IncenseConfig.showPotionBubblesOnPlayer) {
				if (activeEffect == null || activeEffect.getDuration() <= 25) {
					entity.addEffect(new MobEffectInstance(effect, 600, 0, false, false, true));
				}
			}
		}
	}

}
