package io.github.techdweebgaming.bluestone.blocks;

import io.github.techdweebgaming.bluestone.bluestonenetwork.BluestoneLink;
import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneTransmitterTileEntity;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTileEntities;
import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BluestoneButton extends AbstractButtonBlock implements IBluestoneTransmitterTileEntity {

    private boolean wooden;

    protected BluestoneButton(boolean wooden, Properties properties) {
        super(wooden, properties);
        this.wooden = wooden;
    }

    @Override
    protected SoundEvent getSoundEvent(boolean turningOn) {
        if(wooden) return turningOn ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF;
        else return turningOn ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
    }

    @Override
    public int getWeakPower(BlockState blockState, IBlockReader reader, BlockPos pos, Direction side) {
        return 0;
    }

    @Override
    public int getStrongPower(BlockState blockState, IBlockReader reader, BlockPos pos, Direction side) {
        return 0;
    }

    @Override
    public boolean canProvidePower(BlockState state) {
        return false;
    }

    @Override
    public boolean getBluestoneState(BlockState state) {
        return false;
    }

    @Override
    public boolean addBluestoneLink(BluestoneLink link) {
        return false;
    }

    @Override
    public boolean removeBluestoneLink(BluestoneLink link) {
        return false;
    }

    @Override
    public void clearLinks() {

    }

    @Override
    public boolean hasTileEntity(BlockState state) { return true; }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return BluestoneTileEntities.bluestoneButtonTileEntity.create();
    }
}
