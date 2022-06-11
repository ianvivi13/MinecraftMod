package com.bic.bit_o_everything.datagen;

import com.bic.bit_o_everything.block.ModBlocks;
import com.bic.bit_o_everything.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    private static final int BlastFurnaceTime = 100;
    private static final int CampfireTime = 600;
    private static final int FurnaceTime = 200;
    private static final int SmokerTime = 100;

    protected void Compact(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike in, ItemLike out) {
        ShapedRecipeBuilder.shaped(out)
                .define('#', in)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_" + in.asItem(), inventoryTrigger(ItemPredicate.Builder.item().of(in).build()))
                .save(pFinishedRecipeConsumer, "bit_o_everything:" + out.asItem() + "_from_" + in.asItem());

        ShapelessRecipeBuilder.shapeless(in, 9)
                .requires(out)
                .unlockedBy("has_" + out.asItem(), inventoryTrigger(ItemPredicate.Builder.item().of(out).build()))
                .save(pFinishedRecipeConsumer, "bit_o_everything:" + in.asItem() + "_from_" + out.asItem());
    }

    protected void FurnaceCampSmoke(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike out, float xp, ItemLike ... inList) {
        for (ItemLike in: inList) {
            FurnaceCampSmoke(pFinishedRecipeConsumer, out, xp, in);
        }
    }

    protected void FurnaceBlast(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike out, float xp, ItemLike ... inList) {
        for (ItemLike in: inList) {
            FurnaceBlast(pFinishedRecipeConsumer, out, xp, in);
        }
    }

    protected void FurnaceBlast(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike out, float xp, ItemLike in) {
        String unlockName = "has" + in;
        String baseRecipeName = "bit_o_everything:" + out.asItem() + "_from_" + in.asItem() + "_in_";

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(in), out, xp, FurnaceTime)
                .unlockedBy(unlockName, inventoryTrigger(ItemPredicate.Builder.item().of(in).build()))
                .save(pFinishedRecipeConsumer, baseRecipeName + "furnace");

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(in), out, xp, BlastFurnaceTime)
                .unlockedBy(unlockName, inventoryTrigger(ItemPredicate.Builder.item().of(in).build()))
                .save(pFinishedRecipeConsumer, baseRecipeName + "blastfurnace");
    }

    protected void FurnaceCampSmoke(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike out, float xp, ItemLike in) {
        String unlockName = "has" + in;
        String baseRecipeName = "bit_o_everything:" + out.asItem() + "_from_" + in.asItem() + "_in_";

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(in), out, xp, FurnaceTime)
                .unlockedBy(unlockName, inventoryTrigger(ItemPredicate.Builder.item().of(in).build()))
                .save(pFinishedRecipeConsumer, baseRecipeName + "furnace");

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(in), out, xp, CampfireTime)
                .unlockedBy(unlockName, inventoryTrigger(ItemPredicate.Builder.item().of(in).build()))
                .save(pFinishedRecipeConsumer, baseRecipeName + "campfire");

        SimpleCookingRecipeBuilder.smoking(Ingredient.of(in), out, xp, SmokerTime)
                .unlockedBy(unlockName, inventoryTrigger(ItemPredicate.Builder.item().of(in).build()))
                .save(pFinishedRecipeConsumer, baseRecipeName + "smoker");
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        /*  crafting recipes are easier
            Compact: 9 in make out, 1 out makes 9 in
         */

        /* smelting recipes are easier:
            FurnaceBlast: creates furnace & blasting recipes
            FurnaceCampSmoke: creates furnace & campfire & smoking recipes
            Both of these can take an extension of parameters of inputs to make multiple for the same output - good for ores
        */

        ShapedRecipeBuilder.shaped(ModBlocks.ASPHALT.get(), 4)
                .define('S', Blocks.STONE)
                .define('C', Blocks.COBBLESTONE)
                .define('L', ModItems.SLAG.get())
                .pattern("CSC")
                .pattern("SLS")
                .pattern("CSC")
                .unlockedBy("has_slag", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.SLAG.get()).build()))
                .save(pFinishedRecipeConsumer);

        ShapelessRecipeBuilder.shapeless(ModItems.MOD_BOOK.get())
                .requires(Items.BOOK)
                .unlockedBy("has_book", inventoryTrigger(ItemPredicate.Builder.item().of(Items.BOOK).build()))
                .save(pFinishedRecipeConsumer);

        Compact(pFinishedRecipeConsumer, ModItems.RAW_PYRITE.get(), ModBlocks.RAW_PYRITE_BLOCK.get());
        Compact(pFinishedRecipeConsumer, ModItems.PYRITE.get(), ModBlocks.PYRITE_BLOCK.get());
        Compact(pFinishedRecipeConsumer, ModItems.RAW_ZINC.get(), ModBlocks.RAW_ZINC_BLOCK.get());
        Compact(pFinishedRecipeConsumer, ModItems.ZINC_INGOT.get(), ModBlocks.ZINC_BLOCK.get());
        Compact(pFinishedRecipeConsumer, ModItems.RAW_MAGNESIUM.get(), ModBlocks.RAW_MAGNESIUM_BLOCK.get());
        Compact(pFinishedRecipeConsumer, ModItems.MAGNESIUM_INGOT.get(), ModBlocks.MAGNESIUM_BLOCK.get());
        Compact(pFinishedRecipeConsumer, ModItems.RAW_SILVER.get(), ModBlocks.RAW_SILVER_BLOCK.get());
        Compact(pFinishedRecipeConsumer, ModItems.SILVER_INGOT.get(), ModBlocks.SILVER_BLOCK.get());
        Compact(pFinishedRecipeConsumer, ModItems.RAW_TIN.get(), ModBlocks.RAW_TIN_BLOCK.get());
        Compact(pFinishedRecipeConsumer, ModItems.TIN_INGOT.get(), ModBlocks.TIN_BLOCK.get());
        Compact(pFinishedRecipeConsumer, ModItems.RAW_TITANIUM.get(), ModBlocks.RAW_TITANIUM_BLOCK.get());
        Compact(pFinishedRecipeConsumer, ModItems.TITANIUM_INGOT.get(), ModBlocks.TITANIUM_BLOCK.get());
        Compact(pFinishedRecipeConsumer, ModItems.RUBY.get(), ModBlocks.RUBY_BLOCK.get());
        Compact(pFinishedRecipeConsumer, ModItems.SAPPHIRE.get(), ModBlocks.SAPPHIRE_BLOCK.get());

        // smelting recipes (pFinishedRecipeConsumer, output, xp, input(s))
        FurnaceCampSmoke(pFinishedRecipeConsumer, ModItems.FRIED_EGG.get(), 0.35f, Items.EGG);
        FurnaceBlast(pFinishedRecipeConsumer, ModItems.PYRITE.get(),1.0f, ModBlocks.DEEPSLATE_PYRITE_ORE.get(), ModBlocks.PYRITE_ORE.get(), ModItems.RAW_PYRITE.get());
        FurnaceBlast(pFinishedRecipeConsumer, ModItems.ZINC_INGOT.get(),1.0f, ModBlocks.DEEPSLATE_ZINC_ORE.get(), ModBlocks.ZINC_ORE.get(), ModItems.RAW_ZINC.get());
        FurnaceBlast(pFinishedRecipeConsumer, ModItems.MAGNESIUM_INGOT.get(),1.0f, ModBlocks.DEEPSLATE_MAGNESIUM_ORE.get(), ModBlocks.MAGNESIUM_ORE.get(), ModItems.RAW_MAGNESIUM.get());
        FurnaceBlast(pFinishedRecipeConsumer, ModItems.SILVER_INGOT.get(),1.0f, ModBlocks.DEEPSLATE_SILVER_ORE.get(), ModBlocks.SILVER_ORE.get(), ModItems.RAW_SILVER.get());
        FurnaceBlast(pFinishedRecipeConsumer, ModItems.TIN_INGOT.get(),1.0f, ModBlocks.DEEPSLATE_TIN_ORE.get(), ModBlocks.TIN_ORE.get(), ModItems.RAW_TIN.get());
        FurnaceBlast(pFinishedRecipeConsumer, ModItems.TITANIUM_INGOT.get(),1.0f, ModBlocks.DEEPSLATE_TITANIUM_ORE.get(), ModBlocks.TITANIUM_ORE.get(), ModItems.RAW_TITANIUM.get());
        FurnaceBlast(pFinishedRecipeConsumer, ModItems.RUBY.get(),1.0f, ModBlocks.DEEPSLATE_RUBY_ORE.get(), ModBlocks.RUBY_ORE.get());
        FurnaceBlast(pFinishedRecipeConsumer, ModItems.SAPPHIRE.get(),1.0f, ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModBlocks.SAPPHIRE_ORE.get());


    }
}
