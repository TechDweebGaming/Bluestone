package io.github.techdweebgaming.bluestone.tileentities;

import io.github.techdweebgaming.bluestone.blocks.BluestonePressurePlate;
import net.minecraft.block.BlockState;

public class BluestonePressurePlateTileEntity extends BluestoneTransmitterTileEntity {

    protected BluestonePressurePlateTileEntity() {
        super(BluestoneTileEntities.bluestonePressurePlateTileEntity);
    }

    @Override
    public boolean getBluestoneState(BlockState state) {
        return state.get(BluestonePressurePlate.POWERED);
    }
}
