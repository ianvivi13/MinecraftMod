package com.bic.bit_o_everything.item.custom;

import com.bic.bit_o_everything.entity.projectile.AbstractThrowableExplosive;
import com.bic.bit_o_everything.entity.projectile.StickyGrenade;
import com.bic.bit_o_everything.item.ModItems;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraft.world.item.Item.Properties;

public class StickyGrenadeItem extends AbstractThrowableExplosiveItem {

    public StickyGrenadeItem(Properties properties) {
        super(properties);
    }

    @Override
    AbstractThrowableExplosive createBoom(Level pLevel, Player pPlayer) {
        ItemStack istack = new ItemStack(ModItems.STICKY_DETONATOR.get());
        return new StickyGrenade(pLevel, pPlayer,pPlayer.getInventory().contains(istack));
    }

}
