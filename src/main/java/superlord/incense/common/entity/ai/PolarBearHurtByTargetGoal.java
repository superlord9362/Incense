package superlord.incense.common.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.PolarBear;

public class PolarBearHurtByTargetGoal extends HurtByTargetGoal {
	PolarBear polarBear;
	
	public PolarBearHurtByTargetGoal(PolarBear polarBear) {
       super(polarBear);
       this.polarBear = polarBear;
    }

    public void start() {
       super.start();
       if (polarBear.isBaby()) {
          this.alertOthers();
          this.stop();
       }

    }

    protected void alertOther(Mob p_29580_, LivingEntity p_29581_) {
       if (p_29580_ instanceof PolarBear && !p_29580_.isBaby()) {
          super.alertOther(p_29580_, p_29581_);
       }

    }
 }