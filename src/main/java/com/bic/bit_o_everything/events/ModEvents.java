package com.bic.bit_o_everything.events;

import com.bic.bit_o_everything.BitOEverything;
import com.bic.bit_o_everything.item.ModItems;
import com.bic.bit_o_everything.item.custom.fancyTypes.EmptyLeftClick;
import com.bic.bit_o_everything.item.custom.fancyTypes.LeftClickBlock;
import com.bic.bit_o_everything.network.ModPacketHandler;
import com.bic.bit_o_everything.network.ServerboundEmptyLeftClickPacket;
import com.bic.bit_o_everything.spells.AbstractSpell;
import com.bic.bit_o_everything.spells.FireballSpell;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BitOEverything.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void leftClickEmptySendToServer(PlayerInteractEvent.LeftClickEmpty event) { // this is only called on client, must send event to server too
        ItemStack itemStack =  event.getItemStack();
        if (itemStack.getItem() instanceof EmptyLeftClick emptyLeftClickItem) {
            emptyLeftClickItem.emptyLeftClick(event.getPlayer(), itemStack);
            ModPacketHandler.INSTANCE.sendToServer(new ServerboundEmptyLeftClickPacket());
        }
    }

    @SubscribeEvent
    public static void leftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        ItemStack itemStack =  event.getItemStack();
        if (itemStack.getItem() instanceof LeftClickBlock leftClickBlock) {
            leftClickBlock.leftClickBlock(event.getPlayer(), itemStack);
        }
    }
}
