package com.bic.bit_o_everything.datagen.loot;

import net.minecraft.data.loot.ChestLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.function.BiConsumer;

public class ModChestLootTables extends ChestLoot {

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> p_124363_) {
        super.accept(p_124363_);
    }
}
