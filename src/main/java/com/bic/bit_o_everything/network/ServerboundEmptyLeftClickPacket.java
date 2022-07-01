package com.bic.bit_o_everything.network;

import com.bic.bit_o_everything.item.custom.fancyTypes.EmptyLeftClick;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ServerboundEmptyLeftClickPacket {

    public ServerboundEmptyLeftClickPacket() {
    }

    public void encode(FriendlyByteBuf buffer) {
    }

    public ServerboundEmptyLeftClickPacket(FriendlyByteBuf buffer) {
        this();
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        final var success = new AtomicBoolean(false);
        ctx.get().enqueueWork(() -> {
            Player player = ctx.get().getSender();
            ItemStack itemStack = player.getMainHandItem();
            if (itemStack.getItem() instanceof EmptyLeftClick leftClickItem) {
                leftClickItem.emptyLeftClick(player, itemStack);
                success.set(true);
            }
        });
        ctx.get().setPacketHandled(true);
        return success.get();
    }
}
