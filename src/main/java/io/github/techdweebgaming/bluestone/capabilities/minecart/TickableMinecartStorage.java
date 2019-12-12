package io.github.techdweebgaming.bluestone.capabilities.minecart;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class TickableMinecartStorage implements Capability.IStorage<ITickableMinecart> {
    @Nullable
    @Override
    public INBT writeNBT(Capability<ITickableMinecart> capability, ITickableMinecart instance, Direction side) {
        return new CompoundNBT();
    }

    @Override
    public void readNBT(Capability<ITickableMinecart> capability, ITickableMinecart instance, Direction side, INBT nbt) {
        return;
    }
}
