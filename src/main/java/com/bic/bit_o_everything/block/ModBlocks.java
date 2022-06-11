package com.bic.bit_o_everything.block;

import com.bic.bit_o_everything.BitOEverything;
import com.bic.bit_o_everything.item.ModCreativeModeTab;
import com.bic.bit_o_everything.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BitOEverything.MOD_ID);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    // Create blocks here
    public static final RegistryObject<Block> PYRITE_ORE = registerBlock("pyrite_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> DEEPSLATE_PYRITE_ORE = registerBlock("deepslate_pyrite_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> RAW_PYRITE_BLOCK = registerBlock("raw_pyrite_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> PYRITE_BLOCK = registerBlock("pyrite_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> ZINC_ORE = registerBlock("zinc_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> DEEPSLATE_ZINC_ORE = registerBlock("deepslate_zinc_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> ZINC_BLOCK = registerBlock("zinc_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> RAW_ZINC_BLOCK = registerBlock("raw_zinc_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> MAGNESIUM_ORE = registerBlock("magnesium_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> DEEPSLATE_MAGNESIUM_ORE = registerBlock("deepslate_magnesium_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> MAGNESIUM_BLOCK = registerBlock("magnesium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> RAW_MAGNESIUM_BLOCK = registerBlock("raw_magnesium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> SILVER_ORE = registerBlock("silver_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> SILVER_BLOCK = registerBlock("silver_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> RAW_SILVER_BLOCK = registerBlock("raw_silver_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> RAW_TIN_BLOCK = registerBlock("raw_tin_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> RUBY_ORE = registerBlock("ruby_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> RUBY_BLOCK = registerBlock("ruby_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL)
                    .strength(3f).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static final RegistryObject<Block> ASPHALT = registerBlock("asphalt",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.DEEPSLATE)
                    .strength(1.5f).speedFactor(1.2F).requiresCorrectToolForDrops()), ModCreativeModeTab.MODDED);

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
