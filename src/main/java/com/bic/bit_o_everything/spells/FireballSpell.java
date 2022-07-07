package com.bic.bit_o_everything.spells;

import com.bic.bit_o_everything.sound.ModSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class FireballSpell implements AbstractSpell {
    public static final AbstractSpell FIREBALL = new FireballSpell();

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

    public boolean castSpellEmpty(Level level, Player player, InteractionHand pUsedHand) {
        Vec3 vec3 = player.getViewVector(1.0F);
        LargeFireball largefireball = new LargeFireball(level, player, vec3.x, vec3.y, vec3.z, 2);
        largefireball.setPos(player.getEyePosition().x + vec3.x, player.getEyePosition().y + vec3.y, player.getEyePosition().z + vec3.z);
        largefireball.xPower *= 2;
        largefireball.yPower *= 2;
        largefireball.zPower *= 2;
        level.addFreshEntity(largefireball);
        return true;
    }

    public boolean castSpellEntity(Player player, LivingEntity livingEntity) {
        livingEntity.setSecondsOnFire(5);
        return true;
    }

    public boolean castSpellBlock(UseOnContext useOnContext) {
        Player player = useOnContext.getPlayer();
        Level level = useOnContext.getLevel();
        BlockPos blockpos = useOnContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        if (!CampfireBlock.canLight(blockstate) && !CandleBlock.canLight(blockstate) && !CandleCakeBlock.canLight(blockstate)) {
            BlockPos blockpos1 = blockpos.relative(useOnContext.getClickedFace());
            if (BaseFireBlock.canBePlacedAt(level, blockpos1, useOnContext.getHorizontalDirection())) {
                level.playSound(player, blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                BlockState blockstate1 = BaseFireBlock.getState(level, blockpos1);
                level.setBlock(blockpos1, blockstate1, 11);
                level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
            } else {
                return false;
            }
        } else {
            level.playSound(player, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            level.setBlock(blockpos, blockstate.setValue(BlockStateProperties.LIT, Boolean.TRUE), 11);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockpos);
        }
        return true;
    }

}
