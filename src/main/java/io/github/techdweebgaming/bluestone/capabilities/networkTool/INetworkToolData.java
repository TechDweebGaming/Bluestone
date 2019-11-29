package io.github.techdweebgaming.bluestone.capabilities.networkTool;

import net.minecraft.util.math.BlockPos;

public interface INetworkToolData {

    int getWorld();
    BlockPos getPos();
    boolean getIsLinking();

    void setWorld(int id);
    void setPos(BlockPos pos);
    void setIsLinking(boolean isLinking);

    void clearData();

}
