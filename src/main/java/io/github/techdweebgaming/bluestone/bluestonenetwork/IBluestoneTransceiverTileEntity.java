package io.github.techdweebgaming.bluestone.bluestonenetwork;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBluestoneTransceiverTileEntity extends IBluestoneTransmitterTileEntity {

    void unlink(BluestoneLink link);

    void signalReceived(World sourceWorld, BlockPos sourcePos, World targetWorld, BlockPos targetPos, boolean state);

}
