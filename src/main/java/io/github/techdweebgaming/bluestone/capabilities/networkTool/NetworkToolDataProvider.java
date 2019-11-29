package io.github.techdweebgaming.bluestone.capabilities.networkTool;

import io.github.techdweebgaming.bluestone.Bluestone;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NetworkToolDataProvider implements ICapabilitySerializable<INBT> {

    @CapabilityInject(INetworkToolData.class)
    public static final Capability<INetworkToolData> NETWORK_TOOL_DATA_CAPABILITY = null;

    private LazyOptional<INetworkToolData> instance = LazyOptional.of(NETWORK_TOOL_DATA_CAPABILITY::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == NETWORK_TOOL_DATA_CAPABILITY ? instance.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return NETWORK_TOOL_DATA_CAPABILITY.getStorage().writeNBT(NETWORK_TOOL_DATA_CAPABILITY, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        NETWORK_TOOL_DATA_CAPABILITY.getStorage().readNBT(NETWORK_TOOL_DATA_CAPABILITY, this.instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
    }
}
