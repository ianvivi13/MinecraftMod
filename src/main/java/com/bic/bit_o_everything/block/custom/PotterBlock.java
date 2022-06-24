package com.bic.bit_o_everything.block.custom;

import com.bic.bit_o_everything.block.ModBlocks;
import com.bic.bit_o_everything.block.entity.custom.PotterBlockEntity;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Supplier;

public class PotterBlock extends BaseEntityBlock{
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

    public final static ArrayList<Pair<Supplier<Block>, Supplier<Item>>> materialPossibles = new ArrayList<>() {{
        add(new Pair<>(() -> Blocks.TERRACOTTA, () -> Items.CLAY_BALL));
        add(new Pair<>(() -> Blocks.WHITE_TERRACOTTA, () -> Items.WHITE_DYE));
        add(new Pair<>(() -> Blocks.ORANGE_TERRACOTTA, () -> Items.ORANGE_DYE));
        add(new Pair<>(() -> Blocks.MAGENTA_TERRACOTTA, () -> Items.MAGENTA_DYE));
        add(new Pair<>(() -> Blocks.LIGHT_BLUE_TERRACOTTA, () -> Items.LIGHT_BLUE_DYE));
        add(new Pair<>(() -> Blocks.YELLOW_TERRACOTTA, () -> Items.YELLOW_DYE));
        add(new Pair<>(() -> Blocks.LIME_TERRACOTTA, () -> Items.LIME_DYE));
        add(new Pair<>(() -> Blocks.PINK_TERRACOTTA, () -> Items.PINK_DYE));
        add(new Pair<>(() -> Blocks.GRAY_TERRACOTTA, () -> Items.GRAY_DYE));
        add(new Pair<>(() -> Blocks.LIGHT_GRAY_TERRACOTTA, () -> Items.LIGHT_GRAY_DYE));
        add(new Pair<>(() -> Blocks.CYAN_TERRACOTTA, () -> Items.CYAN_DYE));
        add(new Pair<>(() -> Blocks.PURPLE_TERRACOTTA, () -> Items.PURPLE_DYE));
        add(new Pair<>(() -> Blocks.BLUE_TERRACOTTA, () -> Items.BLUE_DYE));
        add(new Pair<>(() -> Blocks.BROWN_TERRACOTTA, () -> Items.BROWN_DYE));
        add(new Pair<>(() -> Blocks.GREEN_TERRACOTTA, () -> Items.GREEN_DYE));
        add(new Pair<>(() -> Blocks.RED_TERRACOTTA, () -> Items.RED_DYE));
        add(new Pair<>(() -> Blocks.BLACK_TERRACOTTA, () -> Items.BLACK_DYE));
    }};

