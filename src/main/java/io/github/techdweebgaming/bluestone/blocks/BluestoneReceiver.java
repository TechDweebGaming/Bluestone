package io.github.techdweebgaming.bluestone.blocks;

import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneReceiver;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BluestoneReceiver extends Block implements IBluestoneReceiver {

    public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;

    protected BluestoneReceiver(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(ENABLED, false));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return getDefaultState();
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(ENABLED);
    }

    @Override
    public void onBluestoneStateChange(boolean state, World sourceWorld, BlockPos sourcePos, World targetWorld, BlockPos targetPos) {
        targetWorld.setBlockState(targetPos, targetWorld.getBlockState(targetPos).with(ENABLED, state));
        targetWorld.getPendingBlockTicks().scheduleTick(targetPos, this, tickRate(targetWorld));
    }

    @Override
    public boolean canProvidePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakPower(BlockState state, IBlockReader reader, BlockPos pos, Direction side) {
        return state.get(ENABLED) ? 15 : 0;
    }

}
