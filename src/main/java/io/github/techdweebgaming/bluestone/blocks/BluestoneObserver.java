package io.github.techdweebgaming.bluestone.blocks;

import io.github.techdweebgaming.bluestone.tileentities.BluestoneTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.ObserverBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BluestoneObserver extends ObserverBlock {

    public BluestoneObserver(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canProvidePower(BlockState state) { return false; }

    @Override
    public int getStrongPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return 0;
    }

    @Override
    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return 0;
    }

    @Override
    public boolean hasTileEntity(BlockState state) { return true; }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return BluestoneTileEntities.bluestoneObserverTileEntity.create();
    }
}
