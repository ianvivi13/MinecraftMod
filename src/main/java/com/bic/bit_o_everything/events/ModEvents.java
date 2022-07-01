package com.bic.bit_o_everything.events;

import com.bic.bit_o_everything.BitOEverything;
import com.bic.bit_o_everything.item.custom.MagicCastingItem;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BitOEverything.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void leftClickEmptyWithMagicCastingItem(PlayerInteractEvent.LeftClickEmpty event) {
        if (event.getPlayer().getMainHandItem().getItem() instanceof MagicCastingItem e) {
            e.scroll(event.getPlayer().getMainHandItem(), event.getPlayer());
        }


    }


}
