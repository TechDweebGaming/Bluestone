package io.github.techdweebgaming.bluestone.capabilities.networktool;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class NetworkToolDataStorage implements Capability.IStorage<INetworkToolData> {

    @Nullable
    @Override
    public INBT writeNBT(Capability<INetworkToolData> capability, INetworkToolData instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("world", instance.getWorld());
        if(instance.getPos() != null) {
            nbt.putInt("x", instance.getPos().getX());
            nbt.putInt("y", instance.getPos().getY());
            nbt.putInt("z", instance.getPos().getZ());
        }
        nbt.putBoolean("isLinking", instance.getIsLinking());
        return nbt;
    }

    @Override
    public void readNBT(Capability<INetworkToolData> capability, INetworkToolData instance, Direction side, INBT nbtRaw) {
        if(nbtRaw == null || !(nbtRaw instanceof CompoundNBT)) return;
        CompoundNBT nbt = (CompoundNBT) nbtRaw;
        instance.setWorld(nbt.getInt("world"));
        if(nbt.hasUniqueId("x")) {
            instance.setPos(new BlockPos(nbt.getInt("x"), nbt.getInt("y"), nbt.getInt("z")));
        } else instance.setPos(null);
        instance.setIsLinking(nbt.getBoolean("isLinking"));
    }
}
