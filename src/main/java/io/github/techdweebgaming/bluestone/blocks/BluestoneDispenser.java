package io.github.techdweebgaming.bluestone.blocks;

import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneReceiver;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BluestoneDispenser extends DispenserBlock implements IBluestoneReceiver {

    protected BluestoneDispenser(Properties builder) {
        super(builder);
    }

    @Override
    public void onBluestoneStateChange(boolean state, World sourceWorld, BlockPos sourcePos, World targetWorld, BlockPos targetPos) {
        boolean oldState = targetWorld.getBlockState(targetPos).get(TRIGGERED);
        targetWorld.setBlockState(targetPos, targetWorld.getBlockState(targetPos).with(TRIGGERED, state));
        if(oldState == false && state == true) {
            targetWorld.getPendingBlockTicks().scheduleTick(targetPos, this, this.tickRate(targetWorld));
        }
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        return;
    }



}
