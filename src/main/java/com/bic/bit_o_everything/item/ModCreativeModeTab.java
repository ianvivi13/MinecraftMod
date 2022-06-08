package com.bic.bit_o_everything.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab MODDED = new CreativeModeTab("modded") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.PYRITE.get());
        }
    };
}
