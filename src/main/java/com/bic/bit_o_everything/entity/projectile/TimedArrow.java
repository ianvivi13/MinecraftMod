package com.bic.bit_o_everything.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class TimedArrow extends ExplosiveArrow {

    private static int expTime;
    private int life;

    public TimedArrow(EntityType<? extends ExplosiveArrow> type, Level level) {
        super(type, level);
    }

    public TimedArrow(LivingEntity shooter, Level level, Item referenceItem, double expTime) {
        super(shooter, level, referenceItem);
        TimedArrow.expTime = (int)(expTime * 20);
    }

    @Override
    public void tick() {
        if(life >= expTime) {
            explode(this.getDeltaMovement());
        }
        super.tick();
        life++;
    }
}
