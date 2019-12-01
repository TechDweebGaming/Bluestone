package io.github.techdweebgaming.bluestone.integration.jei;

import io.github.techdweebgaming.bluestone.Bluestone;
import io.github.techdweebgaming.bluestone.blocks.BluestoneBlocks;
import io.github.techdweebgaming.bluestone.items.BluestoneItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Bluestone.MODID, "bluestone");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addIngredientInfo(new ItemStack(BluestoneItems.networkTool), VanillaTypes.ITEM,
                "The network tool is a nifty device used to link blocks attuned to the bluestone network. In order to link two blocks, one must simply right click the transmitter of their choice, " +
                        "followed by doing the same with the receiver. These two blocks will be linked, and the state of the first will be copied by the second. To unlink the blocks, shift right click the " +
                        "tool and follow the same steps. If you wish to clear the stored transmitter, shift right click while looking straight up and at air.");
        registration.addIngredientInfo(new ItemStack(BluestoneBlocks.bluestoneEmitter), VanillaTypes.ITEM,
                "The simplest transmitter attuned to the bluestone network. Emits a constant ON signal, which can be toggled off via a redstone signal.");
        registration.addIngredientInfo(new ItemStack(BluestoneBlocks.bluestoneReceiver), VanillaTypes.ITEM,
                "The simplest receiver attuned to the bluestone network. Emits a redstone signal when receiving an ON bluestone signal.");
        registration.addIngredientInfo(new ItemStack(BluestoneItems.bluestone), VanillaTypes.ITEM,
                "You've come to realise that enchanting tables draw their power from a network of energy accessible via lapis, dubbed the bluestone network. When lapis is mixed with ordinary redstone, " +
                        "you are able to attune it to this network, allowing you to transfer redstone signals wirelessly over great distances. However, variable signal strength is lost in this transmission.");
    }
}
