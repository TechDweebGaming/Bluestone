package io.github.techdweebgaming.bluestone.tileentities.vanilla;

import io.github.techdweebgaming.bluestone.blocks.vanilla.BluestonePressurePlate;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTileEntities;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTransmitterTileEntity;
import net.minecraft.block.BlockState;

public class BluestonePressurePlateTileEntity extends BluestoneTransmitterTileEntity {

    public BluestonePressurePlateTileEntity() {
        super(BluestoneTileEntities.bluestonePressurePlateTileEntity);
    }

    @Override
    public boolean getBluestoneState(BlockState state) {
        return state.get(BluestonePressurePlate.POWERED);
    }
}
