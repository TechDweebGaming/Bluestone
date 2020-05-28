package io.github.techdweebgaming.bluestone.blocks.vanilla;

import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneReceiver;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class BluestoneLamp extends RedstoneLampBlock implements IBluestoneReceiver {

    public BluestoneLamp(Properties properties) {
        super(properties);
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        return;
    }

    @Override
    public int getLightValue(BlockState state) {
        return state.get(LIT) ? 15 : 0;
    }

    @Override
    public void func_225534_a_(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        return;
    }

    @Override
    public void onBluestoneStateChange(boolean state, World sourceWorld, BlockPos sourcePos, World targetWorld, BlockPos targetPos) {
        targetWorld.setBlockState(targetPos, targetWorld.getBlockState(targetPos).with(LIT, state), 2);
        targetWorld.getPendingBlockTicks().scheduleTick(targetPos, this, tickRate(targetWorld));
    }
}
