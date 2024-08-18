package com.example.examplemod;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.Channel.VersionTest;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

public class PacketHandler {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final ResourceLocation CHANNEL_NAME = ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "main_channel");
    private static final int PROTOCOL_VERSION = 1;
    
    private static final SimpleChannel CHANNEL = ChannelBuilder
            .named(CHANNEL_NAME)
            .clientAcceptedVersions(VersionTest.exact(PROTOCOL_VERSION))
            .serverAcceptedVersions(VersionTest.exact(PROTOCOL_VERSION))
            .networkProtocolVersion(PROTOCOL_VERSION)
            .simpleChannel()
                .play()
                    .clientbound()
                        .addMain(TestPacket.class, TestPacket.STREAM_CODEC, TestPacket::onMessage)
            .build();
    
    public static void registerMessages() {
        LOGGER.debug("Registering network {} v{}", CHANNEL.getName(), CHANNEL.getProtocolVersion());
    }
    
    public static void sendToPlayer(Object message, ServerPlayer player) {
        // Send a message to a specific player
        CHANNEL.send(message, PacketDistributor.PLAYER.with(player));
    }
    
    public static void sendToAllAround(Object message, ResourceKey<Level> dimension, BlockPos center, double radius) {
        // Send a message to the clients of all players within a given distance of the given world position
        CHANNEL.send(message, PacketDistributor.NEAR.with(new PacketDistributor.TargetPoint(center.getX() + 0.5D, center.getY() + 0.5D, center.getZ() + 0.5D, radius, dimension)));
    }
    
    public static void sendToAll(Object message) {
        // Send a message to all connected clients
        CHANNEL.send(message, PacketDistributor.ALL.noArg());
    }
}