    public final static ArrayList<Pair<Supplier<Block>, Supplier<Item>>> flowerPossibles = new ArrayList<>() {{
        add(new Pair<>(() -> Blocks.DANDELION, () -> Blocks.DANDELION.asItem()));
        add(new Pair<>(() -> Blocks.POPPY, () -> Blocks.POPPY.asItem()));
        add(new Pair<>(() -> Blocks.BLUE_ORCHID, () -> Blocks.BLUE_ORCHID.asItem()));
        add(new Pair<>(() -> Blocks.ALLIUM, () -> Blocks.ALLIUM.asItem()));
        add(new Pair<>(() -> Blocks.AZURE_BLUET, () -> Blocks.AZURE_BLUET.asItem()));
        add(new Pair<>(() -> Blocks.RED_TULIP, () -> Blocks.RED_TULIP.asItem()));
        add(new Pair<>(() -> Blocks.ORANGE_TULIP, () -> Blocks.ORANGE_TULIP.asItem()));
        add(new Pair<>(() -> Blocks.WHITE_TULIP, () -> Blocks.WHITE_TULIP.asItem()));
        add(new Pair<>(() -> Blocks.PINK_TULIP, () -> Blocks.PINK_TULIP.asItem()));
        add(new Pair<>(() -> Blocks.OXEYE_DAISY, () -> Blocks.OXEYE_DAISY.asItem()));
        add(new Pair<>(() -> Blocks.CORNFLOWER, () -> Blocks.CORNFLOWER.asItem()));
        add(new Pair<>(() -> Blocks.LILY_OF_THE_VALLEY, () -> Blocks.LILY_OF_THE_VALLEY.asItem()));
        add(new Pair<>(() -> Blocks.WITHER_ROSE, () -> Blocks.WITHER_ROSE.asItem()));
        add(new Pair<>(() -> Blocks.OAK_SAPLING, () -> Blocks.OAK_SAPLING.asItem()));
        add(new Pair<>(() -> Blocks.SPRUCE_SAPLING, () -> Blocks.SPRUCE_SAPLING.asItem()));
        add(new Pair<>(() -> Blocks.BIRCH_SAPLING, () -> Blocks.BIRCH_SAPLING.asItem()));
        add(new Pair<>(() -> Blocks.JUNGLE_SAPLING, () -> Blocks.JUNGLE_SAPLING.asItem()));
        add(new Pair<>(() -> Blocks.ACACIA_SAPLING, () -> Blocks.ACACIA_SAPLING.asItem()));
        add(new Pair<>(() -> Blocks.DARK_OAK_SAPLING, () -> Blocks.DARK_OAK_SAPLING.asItem()));
        add(new Pair<>(() -> Blocks.RED_MUSHROOM, () -> Blocks.RED_MUSHROOM.asItem()));
        add(new Pair<>(() -> Blocks.BROWN_MUSHROOM, () -> Blocks.BROWN_MUSHROOM.asItem()));
        add(new Pair<>(() -> Blocks.FERN, () -> Blocks.FERN.asItem()));
        add(new Pair<>(() -> Blocks.DEAD_BUSH, () -> Blocks.DEAD_BUSH.asItem()));
        add(new Pair<>(() -> Blocks.CACTUS, () -> Blocks.CACTUS.asItem()));
        add(new Pair<>(() -> Blocks.BAMBOO, () -> Blocks.BAMBOO.asItem()));
        add(new Pair<>(() -> Blocks.AZALEA, () -> Blocks.AZALEA.asItem()));
        add(new Pair<>(() -> Blocks.FLOWERING_AZALEA, () -> Blocks.FLOWERING_AZALEA.asItem()));
        add(new Pair<>(() -> Blocks.CRIMSON_FUNGUS, () -> Blocks.CRIMSON_FUNGUS.asItem()));
        add(new Pair<>(() -> Blocks.WARPED_FUNGUS, () -> Blocks.WARPED_FUNGUS.asItem()));
        add(new Pair<>(() -> Blocks.CRIMSON_ROOTS, () -> Blocks.CRIMSON_ROOTS.asItem()));
        add(new Pair<>(() -> Blocks.WARPED_ROOTS, () -> Blocks.WARPED_ROOTS.asItem()));
        add(new Pair<>(() -> Blocks.MANGROVE_PROPAGULE, () -> Blocks.MANGROVE_PROPAGULE.asItem()));
        add(new Pair<>(() -> Blocks.SUGAR_CANE, () -> Blocks.SUGAR_CANE.asItem()));
        add(new Pair<>(() -> Blocks.BIG_DRIPLEAF, () -> Items.BIG_DRIPLEAF));
        add(new Pair<>(() -> Blocks.SWEET_BERRY_BUSH, () -> Items.SWEET_BERRIES));
        add(new Pair<>(() -> Blocks.NETHER_WART, () -> Items.NETHER_WART));
        add(new Pair<>(() -> Blocks.NETHER_SPROUTS, () -> Items.NETHER_SPROUTS));
        add(new Pair<>(() -> Blocks.SMALL_AMETHYST_BUD, () -> Blocks.SMALL_AMETHYST_BUD.asItem()));
        add(new Pair<>(() -> Blocks.MEDIUM_AMETHYST_BUD, () -> Blocks.MEDIUM_AMETHYST_BUD.asItem()));
        add(new Pair<>(() -> Blocks.LARGE_AMETHYST_BUD, () -> Blocks.LARGE_AMETHYST_BUD.asItem()));
        add(new Pair<>(() -> Blocks.AMETHYST_CLUSTER, () -> Blocks.AMETHYST_CLUSTER.asItem()));
        add(new Pair<>(() -> ModBlocks.CHERRY_SAPLING.get(), () -> ModBlocks.CHERRY_SAPLING.get().asItem()));
    }};

