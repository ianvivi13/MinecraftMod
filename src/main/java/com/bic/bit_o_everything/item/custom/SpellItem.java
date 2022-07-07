package com.bic.bit_o_everything.item.custom;

import com.bic.bit_o_everything.spells.AbstractSpell;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class SpellItem extends Item {
    public AbstractSpell SPELL;

    public SpellItem(Properties pProperties, AbstractSpell relatedSpell) {
        super(pProperties);
        this.SPELL = relatedSpell;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal(SPELL.spellName()).setStyle(Style.EMPTY.withColor(SPELL.spellColor())));
        pTooltipComponents.add(Component.literal(SPELL.getDescription()));
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }
}
