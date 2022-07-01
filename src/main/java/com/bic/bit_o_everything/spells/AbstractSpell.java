package com.bic.bit_o_everything.spells;

import com.bic.bit_o_everything.sound.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
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
        return "Abstract";
    }

    public void castSpellEmpty(Level level, Player player) {
        player.sendSystemMessage(Component.literal("This is " + spellName() + " from castSpellEmpty"));
    }

    public void castSpellEntity(Player player, LivingEntity livingEntity) {
        player.sendSystemMessage(Component.literal("This is " + spellName() + " from castSpellEntity"));
    }

    public void castSpellBlock(UseOnContext useOnContext) {
        useOnContext.getPlayer().sendSystemMessage(Component.literal("This is " + spellName() + " from castSpellBlock"));
    }
}
