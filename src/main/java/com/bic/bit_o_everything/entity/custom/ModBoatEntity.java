package com.bic.bit_o_everything.entity.custom;

import com.bic.bit_o_everything.block.ModBlocks;
import com.bic.bit_o_everything.entity.ModEntityTypes;
import com.bic.bit_o_everything.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class ModBoatEntity extends Boat {

    private static final EntityDataAccessor<Integer> BOAT_TYPE =
            SynchedEntityData.defineId(ModBoatEntity.class, EntityDataSerializers.INT);

    public ModBoatEntity(EntityType<? extends Boat> type, Level level) {
        super(type, level);
        this.blocksBuilding = true;
    }

    public ModBoatEntity(Level worldIn, double x, double y, double z) {
        this(ModEntityTypes.MOD_BOAT.get(), worldIn);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public ModBoatEntity.Type getModBoatEntityType() {
        return ModBoatEntity.Type.byId(this.entityData.get(BOAT_TYPE));
    }

    @Override
    public Item getDropItem() {
        switch(this.getModBoatEntityType()) {
            default:
            case CHERRY:
                return ModItems.CHERRY_BOAT.get();
        }
    }

    public void setBoatType(ModBoatEntity.Type boatType) {
        this.entityData.set(BOAT_TYPE, boatType.ordinal());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BOAT_TYPE, Type.CHERRY.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putString("Type", this.getModBoatEntityType().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        if (compound.contains("Type", 8)) {
            this.setBoatType(ModBoatEntity.Type.getTypeFromString(compound.getString("Type")));
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        //return NetworkHooks.getEntitySpawningPacket(this);
        return new ClientboundAddEntityPacket(this);
    }

    public enum Type {
        CHERRY(ModBlocks.CHERRY_PLANKS.get(), "cherry")
        ;

        private final String name;
        private final Block block;

        Type(Block block, String name) {
            this.name = name;
            this.block = block;
        }

        public String getName() {
            return this.name;
        }

        public Block asPlank() {
            return this.block;
        }

        public String toString() {
            return this.name;
        }

        public static ModBoatEntity.Type byId(int id) {
            ModBoatEntity.Type[] aModBoatEntityEntity$type = values();
            if (id < 0 || id >= aModBoatEntityEntity$type.length) {
                id = 0;
            }

            return aModBoatEntityEntity$type[id];
        }

        public static ModBoatEntity.Type getTypeFromString(String nameIn) {
            ModBoatEntity.Type[] boatTypeArray = values();

            for (Type type : boatTypeArray) {
                if (type.getName().equals(nameIn)) {
                    return type;
                }
            }

            return boatTypeArray[0];
        }
    }

}
