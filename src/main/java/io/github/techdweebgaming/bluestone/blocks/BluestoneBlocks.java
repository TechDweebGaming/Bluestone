package io.github.techdweebgaming.bluestone.blocks;

import io.github.techdweebgaming.bluestone.Bluestone;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Bluestone.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Bluestone.MODID)
public class BluestoneBlocks {

    public static final Block bluestoneEmitter = new BluestoneEmitter(Block.Properties.create(Material.ROCK).sound(SoundType.GLASS).hardnessAndResistance(2.0F, 4.0F)).setRegistryName(Bluestone.MODID, "bluestone_emitter");
    public static final Block bluestoneReceiver = new BluestoneReceiver(Block.Properties.create(Material.ROCK).sound(SoundType.GLASS).hardnessAndResistance(2.0F, 4.0F)).setRegistryName(Bluestone.MODID, "bluestone_receiver");

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
            bluestoneEmitter,
            bluestoneReceiver
        );
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
            new BlockItem(bluestoneEmitter, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName(bluestoneEmitter.getRegistryName()),
            new BlockItem(bluestoneReceiver, new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName(bluestoneReceiver.getRegistryName())
        );
    }

}
