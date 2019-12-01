package io.github.techdweebgaming.bluestone.bluestonenetwork;

import net.minecraft.block.Block;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BluestoneLink {

    public boolean newLink = true;

    public int sourceDimID;
    public int targetDimID;
    public BlockPos sourcePos;
    public BlockPos targetPos;

    public BluestoneLink(int sourceDimID, int targetDimID, BlockPos sourcePos, BlockPos targetPos) {
        this.sourceDimID = sourceDimID;
        this.targetDimID = targetDimID;
        this.sourcePos = sourcePos;
        this.targetPos = targetPos;
    }

    public INBT writeToNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("srcX", sourcePos.getX());
        nbt.putInt("srcY", sourcePos.getY());
        nbt.putInt("srcZ", sourcePos.getZ());
        nbt.putInt("srcDimID", sourceDimID);
        nbt.putInt("tgtX", targetPos.getX());
        nbt.putInt("tgtY", targetPos.getY());
        nbt.putInt("tgtZ", targetPos.getZ());
        nbt.putInt("tgtDimID", targetDimID);
        return nbt;
    }

    public static BluestoneLink readFromNBT(CompoundNBT nbt) {
        BlockPos sourcePos = new BlockPos(nbt.getInt("srcX"), nbt.getInt("srcY"), nbt.getInt("srcZ"));
        BlockPos targetPos = new BlockPos(nbt.getInt("tgtX"), nbt.getInt("tgtY"), nbt.getInt("tgtZ"));

        return new BluestoneLink(nbt.getInt("srcDimID"), nbt.getInt("tgtDimID"), sourcePos, targetPos);
    }

    public boolean emitToReceiver(boolean desiredState, World worldIn) {
        Block targetBlock = worldIn.getBlockState(targetPos).getBlock();
        if(!(targetBlock instanceof IBluestoneReceiver)) return false;
        newLink = false;

        IBluestoneReceiver targetReceiver = (IBluestoneReceiver) targetBlock;
        // TODO Fix Interdimensional Linking
        //targetReceiver.onBluestoneStateChange(desiredState, worldIn, sourcePos, worldIn.getServer().getWorld(DimensionType.getById(0)), targetPos);
        targetReceiver.onBluestoneStateChange(desiredState, worldIn, sourcePos, worldIn, targetPos);
        return true;
    }

}
