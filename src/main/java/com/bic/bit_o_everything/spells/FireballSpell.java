package com.bic.bit_o_everything.spells;

import com.bic.bit_o_everything.sound.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class FireballSpell extends AbstractSpell{
    private static final int color = Mth.color(230,76,0);

    @Override
    public SoundEvent getSound() {
        return ModSounds.SPELL_FIREBALL.get();
    }

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
    public void castSpellEmpty(Level level, Player player) {
        player.sendSystemMessage(Component.literal("This is " + spellName() + " from castSpellEmpty"));
    }

    @Override
    public void castSpellEntity(Player player, LivingEntity livingEntity) {
        player.sendSystemMessage(Component.literal("This is " + spellName() + " from castSpellEntity"));
    }

    @Override
    public void castSpellBlock(UseOnContext useOnContext) {
        useOnContext.getPlayer().sendSystemMessage(Component.literal("This is " + spellName() + " from castSpellBlock"));
    }
}
