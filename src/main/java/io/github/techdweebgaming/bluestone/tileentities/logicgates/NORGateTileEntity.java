package io.github.techdweebgaming.bluestone.tileentities.logicgates;

import io.github.techdweebgaming.bluestone.bluestonenetwork.BluestoneLink;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTileEntities;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTransceiverTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.Tuple;

public class NORGateTileEntity extends BluestoneTransceiverTileEntity {

    public NORGateTileEntity() {
        super(BluestoneTileEntities.norGateTileEntity);
    }

    @Override
    public boolean getBluestoneState(BlockState state) {
        for(Tuple<BluestoneLink, Boolean> link : inputLinks) {
            if(link.getB()) return false;
        }
        return true;
    }
}
