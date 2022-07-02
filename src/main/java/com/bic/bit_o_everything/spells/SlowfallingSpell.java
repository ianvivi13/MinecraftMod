package com.bic.bit_o_everything.spells;

import com.bic.bit_o_everything.particle.ModParticles;
import com.bic.bit_o_everything.sound.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class SlowfallingSpell extends AbstractSpell{
    private static final int color = Mth.color(255,230,238);

    @Override
    public SoundEvent getSound() {
        return ModSounds.SPELL_SLOWFALLING.get();
    }

    @Override
    public int spellColor() {
        return color;
    }

    @Override
    public int cooldownTime() {
        return 2;
    }

    @Override
    public int xpConsumed() {
        return 1;
    }

    @Override
    public String spellName() {
        return "Slowfalling";
    }

    @Override
    public String getDescription() {
        return "Gives the player or entity slowfalling. If cast on a block, an area of effect cloud will be created";
    }

    @Override
    public SimpleParticleType getParticles() {
        return ParticleTypes.EFFECT;
    }

    @Override
    public boolean castSpellEmpty(Level level, Player player) {
        return true;
    }

    @Override
    public boolean castSpellEntity(Player player, LivingEntity livingEntity) {
        return true;
    }

    @Override
    public boolean castSpellBlock(UseOnContext useOnContext) {
        return true;
    }
}
