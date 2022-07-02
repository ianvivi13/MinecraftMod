package com.bic.bit_o_everything.item.custom;

import com.bic.bit_o_everything.item.custom.fancyTypes.EmptyLeftClick;
import com.bic.bit_o_everything.particle.ModParticles;
import com.bic.bit_o_everything.sound.ModSounds;
import com.bic.bit_o_everything.spells.AbstractSpell;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Objects;

public class MagicCastingItem extends Item implements EmptyLeftClick {
    public static final String SPELL_TAG = "StoredSpells";
    public static final String CURRENT_TAG = "CurrentSpell";

    public final int MAX_SPELLS;
    public final float COOLDOWN_MOD;
    public final float XP_MOD;

    /*
    textures
    Left click block does not switch spells (scroll) because it is called too fast
    Consider using a byte[] instead of ints
     */

    public MagicCastingItem(Properties pProperties, int maxSpells, float xpModifier, float cooldownModifier) {
        super(pProperties);
        this.MAX_SPELLS = maxSpells;
        this.COOLDOWN_MOD = cooldownModifier;
        this.XP_MOD = xpModifier;
    }

    //region Sounds & Particles
    public void playSoundPlayer(Level pLevel, Player pPlayer, SoundEvent sound) {
        pLevel.playSound(pPlayer, pPlayer.blockPosition(), sound, SoundSource.PLAYERS, 1, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
    }

    public void playSoundServer(Level pLevel, Player pPlayer, SoundEvent sound) {
        pLevel.playSound(null, pPlayer.blockPosition(), sound, SoundSource.PLAYERS, 1, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
    }

    public void spawnParticles(Level level, Player player, SimpleParticleType particle) {
        Vec3 ne = player.getViewVector(0).normalize().multiply(2.5,2.5,2.5);
        for (int i = 0 ; i < 5 ; i ++) {
            level.addAlwaysVisibleParticle(particle,
                    player.getX()+ne.x, player.getEyeHeight() + player.getY()+ne.y, player.getZ()+ne.z,
                    (Math.random()-0.5)*0.25, (Math.random()-0.5)*0.25, (Math.random()-0.5)*0.25);
        }
    }

    public void spawnFailedParticles(Level level, Player player) {
        spawnParticles(level, player, ModParticles.FAILED_SPELL_PARTICLES.get());
    }

    public void spawnSpellParticles(Level level, Player player, AbstractSpell spell) {
        if (spell != null) {
            spawnParticles(level, player, spell.getParticles());
        }
    }
    //endregion
    //region Inventory Looks
    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return true;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        AbstractSpell spell = getCurrentSpellObject(pStack);
        if (spell != null) {
            return spell.spellColor();
        } else {
            return 0;
        }
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        return Math.round(13* (((float)getCurrentSpell(pStack)+1)/((float)getSpells(pStack).length)));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        int[] spells = getSpells(pStack);
        for (int i: spells) {
            AbstractSpell spell = AbstractSpell.SPELLS.get(i);
            TextColor tc = TextColor.fromRgb(spell.spellColor());
            pTooltipComponents.add(Component.literal(spell.spellName()).setStyle(Style.EMPTY.withColor(tc)));
        }

    }
    //endregion
    //region Scrolling
    public void scrollUp(ItemStack spellCaster) {
        int current = getCurrentSpell(spellCaster);
        int length = getSpells(spellCaster).length;
        if (current >= length - 1) {
            setCurrentSpell(spellCaster, 0);
        } else {
            setCurrentSpell(spellCaster, current + 1);
        }
    }

    public void scrollDown(ItemStack spellCaster) {
        int current = getCurrentSpell(spellCaster);
        int length = getSpells(spellCaster).length;
        if (current - 1 < 0) {
            setCurrentSpell(spellCaster, length-1);
        } else {
            setCurrentSpell(spellCaster, current-1);
        }
    }

    // Handles scrolling through spells - also prints the chat message
    public void scroll(ItemStack stack, Player player) {
        if (!player.getLevel().isClientSide()) {
            if (player.isShiftKeyDown()) {
                scrollDown(stack);
            } else {
                scrollUp(stack);
            }
            AbstractSpell spell = getCurrentSpellObject(stack);
            if (spell != null) {
                TextColor tc = TextColor.fromRgb(spell.spellColor());
                player.sendSystemMessage(Component.literal("Current Spell: ").append(Component.literal(spell.spellName()).setStyle(Style.EMPTY.withColor(tc))));
            }
        }
    }
    //endregion
    //region Tag Stuffs
    public static int getCurrentSpell(ItemStack spellCaster) {
        CompoundTag compoundtag = spellCaster.getTag();
        if (compoundtag == null) {
            return 0;
        } else {
            return compoundtag.getInt(CURRENT_TAG);
        }
    }

    public static AbstractSpell getCurrentSpellObject(ItemStack spellCaster) {
        if (getSpells(spellCaster).length < 1) {
            return null;
        } else {
            int[] spells = getSpells(spellCaster);
            return AbstractSpell.SPELLS.get(spells[getCurrentSpell(spellCaster)]);
        }
    }

    public static void setCurrentSpell(ItemStack spellCaster, int currentSpellIndex) {
        CompoundTag compoundTag = getCompoundTag(spellCaster);
        compoundTag.putInt(CURRENT_TAG, currentSpellIndex);
        spellCaster.setTag(compoundTag);
    }

    public static CompoundTag getCompoundTag(ItemStack spellCaster) {
        CompoundTag compoundtag = spellCaster.getTag();
        return Objects.requireNonNullElseGet(compoundtag, CompoundTag::new);
    }

    public static int[] getSpells(ItemStack spellCaster) {
        CompoundTag compoundtag = spellCaster.getTag();
        if (compoundtag == null) {
            return new int[0];
        } else {
            return compoundtag.getIntArray(SPELL_TAG);
        }
    }

    public static void setSpells(ItemStack spellCaster, int[] arr) {
        CompoundTag compoundTag = getCompoundTag(spellCaster);
        compoundTag.putIntArray(SPELL_TAG, arr);
        spellCaster.setTag(compoundTag);
    }
    //endregion
    //region Stacking & Adding Spells
    // Allows for putting spells onto a magic item
    public boolean stacked(ItemStack meStack, ItemStack otherStack, Player pPlayer) {
        if (otherStack.getItem() instanceof SpellItem spellItem) {
            if (this.addSpell(meStack, spellItem.SPELL)) {
                otherStack.shrink(1);
                playSoundPlayer(pPlayer.getLevel(), pPlayer, ModSounds.INCANTATION_SUCCESS.get());
                return true;
            } else {
                playSoundPlayer(pPlayer.getLevel(), pPlayer, ModSounds.INCANTATION_FAILED.get());
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack pStack, ItemStack pOther, Slot pSlot, ClickAction pAction, Player pPlayer, SlotAccess pAccess) {
        return stacked(pStack, pOther, pPlayer);
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack pStack, Slot pSlot, ClickAction pAction, Player pPlayer) {
        return stacked(pStack, pSlot.getItem(), pPlayer);
    }

    // return true if can add spell, false if not
    public boolean addSpell(ItemStack spellCaster, AbstractSpell spell) {
        int newSpellId = AbstractSpell.SPELLS.indexOf(spell);
        int[] spells = getSpells(spellCaster);
        int currentNumberSpells = spells.length;
        for (int i: spells) { // determines if spell is already on the casting item - if so, returns that it can't be put on
            if(i == newSpellId) {
                return false;
            }
        }
        if (currentNumberSpells >= MAX_SPELLS) {
            return false;
        } else {
            int[] combined = new int[currentNumberSpells + 1];
            System.arraycopy(spells,0,combined,0,currentNumberSpells);
            combined[combined.length-1] = newSpellId;
            setSpells(spellCaster, combined);
            return true;
        }
    }
    //endregion
    //region Adding Left Click & Fixing Block Breaking / Entity Hitting
    @Override
    public void emptyLeftClick(Player player, ItemStack itemStack) {
        scroll(itemStack, player);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        scroll(stack, player);
        return true;
    }

    @Override
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return false;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, Player player) {
        return true;
    }

    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        return -100F;
    }
    //endregion
    //region Casting Spell
    public int getXp(AbstractSpell spell) {
        return (int) (this.XP_MOD * spell.xpConsumed());
    }

    public int getCooldown(AbstractSpell spell) {
        return (int) (this.COOLDOWN_MOD * spell.cooldownTime());
    }

    public void doSpellFailed(Level pLevel, Player pPlayer, AbstractSpell spell, String reason) {
        TextColor tc = TextColor.fromRgb(spell.spellColor());
        playSoundServer(pLevel, pPlayer, ModSounds.CAST_FAILED.get());
        pPlayer.sendSystemMessage(Component.literal(reason).append(Component.literal(spell.spellName()).setStyle(Style.EMPTY.withColor(tc))));
    }

    public void doSpellFailed(Level pLevel, Player pPlayer, AbstractSpell spell) {
        doSpellFailed(pLevel, pPlayer, spell, "Cannot cast the following spell here: ");
    }

    public void doSpellSuccess(Level pLevel, Player pPlayer, AbstractSpell spell) {
        pPlayer.giveExperiencePoints(-getXp(spell));
        playSoundServer(pLevel, pPlayer, spell.getSound());
        pPlayer.getCooldowns().addCooldown(this, getCooldown(spell));
    }

    public boolean canCastSpell(Level pLevel, Player pPlayer, AbstractSpell spell, boolean shouldFailSpell) {
        if ((!pPlayer.getCooldowns().isOnCooldown(this)) && (spell != null)) {
            if (pPlayer.totalExperience >= getXp(spell)) {
                return true;
            } else if (shouldFailSpell) {
                doSpellFailed(pLevel, pPlayer, spell, "Not enough experience to cast ");
            }
        }
        return false;
    }

    @Override // use on entity
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        Level level = pPlayer.getLevel();
        if (itemStack.getItem() instanceof MagicCastingItem) {
            if (!level.isClientSide()) {
                AbstractSpell spell = getCurrentSpellObject(itemStack);
                if (canCastSpell(level, pPlayer, spell, true)) {
                    if (spell.castSpellEntity(pPlayer, pInteractionTarget)) {
                        doSpellSuccess(level, pPlayer, spell);
                    } else {
                        doSpellFailed(level, pPlayer, spell);
                    }
                }
            } else {
                AbstractSpell spell = getCurrentSpellObject(itemStack);
                if (canCastSpell(level, pPlayer, spell, false)) {
                    spawnSpellParticles(level, pPlayer, spell);
                } else {
                    spawnFailedParticles(level, pPlayer);
                }
            }
            return InteractionResult.CONSUME;
        }
        return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
    }

