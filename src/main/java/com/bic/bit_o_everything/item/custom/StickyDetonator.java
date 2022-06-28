package com.bic.bit_o_everything.item.custom;

import com.bic.bit_o_everything.entity.projectile.StickyGrenade;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;


public class StickyDetonator extends Item {
    final static int radius = 22;
    final static int maxExplosions = 16;

    public StickyDetonator(Properties tab) {
        super(tab);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        Vec3 start = pPlayer.getPosition(0f).add(-radius,-radius,-radius);
        Vec3 end = pPlayer.getPosition(0f).add(radius,radius,radius);
        AABB aabb = new AABB(start,end);
        int numExplosions = 0;
        for (StickyGrenade nearby : pLevel.getEntitiesOfClass(StickyGrenade.class, aabb)) {
            if (numExplosions >= maxExplosions) {
                break;
            }
            nearby.explodeSelf();
            numExplosions ++;
        }
        return super.use(pLevel, pPlayer, pUsedHand);
}



}
