package io.github.techdweebgaming.bluestone.blocks.vanilla;

import io.github.techdweebgaming.bluestone.tileentities.BluestoneTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BluestonePressurePlate extends PressurePlateBlock {

    public BluestonePressurePlate(Sensitivity sensitivity, Properties properties) {
        super(sensitivity, properties);
    }

    @Override
    public boolean canProvidePower(BlockState state) {
        return false;
    }

    @Override
    public int getStrongPower(BlockState state, IBlockReader access, BlockPos pos, Direction side) {
        return 0;
    }

    @Override
    public int getWeakPower(BlockState blockState, IBlockReader access, BlockPos pos, Direction side) {
        return 0;
    }

    @Override
    public boolean hasTileEntity(BlockState state) { return true; }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader reader) {
        return BluestoneTileEntities.bluestonePressurePlateTileEntity.create();
    }

}
