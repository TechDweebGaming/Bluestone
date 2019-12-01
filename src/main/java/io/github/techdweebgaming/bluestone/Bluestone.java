package io.github.techdweebgaming.bluestone;
import io.github.techdweebgaming.bluestone.capabilities.networktool.INetworkToolData;
import io.github.techdweebgaming.bluestone.capabilities.networktool.NetworkToolData;
import io.github.techdweebgaming.bluestone.capabilities.networktool.NetworkToolDataStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Bluestone.MODID)
public class Bluestone {

    public static final String MODID = "bluestone";

    public static final Logger logger = LogManager.getLogger(MODID);

    public static Bluestone instance;

    public Bluestone() {
        instance = this;

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Register Capabilities
        logger.debug("Registering Capabilities");
        CapabilityManager.INSTANCE.register(INetworkToolData.class, new NetworkToolDataStorage(), NetworkToolData::new);
    }

}
