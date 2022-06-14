package com.bic.bit_o_everything.entity.projectile;

import com.bic.bit_o_everything.entity.ModEntityTypes;
import com.bic.bit_o_everything.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class Grenade extends AbstractThrowableExplosive {

    public Grenade(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public Grenade(Level pLevel, Player pPlayer) {
        super(ModEntityTypes.GRENADE.get(), pPlayer, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.GRENADE.get();
    }

    @Override
    protected float getExpRadius() {
        return 10f;
    }


}
