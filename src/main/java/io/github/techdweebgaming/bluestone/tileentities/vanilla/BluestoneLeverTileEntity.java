package io.github.techdweebgaming.bluestone.tileentities.vanilla;

import io.github.techdweebgaming.bluestone.blocks.vanilla.BluestoneLever;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTileEntities;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTransmitterTileEntity;
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
