package io.github.techdweebgaming.bluestone.tileentities;

import io.github.techdweebgaming.bluestone.blocks.BluestoneEmitter;
import io.github.techdweebgaming.bluestone.bluestoneNetwork.BluestoneLink;
import io.github.techdweebgaming.bluestone.bluestoneNetwork.IBluestoneTransmitter;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BluestoneTransmitterTileEntity extends TileEntity implements ITickableTileEntity, IBluestoneTransmitter {

    private List<BluestoneLink> bluestoneLinks = new ArrayList<BluestoneLink>();

    protected BluestoneTransmitterTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    public BluestoneTransmitterTileEntity() {
        this(BluestoneTileEntities.bluestoneEmitterTileEntity);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        int oldSize = nbt.size();
        nbt.putInt("count", bluestoneLinks.size());
        // Remove excess tags
        if(oldSize > bluestoneLinks.size()) {
            for(int i = oldSize; i > bluestoneLinks.size(); i--) {
                nbt.remove("link-" + (i - 1));
            }
        }
        for(int i = 0; i < bluestoneLinks.size(); i++) {
            nbt.put("link-" + i, bluestoneLinks.get(i).writeToNBT());
        }
        return nbt;
    }

    @Override
    public void read(CompoundNBT nbt) {
        for(int i = 0; i < nbt.getInt("count"); i++) {
            bluestoneLinks.add(BluestoneLink.readFromNBT(nbt.getCompound("link-" + i)));
        }
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
        world.setBlockState(pos, getBlockState().with(BluestoneEmitter.ENABLED, !world.isBlockPowered(pos)));
        Iterator<BluestoneLink> linkIterator = bluestoneLinks.iterator();
        while(linkIterator.hasNext()) {
            if(!linkIterator.next().emitToReceiver(getBlockState().get(BluestoneEmitter.ENABLED), world)) linkIterator.remove();
        }
    }

    @Override
    public boolean getBluestoneState(BlockState state) {
        return state.get(BluestoneEmitter.ENABLED);
    }

    @Override
    public void addBluestoneLink(BluestoneLink link) {
        bluestoneLinks.add(link);
    }

    @Override
    public boolean removeBluestoneLink(BluestoneLink link) {
        Iterator<BluestoneLink> linkIterator = bluestoneLinks.iterator();
        while(linkIterator.hasNext()) {
            BluestoneLink existingLink = linkIterator.next();
            if(link.sourcePos.getX() == existingLink.sourcePos.getX()
            && link.sourcePos.getY() == existingLink.sourcePos.getY()
            && link.sourcePos.getZ() == existingLink.sourcePos.getZ()
            && link.targetPos.getX() == existingLink.targetPos.getX()
            && link.targetPos.getY() == existingLink.targetPos.getY()
            && link.targetPos.getZ() == existingLink.targetPos.getZ()
            && link.sourceDimID == existingLink.sourceDimID
            && link.targetDimID == existingLink.targetDimID) {
                link.emitToReceiver(false, world);
                bluestoneLinks.remove(existingLink);
                return true;
            }
        }
        return false;
    }

    @Override
    public void disableAllLinks() {
        bluestoneLinks.forEach((link) -> link.emitToReceiver(false, world));
    }
}
