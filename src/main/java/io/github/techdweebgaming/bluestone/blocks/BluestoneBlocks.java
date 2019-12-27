package io.github.techdweebgaming.bluestone.blocks;

import io.github.techdweebgaming.bluestone.Bluestone;
import io.github.techdweebgaming.bluestone.blocks.vanilla.*;
import io.github.techdweebgaming.bluestone.items.InDevBlockItem;
import io.github.techdweebgaming.bluestone.tileentities.logicgates.*;
import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Bluestone.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Bluestone.MODID)
public class BluestoneBlocks {

    private static List<Block> blocks = new ArrayList<>();

    public static final Block bluestoneEmitter = register(new BluestoneEmitter(getProperties(Material.ROCK, SoundType.GLASS)), "bluestone_emitter");
    public static final Block bluestoneReceiver = register(new BluestoneReceiver(getProperties(Material.ROCK, SoundType.GLASS)), "bluestone_receiver");
    public static final Block bluestoneLever = register(new BluestoneLever(getProperties(Material.MISCELLANEOUS, SoundType.WOOD, 1.0F, 1.0F, true)), "bluestone_lever");
    public static final Block bluestoneLamp = register(new BluestoneLamp(getProperties(Material.REDSTONE_LIGHT, SoundType.GLASS)), "bluestone_lamp");
    public static final Block bluestoneObserver = register(new BluestoneObserver(getProperties(Material.ROCK, SoundType.GLASS)), "bluestone_observer");
    public static final Block bluestoneTNT = register(new BluestoneTNT(getProperties(Material.TNT, SoundType.PLANT)), "bluestone_tnt");
    public static final Block bluestoneDispenser = register(new BluestoneDispenser(getProperties(Material.ROCK, SoundType.STONE)), "bluestone_dispenser");
    public static final Block bluestoneDropper = register(new BluestoneDropper(getProperties(Material.ROCK, SoundType.STONE)), "bluestone_dropper");
    public static final Block bluestoneStoneButton = register(new BluestoneButton(false, getProperties(Material.MISCELLANEOUS, SoundType.STONE, 1.0F, 1.0F, true)), "bluestone_button_stone");
    public static final Block bluestoneWoodenButton = register(new BluestoneButton(true, getProperties(Material.MISCELLANEOUS, SoundType.WOOD, 1.0F, 1.0F, true)), "bluestone_button_wood");
    public static final Block bluestonePiston = register(new BluestonePiston(false, getProperties(Material.PISTON, SoundType.STONE)), "bluestone_piston");
    public static final Block bluestonePistonSticky = register(new BluestonePiston(true, getProperties(Material.PISTON, SoundType.STONE)), "bluestone_piston_sticky");
    public static final Block bluestoneStonePressurePlate = register(new BluestonePressurePlate(PressurePlateBlock.Sensitivity.MOBS, getProperties(Material.ROCK, SoundType.STONE, 1.0F, 1.0F, true)), "bluestone_pressure_plate_stone");
    public static final Block bluestoneWoodenPressurePlate = register(new BluestonePressurePlate(PressurePlateBlock.Sensitivity.EVERYTHING, getProperties(Material.WOOD, SoundType.WOOD, 1.0F, 1.0F, true)), "bluestone_pressure_plate_wood");
    public static final Block bluestoneDetectorRail = register(new BluestoneDetectorRail(getProperties(Material.MISCELLANEOUS, SoundType.METAL, 1.0F, 1.0F, true)), "bluestone_detector_rail");
    public static final Block bluestonePoweredRail = register(new BluestonePoweredRail(false, getProperties(Material.MISCELLANEOUS, SoundType.METAL, 1.0F, 1.0F, true)), "bluestone_powered_rail");
    public static final Block bluestoneActivatorRail = register(new BluestonePoweredRail(true, getProperties(Material.MISCELLANEOUS, SoundType.METAL, 1.0F, 1.0F, true)), "bluestone_activator_rail");
    public static final Block orGate = register(new LogicGateBlock(getProperties(Material.ROCK, SoundType.STONE), ORGateTileEntity.class), "gate_or");
    public static final Block andGate = register(new LogicGateBlock(getProperties(Material.ROCK, SoundType.STONE), ANDGateTileEntity.class), "gate_and");
    public static final Block nandGate = register(new LogicGateBlock(getProperties(Material.ROCK, SoundType.STONE), NANDGateTileEntity.class), "gate_nand");
    public static final Block norGate = register(new LogicGateBlock(getProperties(Material.ROCK, SoundType.STONE), NORGateTileEntity.class), "gate_nor");
    public static final Block xorGate = register(new LogicGateBlock(getProperties(Material.ROCK, SoundType.STONE), XORGateTileEntity.class), "gate_xor");
    public static final Block xnorGate = register(new LogicGateBlock(getProperties(Material.ROCK, SoundType.STONE), XNORGateTileEntity.class), "gate_xnor");
    public static final Block bluestoneIronDoor = register(new BluestoneDoor(getProperties(Material.IRON, SoundType.METAL)), "bluestone_door_iron");
    public static final Block bluestoneWoodenDoor = register(new BluestoneDoor(getProperties(Material.WOOD, SoundType.WOOD)), "bluestone_door_wood");
    public static final Block bluestoneIronTrapDoor = register(new BluestoneTrapDoor(getProperties(Material.IRON, SoundType.METAL)), "bluestone_trap_door_iron");
    public static final Block bluestoneWoodenTrapDoor = register(new BluestoneTrapDoor(getProperties(Material.WOOD, SoundType.WOOD)), "bluestone_trap_door_wood");

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(blocks.toArray(new Block[blocks.size()]));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for(Block block : blocks) {
            if(block instanceof InDevBlockItem.IInDevBlock) {
                event.getRegistry().register(new InDevBlockItem(block, new Item.Properties().group(Bluestone.bluestoneCreativeTab)).setRegistryName(block.getRegistryName()));
                continue;
            }
            event.getRegistry().register(new BlockItem(block, new Item.Properties().group(Bluestone.bluestoneCreativeTab)).setRegistryName(block.getRegistryName()));
        }
    }

    private static Block.Properties getProperties(Material material, SoundType sound, float hardness, float resistance, boolean doesNotBlockMovement) {
        Block.Properties properties = Block.Properties.create(material).sound(sound).hardnessAndResistance(1.0F, 1.0F);
        if(doesNotBlockMovement) properties = properties.doesNotBlockMovement();
        return properties;
    }

    private static Block.Properties getProperties(Material material, SoundType sound) {
        return getProperties(material, sound, 1.0F, 1.0F, false);
    }

    private static Block register(Block block, String registryName) {
        block.setRegistryName(Bluestone.MODID, registryName);
        blocks.add(block);
        return block;
    }

}
