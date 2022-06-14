package com.bic.bit_o_everything.entity.projectile;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractThrowableExplosive extends ThrowableItemProjectile {

    public AbstractThrowableExplosive(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public AbstractThrowableExplosive(EntityType<? extends ThrowableItemProjectile> entityType, LivingEntity livingEntity, Level level) {
        super(entityType, livingEntity, level);
    }


    protected float getExpRadius() {
        return 4f;
    }

    protected Explosion.BlockInteraction getBlockInteraction() {
        return Explosion.BlockInteraction.BREAK;
    }

    public void explode(Vec3 location) {
        if(!this.level.isClientSide) {
            Vec3 vec3 = location.subtract(this.getX(), this.getY(), this.getZ());
            this.setDeltaMovement(vec3);
            Vec3 vec31 = vec3.normalize().scale((double)0.05F);
            double x = this.getX() - vec31.x;
            double y = this.getY() - vec31.y;
            double z = this.getZ() - vec31.z;
            this.level.explode(this, x, y, z, getExpRadius(), getBlockInteraction());
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        explode(blockHitResult.getLocation());
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        explode(pResult.getLocation());
    }

}
