package io.github.techdweebgaming.bluestone.tileentities;

import io.github.techdweebgaming.bluestone.Bluestone;
import io.github.techdweebgaming.bluestone.blocks.BluestoneBlocks;
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

    private static List<TileEntityType<?>> tileEntities = new ArrayList<>();

    public static final TileEntityType<?> bluestoneEmitterTileEntity = register(BluestoneEmitterTileEntity::new, "bluestone_emitter_te", BluestoneBlocks.bluestoneEmitter);
    public static final TileEntityType<?> bluestoneLeverTileEntity = register(BluestoneLeverTileEntity::new, "bluestone_lever_te", BluestoneBlocks.bluestoneLever);
    public static final TileEntityType<?> bluestoneObserverTileEntity = register(BluestoneObserverTileEntity::new, "bluestone_observer_te", BluestoneBlocks.bluestoneObserver);
    public static final TileEntityType<?> bluestoneButtonTileEntity = register(BluestoneButtonTileEntity::new, "bluestone_button_te", BluestoneBlocks.bluestoneStoneButton, BluestoneBlocks.bluestoneWoodenButton);
    public static final TileEntityType<?> bluestonePressurePlateTileEntity = register(BluestonePressurePlateTileEntity::new, "bluestone_pressure_plate_te", BluestoneBlocks.bluestoneStonePressurePlate, BluestoneBlocks.bluestoneWoodenPressurePlate);

    @SubscribeEvent
    public static void onRegisterTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(tileEntities.toArray(new TileEntityType[tileEntities.size()]));
    }

    public static TileEntityType<?> register(Supplier<TileEntity> teSupplier, String registryName, Block... blocks) {
        TileEntityType<?> teType = TileEntityType.Builder.create(teSupplier, blocks).build(null).setRegistryName(Bluestone.MODID, registryName);
        tileEntities.add(teType);
        return teType;
    }

}
