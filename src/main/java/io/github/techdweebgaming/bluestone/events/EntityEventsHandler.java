package io.github.techdweebgaming.bluestone.events;

import io.github.techdweebgaming.bluestone.Bluestone;
import io.github.techdweebgaming.bluestone.capabilities.minecart.ITickableMinecart;
import io.github.techdweebgaming.bluestone.capabilities.minecart.TickableMinecartProvider;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Bluestone.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EntityEventsHandler {

    @SubscribeEvent
    public static void onEntitySpawn(EntityJoinWorldEvent event) {
        if(event.getEntity().getEntityWorld().isRemote) return;
        if(event.getEntity() instanceof AbstractMinecartEntity) {
            LazyOptional<ITickableMinecart> lazyCap = event.getEntity().getCapability(TickableMinecartProvider.TICKABLE_MINECART_CAPABILITY, null);
            lazyCap.ifPresent(cap -> cap.onSpawn());
        }
    }

}
