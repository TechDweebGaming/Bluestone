package io.github.techdweebgaming.bluestone.blocks.vanilla;

import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneReceiver;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BluestonePoweredRail extends PoweredRailBlock implements IBluestoneReceiver {

    public boolean isActivator;

    public BluestonePoweredRail(boolean isActivator, Properties builder) {
        super(builder, isActivator);
        this.isActivator = isActivator;
    }

    @Override
    protected void updateState(BlockState state, World worldIn, BlockPos pos, Block blockIn) {
        return;
    }

    @Override
    public void onBluestoneStateChange(boolean state, World sourceWorld, BlockPos sourcePos, World targetWorld, BlockPos targetPos) {
        BlockState blockState = targetWorld.getBlockState(targetPos);
        boolean priorState = blockState.get(POWERED);
        if(priorState != state) {
            targetWorld.setBlockState(targetPos, blockState.with(POWERED, state), 3);
            targetWorld.notifyNeighborsOfStateChange(targetPos.down(), this);
            if (blockState.get(SHAPE).isAscending()) {
                targetWorld.notifyNeighborsOfStateChange(targetPos.up(), this);
            }
        }
    }
}
