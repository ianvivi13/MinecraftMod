package com.bic.bit_o_everything.item.custom;

import com.bic.bit_o_everything.entity.projectile.AbstractThrowableExplosive;
import com.bic.bit_o_everything.entity.projectile.Grenade;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class GrenadeItem extends AbstractThrowableExplosiveItem {

    public GrenadeItem(Properties properties) {
        super(properties);
    }

    @Override
    AbstractThrowableExplosive createBoom(Level pLevel, Player pPlayer) {
        return new Grenade(pLevel, pPlayer);
    }

}