    public final static ArrayList<Pair<Supplier<Block>, Supplier<Item>>> dirtPossibles = new ArrayList<>() {{
        add(new Pair<>(() -> Blocks.DIRT, () -> Blocks.DIRT.asItem()));
        add(new Pair<>(() -> Blocks.ROOTED_DIRT, () -> Blocks.ROOTED_DIRT.asItem()));
        add(new Pair<>(() -> Blocks.SAND, () -> Blocks.SAND.asItem()));
        add(new Pair<>(() -> Blocks.MUD, () -> Blocks.MUD.asItem()));
        add(new Pair<>(() -> Blocks.GRASS_BLOCK, () -> Blocks.GRASS_BLOCK.asItem()));
        add(new Pair<>(() -> Blocks.MOSS_BLOCK, () -> Blocks.MOSS_BLOCK.asItem()));
        add(new Pair<>(() -> Blocks.GRAVEL, () -> Blocks.GRAVEL.asItem()));
        add(new Pair<>(() -> Blocks.MYCELIUM, () -> Blocks.MYCELIUM.asItem()));
        add(new Pair<>(() -> Blocks.COARSE_DIRT, () -> Blocks.COARSE_DIRT.asItem()));
        add(new Pair<>(() -> Blocks.PODZOL, () -> Blocks.PODZOL.asItem()));
        add(new Pair<>(() -> Blocks.RED_SAND, () -> Blocks.RED_SAND.asItem()));
        add(new Pair<>(() -> Blocks.SOUL_SAND, () -> Blocks.SOUL_SAND.asItem()));
        add(new Pair<>(() -> Blocks.SOUL_SOIL, () -> Blocks.SOUL_SOIL.asItem()));
        add(new Pair<>(() -> Blocks.CRIMSON_NYLIUM, () -> Blocks.CRIMSON_NYLIUM.asItem()));
        add(new Pair<>(() -> Blocks.SNOW_BLOCK, () -> Blocks.SNOW_BLOCK.asItem()));
        add(new Pair<>(() -> Blocks.WARPED_NYLIUM, () -> Blocks.WARPED_NYLIUM.asItem()));
        add(new Pair<>(() -> Blocks.CLAY, () -> Blocks.CLAY.asItem()));
        add(new Pair<>(() -> Blocks.SCULK, () -> Blocks.SCULK.asItem()));
        add(new Pair<>(() -> Blocks.NETHERRACK, () -> Blocks.NETHERRACK.asItem()));
        add(new Pair<>(() -> Blocks.PACKED_MUD, () -> Blocks.PACKED_MUD.asItem()));
        add(new Pair<>(() -> Blocks.DIRT_PATH, () -> Blocks.DIRT_PATH.asItem()));
        add(new Pair<>(() -> Blocks.FARMLAND, () -> Blocks.FARMLAND.asItem()));
    }};

    public PotterBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    public static Block getDirtBlock(int i) {
        if (i < 0) {
            return null;
        } else {
            return dirtPossibles.get(i).getFirst().get();
        }
    }

    public static Block getMaterialBlock(int i) {
        if (i < 0) {
            return materialPossibles.get(0).getFirst().get();
        } else {
            return materialPossibles.get(i).getFirst().get();
        }
    }

