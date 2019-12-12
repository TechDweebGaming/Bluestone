package io.github.techdweebgaming.bluestone.tileentities;

import io.github.techdweebgaming.bluestone.bluestonenetwork.BluestoneLink;
import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneTransceiverTileEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BluestoneTransceiverTileEntity extends BluestoneTransmitterTileEntity implements IBluestoneTransceiverTileEntity {

    protected List<Tuple<BluestoneLink, Boolean>> inputLinks = new ArrayList<>();

    protected BluestoneTransceiverTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        super.write(nbt);
        int oldSize = nbt.size();
        nbt.putInt("in-count", inputLinks.size());
        if(oldSize > inputLinks.size()) {
            for(int i = oldSize; i > inputLinks.size(); i--) {
                nbt.remove("in-link-" + (i - 1));
                nbt.remove("in-link-" + (i -1) + "-state");
            }
        }
        for(int i = 0; i < inputLinks.size(); i++) {
            nbt.put("in-link-" + i, inputLinks.get(i).getA().writeToNBT());
            nbt.putBoolean("in-link-" + i + "-state", inputLinks.get(i).getB());
        }
        return nbt;
    }

    @Override
    public void read(CompoundNBT nbt) {
        super.read(nbt);
        for(int i = 0; i < nbt.getInt("in-count"); i++) {
            inputLinks.add(new Tuple<>(BluestoneLink.readFromNBT(nbt.getCompound("in-link-" + i)), nbt.getBoolean("in-link-" + i + "-state")));
        }
    }

    @Override
    public void unlink(BluestoneLink link) {
        Iterator<Tuple<BluestoneLink, Boolean>> linkIterator = inputLinks.iterator();
        while(linkIterator.hasNext()) {
            if(BluestoneTransmitterTEUtils.linksMatch(link, linkIterator.next().getA())) linkIterator.remove();
        }
    }

    @Override
    public void signalReceived(World sourceWorld, BlockPos sourcePos, World targetWorld, BlockPos targetPos, boolean state) {
        BluestoneLink link = new BluestoneLink(sourceWorld.dimension.getType().getId(), targetWorld.dimension.getType().getId(), sourcePos, targetPos);
        for(int i = 0; i < inputLinks.size(); i++) {
            if(BluestoneTransmitterTEUtils.linksMatch(inputLinks.get(i).getA(), link)) {
                if(state != inputLinks.get(i).getB()) {
                    inputLinks.set(i, new Tuple<>(inputLinks.get(i).getA(), state));
                }
                return;
            }
        }
        inputLinks.add(new Tuple<>(link, state));
    }
}
