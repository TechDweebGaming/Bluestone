package io.github.techdweebgaming.bluestone.items;

import io.github.techdweebgaming.bluestone.Bluestone;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Bluestone.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Bluestone.MODID)
public class BluestoneItems {

    public static final Item bluestone = new Item(new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName(Bluestone.MODID, "bluestone");
    public static final Item networkTool = new NetworkTool(new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName(Bluestone.MODID, "network_tool");
    public static final Item bluestoneTorch = new Item(new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName(Bluestone.MODID, "bluestone_torch");

    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                bluestone,
                networkTool,
                bluestoneTorch
        );
    }

}
