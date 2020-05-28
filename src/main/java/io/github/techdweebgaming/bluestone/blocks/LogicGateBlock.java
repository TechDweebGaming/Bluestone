package io.github.techdweebgaming.bluestone.blocks;

import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneReceiver;
import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneTransceiverTileEntity;
import io.github.techdweebgaming.bluestone.tileentities.BluestoneTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class LogicGateBlock extends Block implements IBluestoneReceiver {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    private Class<? extends TileEntity> tileEntityClass;

    public LogicGateBlock(Properties properties, Class<? extends TileEntity> tileEntityClass) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(POWERED, false));
        this.tileEntityClass = tileEntityClass;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return getDefaultState();
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(POWERED);
    }

    @Override
    public void onBluestoneStateChange(boolean state, World sourceWorld, BlockPos sourcePos, World targetWorld, BlockPos targetPos) {
        if(targetWorld.getTileEntity(targetPos) != null && targetWorld.getTileEntity(targetPos) instanceof IBluestoneTransceiverTileEntity) {
            IBluestoneTransceiverTileEntity te = (IBluestoneTransceiverTileEntity) targetWorld.getTileEntity(targetPos);
            te.signalReceived(sourceWorld, sourcePos, targetWorld, targetPos, state);
            targetWorld.setBlockState(targetPos, targetWorld.getBlockState(targetPos).with(POWERED, te.getBluestoneState(targetWorld.getBlockState(targetPos))));
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return BluestoneTileEntities.getTileEntityFromClass(tileEntityClass);
    }

    @Override
    public ActionResultType func_225533_a_(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        player.sendStatusMessage(new StringTextComponent(String.format("%s | %s | %s", hit.getHitVec().x - pos.getX(), hit.getHitVec().y - pos.getY(), hit.getHitVec().z - pos.getZ())), true);
        return ActionResultType.PASS;
    }
}
