package io.github.techdweebgaming.bluestone.tileentities.logicgates;

import io.github.techdweebgaming.bluestone.bluestonenetwork.BluestoneLink;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTileEntities;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTransceiverTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.Tuple;

public class XORGateTileEntity extends BluestoneTransceiverTileEntity {

    public XORGateTileEntity() {
        super(BluestoneTileEntities.xorGateTileEntity);
    }

    @Override
    public boolean getBluestoneState(BlockState state) {
        boolean output = false;
        for(Tuple<BluestoneLink, Boolean> link : inputLinks) {
            if(link.getB()) {
                if(output) return false;
                output = true;
            }
        }
        return output;
    }
}
