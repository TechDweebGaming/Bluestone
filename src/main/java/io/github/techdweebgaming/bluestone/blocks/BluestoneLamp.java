package io.github.techdweebgaming.bluestone.blocks;

import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneReceiver;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        return;
    }

    @Override
    public void onBluestoneStateChange(boolean state, World sourceWorld, BlockPos sourcePos, World targetWorld, BlockPos targetPos) {
        targetWorld.setBlockState(targetPos, targetWorld.getBlockState(targetPos).with(LIT, state));
        targetWorld.getPendingBlockTicks().scheduleTick(targetPos, this, tickRate(targetWorld));
    }
}
