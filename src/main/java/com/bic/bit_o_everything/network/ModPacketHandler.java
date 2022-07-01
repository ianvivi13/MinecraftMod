package com.bic.bit_o_everything.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class ModPacketHandler {
    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("bit_o_everything","main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private ModPacketHandler() {

    }

    public static void init() {
        int index = 0;
        INSTANCE.messageBuilder(ServerboundEmptyLeftClickPacket.class, index++, NetworkDirection.PLAY_TO_SERVER)
                .encoder(ServerboundEmptyLeftClickPacket::encode)
                .decoder(ServerboundEmptyLeftClickPacket::new)
                .consumer(ServerboundEmptyLeftClickPacket::handle)
                .add();
    }


}
