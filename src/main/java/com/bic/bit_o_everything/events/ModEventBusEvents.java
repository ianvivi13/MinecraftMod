package com.bic.bit_o_everything.events;

import com.bic.bit_o_everything.BitOEverything;
import com.bic.bit_o_everything.item.custom.fancyTypes.EmptyLeftClick;
import com.bic.bit_o_everything.item.custom.fancyTypes.LeftClickBlock;
import com.bic.bit_o_everything.network.ModPacketHandler;
import com.bic.bit_o_everything.network.ServerboundEmptyLeftClickPacket;
import com.bic.bit_o_everything.particle.ModParticles;
import com.bic.bit_o_everything.particle.custom.FailedSpellParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BitOEverything.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerPaticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.FAILED_SPELL_PARTICLES.get(),
                FailedSpellParticles.Provider::new);
    }

}
