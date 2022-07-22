package com.bic.bit_o_everything.item.custom;

import com.bic.bit_o_everything.item.ModItems;
import com.bic.bit_o_everything.entity.projectile.TimedArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraft.world.item.Item.Properties;

public class TimedArrowItem extends ArrowItem {
    public final float damage;
    public double time;

    public TimedArrowItem(Properties properties, float damage, double v) {
        super(properties);
        this.damage = damage;
        time = v;
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        TimedArrow arrow = new TimedArrow(pShooter, pLevel, ModItems.EXPLOSIVE_ARROW.get(), time);
        arrow.setBaseDamage(this.damage);
        return arrow;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
        return false;
    }
}
