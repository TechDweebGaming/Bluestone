package io.github.techdweebgaming.bluestone.bluestoneNetwork;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBluestoneReceiver {

    void onBluestoneStateChange(boolean state, World sourceWorld, BlockPos sourcePos, World targetWorld, BlockPos targetPos);

}
