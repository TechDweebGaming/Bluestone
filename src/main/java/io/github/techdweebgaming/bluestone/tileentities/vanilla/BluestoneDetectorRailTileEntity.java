package io.github.techdweebgaming.bluestone.tileentities.vanilla;

import io.github.techdweebgaming.bluestone.blocks.vanilla.BluestoneDetectorRail;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTileEntities;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTransmitterTileEntity;
import net.minecraft.block.BlockState;

public class BluestoneDetectorRailTileEntity extends BluestoneTransmitterTileEntity {

    public BluestoneDetectorRailTileEntity() {
        super(BluestoneTileEntities.bluestoneDetectorRailTileEntity);
    }

    @Override
    public boolean getBluestoneState(BlockState state) {
        return state.get(BluestoneDetectorRail.POWERED);
    }
}
