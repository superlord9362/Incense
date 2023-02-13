package superlord.incense.common.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Bee;

public class BeeHurtByOtherGoal extends HurtByTargetGoal {
    Bee bee;
	
	public BeeHurtByOtherGoal(Bee bee) {
       super(bee);
       this.bee = bee;
    }

    public boolean canContinueToUse() {
       return bee.isAngry() && super.canContinueToUse();
    }

    protected void alertOther(Mob p_28035_, LivingEntity p_28036_) {
       if (p_28035_ instanceof Bee && this.mob.hasLineOfSight(p_28036_)) {
          p_28035_.setTarget(p_28036_);
       }

    }
 }