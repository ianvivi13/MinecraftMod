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
import java.util.function.Supplier;

public class FireballSpell implements AbstractSpell {
    public static final Supplier<? extends AbstractSpell> FIREBALL = FireballSpell::new;

    private static final int color = Mth.color(230,76,0);

    public SoundEvent getSound() {
        return ModSounds.SPELL_FIREBALL.get();
    }

    public int spellColor() {
        return color;
    }

    public int cooldownTime() {
        return 20;
    }

    public int xpConsumed() {
        return 100;
    }

    public String spellName() {
        return "Fireball";
    }

    public String getDescription() {
        return "Summons a fireball at your enemies";
    }

    public SimpleParticleType getParticles() {
        return ParticleTypes.SMALL_FLAME;
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
