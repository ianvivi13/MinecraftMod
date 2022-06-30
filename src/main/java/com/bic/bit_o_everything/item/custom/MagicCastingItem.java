package com.bic.bit_o_everything.item.custom;

import com.bic.bit_o_everything.sound.ModSounds;
import com.bic.bit_o_everything.spells.AbstractSpell;
import com.bic.bit_o_everything.spells.SpellList;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class MagicCastingItem extends Item {
    public static final String SPELL_TAG = "StoredSpells";
    public static final String CURRENT_TAG = "CurrentSpell";

    public final int MAX_SPELLS;
    public final float COOLDOWN_MOD;
    public final float XP_MOD;

    /*
    textures
    particles
    sounds:
        - failed cast
        - each spell
        - incantation success
        - incantation failed
     */

    public MagicCastingItem(Properties pProperties, int maxSpells, float xpModifier, float cooldownModifier) {
        super(pProperties);
        this.MAX_SPELLS = maxSpells;
        this.COOLDOWN_MOD = cooldownModifier;
        this.XP_MOD = xpModifier;
    }

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
            AbstractSpell spell = SpellList.SPELLS.get(i).get();
            TextColor tc = TextColor.fromRgb(spell.spellColor());
            pTooltipComponents.add(Component.literal(spell.spellName()).setStyle(Style.EMPTY.withColor(tc)));
        }

    }

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
            return SpellList.SPELLS.get(spells[getCurrentSpell(spellCaster)]).get();
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

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack pStack, ItemStack pOther, Slot pSlot, ClickAction pAction, Player pPlayer, SlotAccess pAccess) {
        if (pOther.getItem() instanceof SpellItem spellItem) {
            if (this.addSpell(pStack, spellItem.RS)) {
                pOther.shrink(1);
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

    public static void setSpells(ItemStack spellCaster, int[] arr) {
        CompoundTag compoundTag = getCompoundTag(spellCaster);
        compoundTag.putIntArray(SPELL_TAG, arr);
        spellCaster.setTag(compoundTag);
    }

    // return true if can add spell, false if not
    public boolean addSpell(ItemStack spellCaster, Supplier<? extends AbstractSpell> spell) {
        int newSpellId = SpellList.SPELLS.indexOf(spell);
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


    public void playSoundPlayer(Level pLevel, Player pPlayer, SoundEvent sound) {
        pLevel.playSound(pPlayer, pPlayer.blockPosition(), sound, SoundSource.PLAYERS, 1, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
    }

    public void playSoundServer(Level pLevel, Player pPlayer, SoundEvent sound) {
        pLevel.playSound(null, pPlayer.blockPosition(), sound, SoundSource.PLAYERS, 1, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {
            if (pPlayer.isShiftKeyDown()) {
                scrollUp(pPlayer.getMainHandItem());
                AbstractSpell spell = getCurrentSpellObject(pPlayer.getMainHandItem());
                if (spell != null) {
                    TextColor tc = TextColor.fromRgb(spell.spellColor());
                    pPlayer.sendSystemMessage(Component.literal("Current Spell: ").append(Component.literal(spell.spellName()).setStyle(Style.EMPTY.withColor(tc))));
                }
            } else {
                AbstractSpell spell = getCurrentSpellObject(pPlayer.getMainHandItem());
                if (spell != null) {
                    int totXp = (int) (this.XP_MOD * spell.xpConsumed());
                    if (pPlayer.totalExperience >= totXp) {
                        spell.castSpell(pLevel, pPlayer, pUsedHand);
                        pPlayer.giveExperiencePoints(-totXp);
                        playSoundServer(pLevel, pPlayer, spell.getSound());
                        pPlayer.getCooldowns().addCooldown(this, (int) (this.COOLDOWN_MOD * spell.cooldownTime()));
                    } else {
                        TextColor tc = TextColor.fromRgb(spell.spellColor());
                        playSoundServer(pLevel, pPlayer, ModSounds.CAST_FAILED.get());
                        pPlayer.sendSystemMessage(Component.literal("Not enough experience to cast ").append(Component.literal(spell.spellName()).setStyle(Style.EMPTY.withColor(tc))));
                    }
                }
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

}
