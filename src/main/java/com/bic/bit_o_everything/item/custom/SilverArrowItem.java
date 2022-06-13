package com.bic.bit_o_everything.item.custom;

import com.bic.bit_o_everything.entity.projectile.SilverArrow;
import com.bic.bit_o_everything.item.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SilverArrowItem extends ArrowItem {
    public final float damage;

    public SilverArrowItem(Item.Properties properties, float damage) {
        super(properties);
        this.damage = damage;
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        SilverArrow arrow = new SilverArrow(pShooter, pLevel, ModItems.SILVER_ARROW.get());
        arrow.setBaseDamage(this.damage);
        return arrow;
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
        return false;
    }
}
