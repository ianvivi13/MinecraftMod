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
import java.util.function.Supplier;

public class SpellItem extends Item {
    public Supplier<? extends AbstractSpell> RS;

    public SpellItem(Properties pProperties, Supplier<? extends AbstractSpell> relatedSpell) {
        super(pProperties);
        this.RS = relatedSpell;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        AbstractSpell spell = RS.get();
        pTooltipComponents.add(Component.literal(spell.spellName()).setStyle(Style.EMPTY.withColor(spell.spellColor())));
        pTooltipComponents.add(Component.literal(spell.getDescription()));
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }
}
