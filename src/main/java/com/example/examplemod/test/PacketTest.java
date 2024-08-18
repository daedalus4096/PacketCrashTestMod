package com.example.examplemod.test;

import com.example.examplemod.ExampleMod;

import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraftforge.gametest.GameTestHolder;

@GameTestHolder(ExampleMod.MODID)
public class PacketTest {
    @GameTest(template = ExampleMod.MODID + ":test/empty3x3x3")
    public static void test(GameTestHelper helper) {
        helper.assertTrue(true, "Something's wrong with the universe");
        helper.succeed();
    }
}
