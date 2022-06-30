package com.bic.bit_o_everything.sound;

import com.bic.bit_o_everything.BitOEverything;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BitOEverything.MOD_ID);

    public static final RegistryObject<SoundEvent> CAST_FAILED =
            registerSoundEvent("cast_failed");

    public static final RegistryObject<SoundEvent> INCANTATION_FAILED =
            registerSoundEvent("incantation_failed");

    public static final RegistryObject<SoundEvent> INCANTATION_SUCCESS =
            registerSoundEvent("incantation_success");

    public static final RegistryObject<SoundEvent> SPELL_FIREBALL =
            registerSoundEvent("spell_fireball");

    public static final RegistryObject<SoundEvent> SPELL_SLOWFALLING =
            registerSoundEvent("spell_slowfalling");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(BitOEverything.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
