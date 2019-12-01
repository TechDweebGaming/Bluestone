package io.github.techdweebgaming.bluestone.tileentities;

import io.github.techdweebgaming.bluestone.blocks.BluestoneLever;
import net.minecraft.block.BlockState;

public class BluestoneLeverTileEntity extends BluestoneTransmitterTileEntity {

    public BluestoneLeverTileEntity() {
        super(BluestoneTileEntities.bluestoneLeverTileEntity);
    }

    @Override
    public boolean getBluestoneState(BlockState state) {
        return state.get(BluestoneLever.POWERED);
    }

}
