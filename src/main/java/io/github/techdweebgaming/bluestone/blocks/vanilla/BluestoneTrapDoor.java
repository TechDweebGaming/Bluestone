package io.github.techdweebgaming.bluestone.blocks.vanilla;

import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneReceiver;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BluestoneTrapDoor extends TrapDoorBlock implements IBluestoneReceiver {

    public BluestoneTrapDoor(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context).with(POWERED, false);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        return;
    }

    @Override
    public void onBluestoneStateChange(boolean state, World sourceWorld, BlockPos sourcePos, World targetWorld, BlockPos targetPos) {
        targetWorld.setBlockState(targetPos, targetWorld.getBlockState(targetPos).with(POWERED, state).with(OPEN, state), 2);
        playSound(null, targetWorld, targetPos, state);
    }
}
