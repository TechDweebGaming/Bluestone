package io.github.techdweebgaming.bluestone.tileentities;

import io.github.techdweebgaming.bluestone.Bluestone;
import io.github.techdweebgaming.bluestone.blocks.BluestoneBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Bluestone.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Bluestone.MODID)
public class BluestoneTileEntities {

    public static final TileEntityType<?> bluestoneEmitterTileEntity = TileEntityType.Builder.create((Supplier<TileEntity>) BluestoneTransmitterTileEntity::new, BluestoneBlocks.bluestoneEmitter)
            .build(null)
            .setRegistryName(Bluestone.MODID, "bluestone_emitter_te");

    @SubscribeEvent
    public static void onRegisterTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(
            bluestoneEmitterTileEntity
        );
    }

}
