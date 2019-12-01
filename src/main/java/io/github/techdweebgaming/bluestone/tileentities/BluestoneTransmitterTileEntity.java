package io.github.techdweebgaming.bluestone.tileentities;

import io.github.techdweebgaming.bluestone.bluestonenetwork.BluestoneLink;
import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneTransceiverTileEntity;
import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneTransmitterTileEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BluestoneTransmitterTileEntity extends TileEntity implements ITickableTileEntity, IBluestoneTransmitterTileEntity {

    protected List<BluestoneLink> bluestoneLinks = new ArrayList<BluestoneLink>();
    protected boolean lastEmittedState;

    protected BluestoneTransmitterTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);
        return BluestoneTransmitterTEUtils.write(nbt, bluestoneLinks);
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);
        bluestoneLinks = BluestoneTransmitterTEUtils.read(nbt);
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return write(new CompoundNBT());
    }

    @Override
    public void handleUpdateTag(CompoundNBT nbt) {
        read(nbt);
    }

    @Override
    public void tick() {
        boolean state = getBluestoneState(getBlockState());
        if(BluestoneTransmitterTEUtils.tick(bluestoneLinks, state, world, lastEmittedState == state)) markDirty();
        lastEmittedState = state;
    }

    @Override
    public boolean addBluestoneLink(BluestoneLink link) {
            if(BluestoneTransmitterTEUtils.addBluestoneLink(link, bluestoneLinks)) {
                markDirty();
                return true;
            }
        return false;
    }

    @Override
    public boolean removeBluestoneLink(BluestoneLink link) {
            if(BluestoneTransmitterTEUtils.removeBluestoneLink(link, bluestoneLinks, world)) {
                markDirty();
                return true;
            }
        return false;
    }

    @Override
    public void clearLinks() {
            Iterator<BluestoneLink> linkIterator = bluestoneLinks.iterator();
            while(linkIterator.hasNext()) {
                BluestoneLink link = linkIterator.next();
                link.emitToReceiver(false, world);
                // TODO Add Interdimensional Support
                TileEntity te = world.getTileEntity(link.targetPos);
                if(te instanceof IBluestoneTransceiverTileEntity) {
                    IBluestoneTransceiverTileEntity transceiverTE = (IBluestoneTransceiverTileEntity) te;
                    transceiverTE.unlink(link);
                }
            }
            markDirty();
    }
}
