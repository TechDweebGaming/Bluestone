package io.github.techdweebgaming.bluestone.bluestonenetwork;

import net.minecraft.block.BlockState;

public interface IBluestoneTransmitterTileEntity {

    boolean getBluestoneState(BlockState state);

    boolean addBluestoneLink(BluestoneLink link);
    boolean removeBluestoneLink(BluestoneLink link);

    void clearLinks();

}