    public static Item getDirtItem(int i) {
        if (i < 0) {
            return null;
        } else {
            return dirtPossibles.get(i).getSecond().get();
        }
    }

    public static Block getFlowerBlock(int i) {
        if (i < 0) {
            return null;
        } else {
            return flowerPossibles.get(i).getFirst().get();
        }
    }

    public static Item getFlowerItem(int i) {
        if (i < 0) {
            return null;
        } else {
            return flowerPossibles.get(i).getSecond().get();
        }
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        if (!pLevel.isClientSide) {
            BlockEntity blockentity = pLevel.getBlockEntity(pPos);
            if (blockentity instanceof PotterBlockEntity potterBlockEntity) {
                if (itemStack.isEmpty()) {
                    if (potterBlockEntity.flower >= 0) {
                        dropFlower(pLevel, pPos);
                    } else {
                        dropDirt(pLevel, pPos);
                    }
                    potterBlockEntity.updateRender();
                    return InteractionResult.SUCCESS;
                } else {
                    int i = 0;
                    for (Pair<Supplier<Block>, Supplier<Item>> p : dirtPossibles) {
                        if (itemStack.is(p.getSecond().get())) {
                            if (i == potterBlockEntity.dirt) {
                                return InteractionResult.SUCCESS;
                            }
                            dropDirt(pLevel, pPos);
                            potterBlockEntity.dirt = i;
                            itemStack.shrink(1);
                            potterBlockEntity.updateRender();
                            return InteractionResult.SUCCESS;
                        }
                        i ++;
                    }
                    if (potterBlockEntity.dirt >= 0) {
                        int j = 0;
                        for (Pair<Supplier<Block>, Supplier<Item>> p : flowerPossibles) {
                            if (itemStack.is(p.getSecond().get())) {
                                if (j == potterBlockEntity.flower) {
                                    return InteractionResult.SUCCESS;
                                }
                                dropFlower(pLevel, pPos);
                                potterBlockEntity.flower = j;
                                itemStack.shrink(1);
                                potterBlockEntity.updateRender();
                                return InteractionResult.SUCCESS;
                            }
                            j ++;
                        }
                    }
                    int k = 0;
                    for (Pair<Supplier<Block>, Supplier<Item>> p : materialPossibles) {
                        if (itemStack.is(p.getSecond().get())) {
                            if (k == potterBlockEntity.material) {
                                return InteractionResult.SUCCESS;
                            }
                            potterBlockEntity.material = k;
                            itemStack.shrink(1);
                            potterBlockEntity.updateRender();
                            return InteractionResult.SUCCESS;
                        }
                        k ++;
                    }
                }

            }
        }
        return InteractionResult.CONSUME;
    }

    public void dropFlower(Level pLevel, BlockPos pPos) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof PotterBlockEntity potterBlockEntity) {
            if (potterBlockEntity.flower >= 0) {
                ItemStack flowerDrop = new ItemStack(getFlowerItem(potterBlockEntity.flower));
                Containers.dropItemStack(pLevel, pPos.getX(), pPos.getY() + 1, pPos.getZ(), flowerDrop);
                potterBlockEntity.flower = -1;
            }
        }
    }

    public void dropDirt(Level pLevel, BlockPos pPos) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof PotterBlockEntity potterBlockEntity) {
            if (potterBlockEntity.dirt >= 0) {
                ItemStack flowerDrop = new ItemStack(getDirtItem(potterBlockEntity.dirt));
                Containers.dropItemStack(pLevel, pPos.getX(), pPos.getY() + 1, pPos.getZ(), flowerDrop);
                potterBlockEntity.dirt = -1;
            }
        }
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        dropDirt(pLevel, pPos);
        dropFlower(pLevel, pPos);
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public @NotNull RenderShape getRenderShape(BlockState pState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PotterBlockEntity(pPos, pState);
    }
}
