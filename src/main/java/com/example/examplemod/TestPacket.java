package com.example.examplemod;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class TestPacket {
    public static final StreamCodec<FriendlyByteBuf, TestPacket> STREAM_CODEC = StreamCodec.ofMember(TestPacket::encode, TestPacket::decode);
    private static final Logger LOGGER = LogUtils.getLogger();
    
    protected final String msg;
    
    public TestPacket(String msg) {
        this.msg = msg;
    }
    
    public static void encode(TestPacket message, FriendlyByteBuf buf) {
        buf.writeUtf(message.msg);
    }
    
    public static TestPacket decode(FriendlyByteBuf buf) {
        return new TestPacket(buf.readUtf());
    }
    
    public static void onMessage(TestPacket message, CustomPayloadEvent.Context ctx) {
        LOGGER.info("Received packet with message: {}", message.msg);
    }
}
