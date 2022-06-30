package com.bic.bit_o_everything.item.custom;

import com.bic.bit_o_everything.spells.AbstractSpell;
import com.bic.bit_o_everything.spells.SpellList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class SpellItem extends Item {
    public Supplier<? extends AbstractSpell> RS;

    public SpellItem(Properties pProperties, int relatedSpell) {
        super(pProperties);
        this.RS = SpellList.SPELLS.get(relatedSpell);
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }
}
