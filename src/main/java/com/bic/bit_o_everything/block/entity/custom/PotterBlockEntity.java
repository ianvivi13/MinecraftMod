package com.bic.bit_o_everything.block.entity.custom;

import com.bic.bit_o_everything.block.custom.PotterBlock;
import com.bic.bit_o_everything.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class PotterBlockEntity extends BlockEntity {

    public int dirt = -1;
    public int flower = -1;
    public Block material = Blocks.LIME_TERRACOTTA;

    public PotterBlockEntity(BlockPos pPos, BlockState pState) {
        super(ModBlockEntities.POTTER.get(), pPos, pState);
    }

    // getUpdateTag / handleUpdateTag / getUpdatePacket / onDataPacket

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.dirt = pTag.getInt("dirt");
        this.flower = pTag.getInt("flower");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("dirt",this.dirt);
        pTag.putInt("flower",this.flower);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag t = super.getUpdateTag();
        saveAdditional(t);
        return t;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void updateRender() {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    public Block getDirt() {
        return PotterBlock.getDirtBlock(this.dirt);
    }

    public Block getFlower() {
        return PotterBlock.getFlowerBlock(this.flower);
    }

    public Block getMaterial() {
        return this.material;
    }

}