    @Override // use on block
    public InteractionResult useOn(UseOnContext pContext) {
        Player pPlayer = pContext.getPlayer();
        ItemStack itemStack = pPlayer.getItemInHand(pContext.getHand());
        Level level = pPlayer.getLevel();
        if (itemStack.getItem() instanceof MagicCastingItem) {
            if (!level.isClientSide()) {
                AbstractSpell spell = getCurrentSpellObject(itemStack);
                if (canCastSpell(level, pPlayer, spell, true)) {
                    if (spell.castSpellBlock(pContext)) {
                        doSpellSuccess(level, pPlayer, spell);
                    } else {
                        doSpellFailed(level, pPlayer, spell);
                    }
                }
            } else {
                AbstractSpell spell = getCurrentSpellObject(itemStack);
                if (canCastSpell(level, pPlayer, spell, false)) {
                    spawnSpellParticles(level, pPlayer, spell);
                } else {
                    spawnFailedParticles(level, pPlayer);
                }
            }
            return InteractionResult.CONSUME;
        }
        return super.useOn(pContext);
    }

    @Override // use on air
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        if (itemStack.getItem() instanceof MagicCastingItem) {
            if (!pLevel.isClientSide()) {
                AbstractSpell spell = getCurrentSpellObject(itemStack);
                if (canCastSpell(pLevel, pPlayer, spell, true)) {
                    if (spell.castSpellEmpty(pLevel, pPlayer)) {
                        doSpellSuccess(pLevel, pPlayer, spell);
                    } else {
                        doSpellFailed(pLevel, pPlayer, spell);
                    }
                }
            } else {
                AbstractSpell spell = getCurrentSpellObject(itemStack);
                if (canCastSpell(pLevel, pPlayer, spell, false)) {
                    spawnSpellParticles(pLevel, pPlayer, spell);
                } else {
                    spawnFailedParticles(pLevel, pPlayer);
                }
            }
            return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
    //endregion
}
