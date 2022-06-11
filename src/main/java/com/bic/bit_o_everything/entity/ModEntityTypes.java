package com.bic.bit_o_everything.entity;

import com.bic.bit_o_everything.BitOEverything;
import com.bic.bit_o_everything.entity.custom.ModBoatEntity;
import com.bic.bit_o_everything.entity.custom.ModChestBoatEntity;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, BitOEverything.MOD_ID);

    public static final RegistryObject<EntityType<ModBoatEntity>> MOD_BOAT =
            ENTITY_TYPES.register("boat",
                    () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, MobCategory.MISC)
                            .sized(1.375F, 0.5625F).clientTrackingRange(10)
                            .build("boat"));

    public static final RegistryObject<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT =
            ENTITY_TYPES.register("chest_boat",
                    () -> EntityType.Builder.<ModChestBoatEntity>of(ModChestBoatEntity::new, MobCategory.MISC)
                            .sized(1.375F, 0.5625F).clientTrackingRange(10)
                            .build("chest_boat"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
