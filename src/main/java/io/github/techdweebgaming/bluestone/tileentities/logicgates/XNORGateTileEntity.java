package io.github.techdweebgaming.bluestone.tileentities.logicgates;

import io.github.techdweebgaming.bluestone.bluestonenetwork.BluestoneLink;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTileEntities;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTransceiverTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.Tuple;

public class XNORGateTileEntity extends BluestoneTransceiverTileEntity {

    public XNORGateTileEntity() {
        super(BluestoneTileEntities.xnorGateTileEntity);
    }

    @Override
    public boolean getBluestoneState(BlockState state) {
        boolean output = true;
        for(Tuple<BluestoneLink, Boolean> link : inputLinks) {
            if(!link.getB()) {
                if(!output) return true;
                output = false;
            }
        }
        return output;
    }

}
