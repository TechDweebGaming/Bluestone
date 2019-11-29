package io.github.techdweebgaming.bluestone.events;

import io.github.techdweebgaming.bluestone.Bluestone;
import io.github.techdweebgaming.bluestone.bluestoneNetwork.IBluestoneTransmitter;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Bluestone.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BlockEventsHandler {

    @SubscribeEvent
    public static void onBlockBroken(BlockEvent.BreakEvent event) {
        if(event.getWorld().getTileEntity(event.getPos()) instanceof IBluestoneTransmitter) {
            ((IBluestoneTransmitter) event.getWorld().getTileEntity(event.getPos())).disableAllLinks();
        }
    }
}
