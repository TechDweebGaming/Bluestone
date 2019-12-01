package io.github.techdweebgaming.bluestone.blocks;

import io.github.techdweebgaming.bluestone.tileentities.BluestoneTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class BluestoneEmitter extends Block {

    public static final BooleanProperty ENABLED = BlockStateProperties.ENABLED;

    protected BluestoneEmitter(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(ENABLED, true));
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
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return BluestoneTileEntities.bluestoneEmitterTileEntity.create();
    }
}
