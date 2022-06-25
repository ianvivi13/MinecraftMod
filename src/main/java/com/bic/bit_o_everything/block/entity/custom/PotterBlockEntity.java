package com.bic.bit_o_everything.block.entity.custom;

import com.bic.bit_o_everything.block.custom.PotterBlock;
import com.bic.bit_o_everything.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

public class PotterBlockEntity extends BlockEntity {

    public int dirt = -1;
    public int flower = -1;
    public int material = 0;
    public float flowerRotation = 0;
    public boolean[] connections = new boolean[] {
            true, true, true,
            true, false, true,
            true, true, true
    };

    public PotterBlockEntity(BlockPos pPos, BlockState pState) {
        super(ModBlockEntities.POTTER.get(), pPos, pState);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.dirt = pTag.getInt("dirt");
        this.flower = pTag.getInt("flower");
        this.material = pTag.getInt("material");
        this.flowerRotation = pTag.getFloat("flowerRotation");
        for (int i = 0 ; i < connections.length ; i ++) {
            this.connections[i] = pTag.getBoolean(String.valueOf(i));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("dirt",this.dirt);
        pTag.putInt("flower",this.flower);
        pTag.putInt("material",this.material);
        pTag.putFloat("flowerRotation",this.flowerRotation);
        for (int i = 0 ; i < connections.length ; i ++) {
            pTag.putBoolean(String.valueOf(i),connections[i]);
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag t = super.getUpdateTag();
        saveAdditional(t);
        return t;
    }

    @Override
    public AABB getRenderBoundingBox() {
        BlockPos pos = this.getBlockPos();
        return new AABB(pos.offset(-1,0,-1), pos.offset(1,2,1));
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void update() {
        this.requestModelDataUpdate();
    }

    public void updateRender() {
        update();
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
        return PotterBlock.getMaterialBlock(this.material);
    }


}
