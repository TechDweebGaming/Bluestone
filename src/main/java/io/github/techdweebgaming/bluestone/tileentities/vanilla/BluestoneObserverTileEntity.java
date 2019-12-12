package io.github.techdweebgaming.bluestone.tileentities.vanilla;

import io.github.techdweebgaming.bluestone.blocks.vanilla.BluestoneObserver;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTileEntities;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTransmitterTileEntity;
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
