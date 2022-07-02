package com.bic.bit_o_everything.spells;

import com.bic.bit_o_everything.sound.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class SlowfallingSpell implements AbstractSpell{
    public static final AbstractSpell SLOWFALLING = new SlowfallingSpell();

    private static final int color = Mth.color(255,230,238);

    public SoundEvent getSound() {
        return ModSounds.SPELL_SLOWFALLING.get();
    }

    public int spellColor() {
        return color;
    }

    public int cooldownTime() {
        return 2;
    }

    public int xpConsumed() {
        return 1;
    }

    public String spellName() {
        return "Slowfalling";
    }

    public String getDescription() {
        return "Gives the player or entity slowfalling. If cast on a block, an area of effect cloud will be created";
    }

    public SimpleParticleType getParticles() {
        return ParticleTypes.EFFECT;
    }

    public boolean castSpellEmpty(Level level, Player player) {
        return true;
    }

    public boolean castSpellEntity(Player player, LivingEntity livingEntity) {
        return true;
    }

    public boolean castSpellBlock(UseOnContext useOnContext) {
        return true;
    }
}
