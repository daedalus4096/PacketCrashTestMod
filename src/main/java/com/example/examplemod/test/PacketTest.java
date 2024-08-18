package com.example.examplemod.test;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.PacketHandler;
import com.example.examplemod.TestPacket;

import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraftforge.gametest.GameTestHolder;

@GameTestHolder(ExampleMod.MODID)
public class PacketTest {
    @GameTest(template = ExampleMod.MODID + ":test/empty3x3x3")
    public static void canary(GameTestHelper helper) {
        helper.assertTrue(true, "Something's wrong with the universe");
        helper.succeed();
    }
    
    @GameTest(template = ExampleMod.MODID + ":test/empty3x3x3")
    public static void send_to_player(GameTestHelper helper) {
        ServerPlayer player = helper.makeMockServerPlayer();
        PacketHandler.sendToPlayer(new TestPacket("testing123"), player);
        helper.succeed();
    }
    
    @GameTest(template = ExampleMod.MODID + ":test/empty3x3x3")
    public static void send_to_nearby(GameTestHelper helper) {
        helper.makeMockPlayer(GameType.SURVIVAL);
        PacketHandler.sendToAllAround(new TestPacket("testing123"), Level.OVERWORLD, BlockPos.ZERO, 5D);
        helper.succeed();
    }
    
    @GameTest(template = ExampleMod.MODID + ":test/empty3x3x3")
    public static void send_to_all(GameTestHelper helper) {
        helper.makeMockPlayer(GameType.SURVIVAL);
        PacketHandler.sendToAll(new TestPacket("testing123"));
        helper.succeed();
    }
}
