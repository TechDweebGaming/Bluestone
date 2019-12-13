package io.github.techdweebgaming.bluestone.tileentities;

import io.github.techdweebgaming.bluestone.blocks.BluestoneEmitter;
import net.minecraft.block.BlockState;

public class BluestoneEmitterTileEntity extends BluestoneTransmitterTileEntity {

    public BluestoneEmitterTileEntity() {
        super(BluestoneTileEntities.bluestoneEmitterTileEntity);
    }

    @Override
    public void tick() {
        if(world.isBlockPowered(pos) == getBlockState().get(BluestoneEmitter.ENABLED)) world.setBlockState(pos, getBlockState().with(BluestoneEmitter.ENABLED, !world.isBlockPowered(pos)));
        super.tick();
    }

    @Override
    public boolean getBluestoneState(BlockState state) {
        return state.get(BluestoneEmitter.ENABLED);
    }

}
