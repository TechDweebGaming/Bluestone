package io.github.techdweebgaming.bluestone;
import io.github.techdweebgaming.bluestone.capabilities.minecart.ITickableMinecart;
import io.github.techdweebgaming.bluestone.capabilities.minecart.TickableMinecart;
import io.github.techdweebgaming.bluestone.capabilities.minecart.TickableMinecartStorage;
import io.github.techdweebgaming.bluestone.capabilities.networktool.INetworkToolData;
import io.github.techdweebgaming.bluestone.capabilities.networktool.NetworkToolData;
import io.github.techdweebgaming.bluestone.capabilities.networktool.NetworkToolDataStorage;
import io.github.techdweebgaming.bluestone.items.BluestoneItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IMinecartCollisionHandler;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

@Mod(Bluestone.MODID)
public class Bluestone {

    public static final String MODID = "bluestone";

    public static final Logger logger = LogManager.getLogger(MODID);

    public static Bluestone instance;

    public static final ItemGroup bluestoneCreativeTab = new ItemGroup(MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BluestoneItems.bluestone);
        }

        @Override
        public boolean hasSearchBar() {
            return true;
        }
    }.setBackgroundImageName("item_search.png");

    public Bluestone() {
        instance = this;

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Register Capabilities
        logger.debug("Registering Capabilities");
        CapabilityManager.INSTANCE.register(INetworkToolData.class, new NetworkToolDataStorage(), NetworkToolData::new);
        CapabilityManager.INSTANCE.register(ITickableMinecart.class, new TickableMinecartStorage(), () -> new TickableMinecart(null));
    }

}
