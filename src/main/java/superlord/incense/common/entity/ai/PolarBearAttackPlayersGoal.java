package superlord.incense.common.entity.ai;

import java.util.function.Predicate;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.player.Player;

public class PolarBearAttackPlayersGoal extends NearestAttackableTargetGoal<Player> {
	PolarBear polarBear;
	
    public PolarBearAttackPlayersGoal(PolarBear polarBear) {
       super(polarBear, Player.class, 20, true, true, (Predicate<LivingEntity>)null);
       this.polarBear = polarBear;
    }

    public boolean canUse() {
       if (polarBear.isBaby()) {
          return false;
       } else {
          if (super.canUse()) {
             for(PolarBear polarbear : polarBear.level.getEntitiesOfClass(PolarBear.class, polarBear.getBoundingBox().inflate(8.0D, 4.0D, 8.0D))) {
                if (polarbear.isBaby()) {
                   return true;
                }
             }
          }

          return false;
       }
    }

    protected double getFollowDistance() {
       return super.getFollowDistance() * 0.5D;
    }
 }