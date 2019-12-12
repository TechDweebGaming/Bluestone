package io.github.techdweebgaming.bluestone.tileentities.logicgates;

import io.github.techdweebgaming.bluestone.bluestonenetwork.BluestoneLink;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTileEntities;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTransceiverTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.Tuple;

public class ORGateTileEntity extends BluestoneTransceiverTileEntity {

    public ORGateTileEntity() {
        super(BluestoneTileEntities.orGateTileEntity);
    }

    @Override
    public boolean getBluestoneState(BlockState state) {
        for(Tuple<BluestoneLink, Boolean> link : inputLinks) {
            if(link.getB()) return true;
        }
        return false;
    }
}
