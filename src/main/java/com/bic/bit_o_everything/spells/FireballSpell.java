package com.bic.bit_o_everything.spells;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class FireballSpell extends AbstractSpell{
    private static final int color = Mth.color(230,76,0);

    @Override
    public final int spellColor() {
        return color;
    }

    @Override
    public final int cooldownTime() {
        return 20;
    }

    @Override
    public final int xpConsumed() {
        return 100;
    }

    @Override
    public String spellName() {
        return "Fireball";
    }

    @Override
    public void castSpell(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.sendSystemMessage(Component.literal("Casting: " + spellName()));
    }
}
