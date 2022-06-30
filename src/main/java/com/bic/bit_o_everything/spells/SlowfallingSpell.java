package com.bic.bit_o_everything.spells;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SlowfallingSpell extends AbstractSpell{
    private static final int color = Mth.color(255,230,238);

    @Override
    public final int spellColor() {
        return color;
    }

    @Override
    public final int cooldownTime() {
        return 2;
    }

    @Override
    public final int xpConsumed() {
        return 1;
    }

    @Override
    public String spellName() {
        return "Slowfalling";
    }

    @Override
    public void castSpell(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.sendSystemMessage(Component.literal("Casting: " + spellName()));
    }
}
