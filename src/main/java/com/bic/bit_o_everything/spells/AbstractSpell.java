package com.bic.bit_o_everything.spells;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class AbstractSpell{

    public int spellColor() {
        return 0;
    }

    public int cooldownTime() {
        return 0;
    }

    public int xpConsumed() {
        return 0;
    }

    public String spellName() {
        return null;
    }

    public void castSpell(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        System.out.println("this is the abstract spell");
    }
}
