package io.github.techdweebgaming.bluestone.tileentities.vanilla;

import io.github.techdweebgaming.bluestone.blocks.vanilla.BluestoneButton;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTileEntities;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTransmitterTileEntity;
import net.minecraft.block.BlockState;

public class BluestoneButtonTileEntity extends BluestoneTransmitterTileEntity {

    public BluestoneButtonTileEntity() {
        super(BluestoneTileEntities.bluestoneButtonTileEntity);
    }

    @Override
    public boolean getBluestoneState(BlockState state) {
        return state.get(BluestoneButton.POWERED);
    }

}
