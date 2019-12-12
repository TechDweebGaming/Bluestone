package io.github.techdweebgaming.bluestone.capabilities.minecart;

import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TickableMinecartProvider implements ICapabilityProvider {

    @CapabilityInject(ITickableMinecart.class)
    public static final Capability<ITickableMinecart> TICKABLE_MINECART_CAPABILITY = null;

    private LazyOptional<TickableMinecart> lazyInstance = LazyOptional.empty();

    private TickableMinecart instance = null;

    public TickableMinecartProvider(AbstractMinecartEntity cart) {
        instance = new TickableMinecart(cart);
        lazyInstance = LazyOptional.of(() -> instance).cast();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == TICKABLE_MINECART_CAPABILITY ? lazyInstance.cast() : LazyOptional.empty();
    }
}
