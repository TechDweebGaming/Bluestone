package io.github.techdweebgaming.bluestone.tileentities;

import io.github.techdweebgaming.bluestone.blocks.BluestoneButton;
import net.minecraft.block.BlockState;

public class BluestoneButtonTileEntity extends BluestoneTransmitterTileEntity {

    protected BluestoneButtonTileEntity() {
        super(BluestoneTileEntities.bluestoneButtonTileEntity);
    }

    @Override
    public boolean getBluestoneState(BlockState state) {
        return state.get(BluestoneButton.POWERED);
    }

}
