package com.bic.bit_o_everything.block.custom;

import com.bic.bit_o_everything.block.entity.custom.PotterBlockEntity;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.function.Supplier;

public class ConcretePotterBlock extends PotterBlock{

    public final static ArrayList<Pair<Supplier<Block>, Supplier<Item>>> materialPossibles = new ArrayList<>() {{
        add(new Pair<>(() -> Blocks.WHITE_CONCRETE, () -> Items.WHITE_DYE));
        add(new Pair<>(() -> Blocks.ORANGE_CONCRETE, () -> Items.ORANGE_DYE));
        add(new Pair<>(() -> Blocks.MAGENTA_CONCRETE, () -> Items.MAGENTA_DYE));
        add(new Pair<>(() -> Blocks.LIGHT_BLUE_CONCRETE, () -> Items.LIGHT_BLUE_DYE));
        add(new Pair<>(() -> Blocks.YELLOW_CONCRETE, () -> Items.YELLOW_DYE));
        add(new Pair<>(() -> Blocks.LIME_CONCRETE, () -> Items.LIME_DYE));
        add(new Pair<>(() -> Blocks.PINK_CONCRETE, () -> Items.PINK_DYE));
        add(new Pair<>(() -> Blocks.GRAY_CONCRETE, () -> Items.GRAY_DYE));
        add(new Pair<>(() -> Blocks.LIGHT_GRAY_CONCRETE, () -> Items.LIGHT_GRAY_DYE));
        add(new Pair<>(() -> Blocks.CYAN_CONCRETE, () -> Items.CYAN_DYE));
        add(new Pair<>(() -> Blocks.PURPLE_CONCRETE, () -> Items.PURPLE_DYE));
        add(new Pair<>(() -> Blocks.BLUE_CONCRETE, () -> Items.BLUE_DYE));
        add(new Pair<>(() -> Blocks.BROWN_CONCRETE, () -> Items.BROWN_DYE));
        add(new Pair<>(() -> Blocks.GREEN_CONCRETE, () -> Items.GREEN_DYE));
        add(new Pair<>(() -> Blocks.RED_CONCRETE, () -> Items.RED_DYE));
        add(new Pair<>(() -> Blocks.BLACK_CONCRETE, () -> Items.BLACK_DYE));
    }};

    public ConcretePotterBlock(Properties p_49795_) {
        super(p_49795_);
    }

    public static Block getMaterialBlock(int i) {
        if (i < 0) {
            return materialPossibles.get(0).getFirst().get();
        } else {
            return materialPossibles.get(i).getFirst().get();
        }
    }

    @Override
    public ArrayList<Pair<Supplier<Block>, Supplier<Item>>> getMaterialPossibles() {
        return materialPossibles;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        PotterBlockEntity t =  new PotterBlockEntity(pPos, pState);
        t.concrete = true;
        return t;
    }
}
