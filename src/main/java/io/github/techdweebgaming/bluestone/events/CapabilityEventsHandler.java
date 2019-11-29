package io.github.techdweebgaming.bluestone.events;

import io.github.techdweebgaming.bluestone.Bluestone;
import io.github.techdweebgaming.bluestone.capabilities.networkTool.NetworkToolDataProvider;
import io.github.techdweebgaming.bluestone.items.NetworkTool;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Bluestone.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityEventsHandler {

    public static final ResourceLocation NETWORK_TOOL_DATA_CAP = new ResourceLocation(Bluestone.MODID, "network_tool_data");

    @SubscribeEvent
    public static void attachItemCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if(event.getObject().getItem() instanceof NetworkTool) {
            event.addCapability(NETWORK_TOOL_DATA_CAP, new NetworkToolDataProvider());
        }
    }

}
