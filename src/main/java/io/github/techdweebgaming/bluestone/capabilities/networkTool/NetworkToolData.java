package io.github.techdweebgaming.bluestone.capabilities.networkTool;

import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class NetworkToolData implements INetworkToolData {

    private int world;
    private BlockPos pos;
    private boolean isLinking;

    public NetworkToolData() {
        world = 0;
        pos = null;
        isLinking = true;
    }

    @Override
    public int getWorld() {
        return world;
    }

    @Override
    @Nullable
    public BlockPos getPos() {
        return pos;
    }

    @Override
    public boolean getIsLinking() {
        return isLinking;
    }

    @Override
    public void setWorld(int id) {
        world = id;
    }

    @Override
    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void setIsLinking(boolean isLinking) {
        this.isLinking = isLinking;
    }

    @Override
    public void clearData() {
        world = 0;
        pos = null;
    }
}
