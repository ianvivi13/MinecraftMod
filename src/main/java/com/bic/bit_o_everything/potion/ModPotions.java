package com.bic.bit_o_everything.potion;

import com.bic.bit_o_everything.BitOEverything;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, BitOEverything.MOD_ID);

    //Add potions here




    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
