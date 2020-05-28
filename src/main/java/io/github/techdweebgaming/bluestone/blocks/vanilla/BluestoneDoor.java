package io.github.techdweebgaming.bluestone.blocks.vanilla;

import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneReceiver;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BluestoneDoor extends DoorBlock implements IBluestoneReceiver {

    public BluestoneDoor(Properties builder) {
        super(builder);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos blockPos = context.getPos();
        if (blockPos.getY() < 255 && context.getWorld().getBlockState(blockPos.up()).isReplaceable(context)) {
            return super.getStateForPlacement(context).with(POWERED, false);
        }
        return null;
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromBlock, boolean isMoving) {
        return;
    }

    @Override
    public void onBluestoneStateChange(boolean state, World sourceWorld, BlockPos sourcePos, World targetWorld, BlockPos targetPos) {
        targetWorld.setBlockState(targetPos, targetWorld.getBlockState(targetPos).with(POWERED, state), 2);
        if(targetWorld.getBlockState(targetPos).get(OPEN) != state) {
            if(targetWorld.getBlockState(targetPos).get(HALF) == DoubleBlockHalf.UPPER) {
                toggleDoor(targetWorld, targetPos.func_177977_b(), state);
            } else {
                toggleDoor(targetWorld, targetPos, state);
            }
        }
    }
}
