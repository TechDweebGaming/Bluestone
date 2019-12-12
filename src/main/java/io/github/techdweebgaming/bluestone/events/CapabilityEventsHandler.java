package io.github.techdweebgaming.bluestone.events;

import io.github.techdweebgaming.bluestone.Bluestone;
import io.github.techdweebgaming.bluestone.capabilities.minecart.TickableMinecart;
import io.github.techdweebgaming.bluestone.capabilities.minecart.TickableMinecartProvider;
import io.github.techdweebgaming.bluestone.capabilities.networktool.NetworkToolDataProvider;
import io.github.techdweebgaming.bluestone.items.NetworkTool;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Bluestone.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityEventsHandler {

    public static final ResourceLocation NETWORK_TOOL_DATA_CAP = new ResourceLocation(Bluestone.MODID, "network_tool_data");
    public static final ResourceLocation TICKABLE_MINECART_CAP = new ResourceLocation(Bluestone.MODID, "tickable_minecart");

    @SubscribeEvent
    public static void attachItemCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if(event.getObject().getItem() instanceof NetworkTool) {
            event.addCapability(NETWORK_TOOL_DATA_CAP, new NetworkToolDataProvider());
        }
    }

    @SubscribeEvent
    public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof AbstractMinecartEntity) {
            if(event.getObject().getEntityWorld().isRemote) return;

            if(event.getObject().getCapability(TickableMinecartProvider.TICKABLE_MINECART_CAPABILITY, null).equals(LazyOptional.empty())) event.addCapability(TICKABLE_MINECART_CAP, new TickableMinecartProvider((AbstractMinecartEntity)event.getObject()));
        }
    }

}
