package io.github.techdweebgaming.bluestone.bluestoneNetwork;

import net.minecraft.block.BlockState;

public interface IBluestoneTransmitter {

    boolean getBluestoneState(BlockState state);

    void addBluestoneLink(BluestoneLink link);
    boolean removeBluestoneLink(BluestoneLink link);

    void disableAllLinks();

}
