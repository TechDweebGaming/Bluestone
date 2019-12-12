package io.github.techdweebgaming.bluestone.tileentities;

import io.github.techdweebgaming.bluestone.Bluestone;
import io.github.techdweebgaming.bluestone.blocks.BluestoneBlocks;
import io.github.techdweebgaming.bluestone.tileentities.logicgates.*;
import io.github.techdweebgaming.bluestone.tileentities.vanilla.*;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Bluestone.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Bluestone.MODID)
public class BluestoneTileEntities {

    private static List<TileEntityType<? extends TileEntity>> tileEntities = new ArrayList<>();

    public static final TileEntityType<BluestoneEmitterTileEntity> bluestoneEmitterTileEntity = register(BluestoneEmitterTileEntity::new, "bluestone_emitter_te", BluestoneBlocks.bluestoneEmitter);
    public static final TileEntityType<BluestoneLeverTileEntity> bluestoneLeverTileEntity = register(BluestoneLeverTileEntity::new, "bluestone_lever_te", BluestoneBlocks.bluestoneLever);
    public static final TileEntityType<BluestoneObserverTileEntity> bluestoneObserverTileEntity = register(BluestoneObserverTileEntity::new, "bluestone_observer_te", BluestoneBlocks.bluestoneObserver);
    public static final TileEntityType<BluestoneButtonTileEntity> bluestoneButtonTileEntity = register(BluestoneButtonTileEntity::new, "bluestone_button_te", BluestoneBlocks.bluestoneStoneButton, BluestoneBlocks.bluestoneWoodenButton);
    public static final TileEntityType<BluestonePressurePlateTileEntity> bluestonePressurePlateTileEntity = register(BluestonePressurePlateTileEntity::new, "bluestone_pressure_plate_te", BluestoneBlocks.bluestoneStonePressurePlate, BluestoneBlocks.bluestoneWoodenPressurePlate);
    public static final TileEntityType<BluestoneDetectorRailTileEntity> bluestoneDetectorRailTileEntity = register(BluestoneDetectorRailTileEntity::new, "bluestone_detector_rail_te", BluestoneBlocks.bluestoneDetectorRail);
    public static final TileEntityType<ORGateTileEntity> orGateTileEntity = register(ORGateTileEntity::new, "gate_or_te", BluestoneBlocks.orGate);
    public static final TileEntityType<ANDGateTileEntity> andGateTileEntity = register(ANDGateTileEntity::new, "gate_and_te", BluestoneBlocks.andGate);
    public static final TileEntityType<NANDGateTileEntity> nandGateTileEntity = register(NANDGateTileEntity::new, "gate_nand_te", BluestoneBlocks.nandGate);
    public static final TileEntityType<NORGateTileEntity> norGateTileEntity = register(NORGateTileEntity::new, "gate_nor_te", BluestoneBlocks.norGate);
    public static final TileEntityType<XORGateTileEntity> xorGateTileEntity = register(XORGateTileEntity::new, "gate_xor_te", BluestoneBlocks.xorGate);
    public static final TileEntityType<XNORGateTileEntity> xnorGateTileEntity = register(XNORGateTileEntity::new, "gate_xnor_te", BluestoneBlocks.xnorGate);

    @SubscribeEvent
    public static void onRegisterTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(tileEntities.toArray(new TileEntityType[tileEntities.size()]));
    }

    public static <T extends TileEntity> TileEntityType<T> register(Supplier<T> teSupplier, String registryName, Block... blocks) {
        TileEntityType<?> teType = TileEntityType.Builder.create(teSupplier, blocks).build(null).setRegistryName(Bluestone.MODID, registryName);
        tileEntities.add(teType);
        return (TileEntityType<T>) teType;
    }

    public static <T extends TileEntity> TileEntity getTileEntityFromClass(Class<T> clazz) {
       for(TileEntityType<? extends TileEntity> teType : tileEntities) {
           TileEntity te = teType.create();
           if(te.getClass() == clazz) return te;
       }
       return null;
    }

}
