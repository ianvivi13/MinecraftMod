package com.bic.bit_o_everything.datagen;


import com.bic.bit_o_everything.BitOEverything;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final class Blocks {
        public static final TagKey<Block> CHERRY_LOGS = tag("cherry_logs");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(BitOEverything.MOD_ID, name));
        }

    }


    public static final class Items {
        public static final TagKey<Item> CHERRY_LOGS = tag("cherry_logs");
        public static final TagKey<Item> CONCRETES = tag("concretes");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(BitOEverything.MOD_ID, name));
        }
    }
}
