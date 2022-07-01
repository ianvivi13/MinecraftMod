package com.bic.bit_o_everything.spells;

import com.bic.bit_o_everything.sound.ModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class AbstractSpell{

    public SoundEvent getSound() {
        return ModSounds.CAST_FAILED.get();
    }

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

    public void castSpell(Level pLevel, Player pPlayer) {
        System.out.println("this is the abstract spell");
    }
}
