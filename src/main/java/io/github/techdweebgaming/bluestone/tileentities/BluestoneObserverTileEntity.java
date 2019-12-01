package io.github.techdweebgaming.bluestone.tileentities;

import io.github.techdweebgaming.bluestone.blocks.BluestoneObserver;
import net.minecraft.block.BlockState;

public class BluestoneObserverTileEntity extends BluestoneTransmitterTileEntity {

    public BluestoneObserverTileEntity() {
        super(BluestoneTileEntities.bluestoneObserverTileEntity);
    }

    @Override
    public boolean getBluestoneState(BlockState state) {
        return state.get(BluestoneObserver.POWERED);
    }

}
