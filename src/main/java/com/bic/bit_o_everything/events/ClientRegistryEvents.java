package com.bic.bit_o_everything.events;

import com.bic.bit_o_everything.BitOEverything;
import com.bic.bit_o_everything.item.ModItems;
import com.bic.bit_o_everything.item.custom.SpellItem;
import com.bic.bit_o_everything.spells.AbstractSpell;
import com.bic.bit_o_everything.spells.FireballSpell;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = BitOEverything.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistryEvents {

    @SubscribeEvent
    public static void registerItemColor(ColorHandlerEvent.Item event) {
        regSpellColor(event, ModItems.SLOWFALLING_SPELL.get());
        regSpellColor(event, ModItems.TORCH_SPELL.get());
        regSpellColor(event, ModItems.FIREBALL_SPELL.get());
    }





    public static void regSpellColor(ColorHandlerEvent.Item event, Item item) {
        if (item instanceof SpellItem spellItem) {
            event.getItemColors().register(AbstractSpell.getItemColor(spellItem.SPELL), item);
        }
    }
}
