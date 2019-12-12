package io.github.techdweebgaming.bluestone.blocks.vanilla;

import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneReceiver;
import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneTransmitterTileEntity;
import io.github.techdweebgaming.bluestone.items.InDevBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.state.PistonBlockStructureHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.PistonTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BluestonePiston extends PistonBlock implements IBluestoneReceiver, InDevBlockItem.IInDevBlock {

    public BluestonePiston(boolean sticky, Properties properties) {
        super(sticky, properties);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        return;
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        return;
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        return;
    }

    @Override
    public void onBluestoneStateChange(boolean bsState, World sourceWorld, BlockPos sourcePos, World targetWorld, BlockPos targetPos) {
        BlockState state = targetWorld.getBlockState(targetPos);
        Direction direction = state.get(FACING);
        if(targetWorld.getBlockState(targetPos.offset(direction, 1)).getBlock() instanceof IBluestoneReceiver || targetWorld.getTileEntity(targetPos.offset(direction, 1)) instanceof IBluestoneTransmitterTileEntity) return;
        if(bsState && !state.get(EXTENDED)) {
            if ((new PistonBlockStructureHelper(targetWorld, targetPos, direction, true)).canMove()) {
                targetWorld.addBlockEvent(targetPos, this, 0, direction.getIndex());
            }
        } else if (!bsState && state.get(EXTENDED)) {
            BlockPos extendedPos = targetPos.offset(direction, 2);
            BlockState extendedState = targetWorld.getBlockState(extendedPos);
            int i = 1;
            if (extendedState.getBlock() == Blocks.MOVING_PISTON && extendedState.get(FACING) == direction) {
                PistonTileEntity tileEntity = (PistonTileEntity) targetWorld.getTileEntity(extendedPos);
                if(targetWorld instanceof ServerWorld) {
                    if(tileEntity.isExtending() && (tileEntity.getProgress(0.0F) < 0.5F || targetWorld.getGameTime() == tileEntity.getLastTicked() || ((ServerWorld)targetWorld).isInsideTick())) {
                        i = 2;
                    }
                }
            }

            targetWorld.addBlockEvent(targetPos, this, i, direction.getIndex());
        }
    }
}
