package io.github.techdweebgaming.bluestone.tileentities;

import io.github.techdweebgaming.bluestone.bluestonenetwork.BluestoneLink;
import io.github.techdweebgaming.bluestone.bluestonenetwork.IBluestoneTransceiverTileEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BluestoneTransmitterTEUtils {

    public static CompoundNBT write(CompoundNBT nbt, List<BluestoneLink> bluestoneLinks) {
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

    public static List<BluestoneLink> read(CompoundNBT nbt) {
        List<BluestoneLink> bluestoneLinks = new ArrayList<BluestoneLink>();
        for(int i = 0; i < nbt.getInt("count"); i++) {
            bluestoneLinks.add(BluestoneLink.readFromNBT(nbt.getCompound("link-" + i)));
        }
        return bluestoneLinks;
    }

    public static boolean addBluestoneLink(BluestoneLink link, List<BluestoneLink> bluestoneLinks) {
        for(int i = 0; i < bluestoneLinks.size(); i++) {
            if(linksMatch(bluestoneLinks.get(i), link)) return false;
        }
        bluestoneLinks.add(link);
        return true;
    }

    public static boolean removeBluestoneLink(BluestoneLink link, List<BluestoneLink> bluestoneLinks, World world) {
        Iterator<BluestoneLink> linkIterator = bluestoneLinks.iterator();
        while(linkIterator.hasNext()) {
            BluestoneLink existingLink = linkIterator.next();
            if(linksMatch(link, existingLink)) {
                link.emitToReceiver(false, world);
                TileEntity te = world.getTileEntity(link.targetPos);
                if(te instanceof IBluestoneTransceiverTileEntity) {
                    IBluestoneTransceiverTileEntity transceiverTE = (IBluestoneTransceiverTileEntity) te;
                    transceiverTE.unlink(link);
                }
                bluestoneLinks.remove(existingLink);
                return true;
            }
        }
        return false;
    }

    public static boolean tick(List<BluestoneLink> bluestoneLinks, boolean state, World world, boolean newLinksOnly) {
        boolean isDirty = false;
        Iterator<BluestoneLink> linkIterator = bluestoneLinks.iterator();
        while(linkIterator.hasNext()) {
            BluestoneLink nextLink = linkIterator.next();
            if(newLinksOnly && !nextLink.newLink) continue;
            if(!nextLink.emitToReceiver(state, world)) {
                linkIterator.remove();
                isDirty = true;
            }
        }
        return isDirty;
    }

    public static boolean linksMatch(BluestoneLink link1, BluestoneLink link2) {
        return link1.sourcePos.getX() == link2.sourcePos.getX()
                && link1.sourcePos.getY() == link2.sourcePos.getY()
                && link1.sourcePos.getZ() == link2.sourcePos.getZ()
                && link1.targetPos.getX() == link2.targetPos.getX()
                && link1.targetPos.getY() == link2.targetPos.getY()
                && link1.targetPos.getZ() == link2.targetPos.getZ()
                && link1.sourceDimID == link2.sourceDimID
                && link1.targetDimID == link2.targetDimID;
    }

}
