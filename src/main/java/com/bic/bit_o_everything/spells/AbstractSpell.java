package com.bic.bit_o_everything.spells;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import java.util.ArrayList;
import java.util.function.Supplier;

public interface AbstractSpell {
    /**
     * @return Name of this spell
     */
    String spellName();

    /**
     * The {@link AbstractSpell#spellName()} will automatically be appended with the {@link AbstractSpell#spellColor()}
     * @return Description of this spell
     */
    String getDescription();

    /**
     * @return Int color of this spell - use {@link Mth#color(int, int, int)}
     */
    int spellColor();

    /**
     * @return Default experience points consumed
     */
    int xpConsumed();

    /**
     * @return Default cooldown time of this spell
     */
    int cooldownTime();

    /**
     * @return Spell success sound
     */
    SoundEvent getSound();

    /**
     * @return Spell success particles
     */
    SimpleParticleType getParticles();

    /**
     * Called when the player casts the spell on a Block - Server Side Only
     * @return True normally - False to cancel the cast
     */
    boolean castSpellBlock(UseOnContext useOnContext);

    /**
     * Called when the player casts the spell on an LivingEntity - Server Side Only
     * @return True normally - False to cancel the cast
     */
    boolean castSpellEntity(Player player, LivingEntity livingEntity);

    /**
     * Called when the player casts the spell into the air - Server Side Only
     * @return True normally - False to cancel the cast
     */
    boolean castSpellEmpty(Level level, Player player);

    /**
     * Current list of spells suppliers. Used as indexes by {@link com.bic.bit_o_everything.item.custom.MagicCastingItem castingItems} to determine spells
     * <br>
     * Reference {@link FireballSpell#FIREBALL FIREBALL} for an example
     */
    ArrayList<Supplier<? extends AbstractSpell>> SPELLS = new ArrayList<>(){{
        add(FireballSpell.FIREBALL);
        add(SlowfallingSpell.SLOWFALLING);
    }};
}
