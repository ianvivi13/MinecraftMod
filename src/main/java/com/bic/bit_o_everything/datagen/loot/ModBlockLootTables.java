package com.bic.bit_o_everything.datagen.loot;

import com.bic.bit_o_everything.block.ModBlocks;
import com.bic.bit_o_everything.item.ModItems;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends BlockLoot {

    @Override
    protected void addTables() {
        this.dropSelf(ModBlocks.PYRITE_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_PYRITE_BLOCK.get());
        this.dropSelf(ModBlocks.ASPHALT.get());

        this.add(ModBlocks.DEEPSLATE_PYRITE_ORE.get(), (block) -> createOreDrop(ModBlocks.DEEPSLATE_PYRITE_ORE.get(), ModItems.RAW_PYRITE.get()));
        this.add(ModBlocks.PYRITE_ORE.get(), (block) -> createOreDrop(ModBlocks.PYRITE_ORE.get(), ModItems.RAW_PYRITE.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
