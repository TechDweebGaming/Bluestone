package io.github.techdweebgaming.bluestone.items;

import io.github.techdweebgaming.bluestone.bluestoneNetwork.BluestoneLink;
import io.github.techdweebgaming.bluestone.bluestoneNetwork.IBluestoneReceiver;
import io.github.techdweebgaming.bluestone.bluestoneNetwork.IBluestoneTransmitter;
import io.github.techdweebgaming.bluestone.capabilities.networkTool.INetworkToolData;
import io.github.techdweebgaming.bluestone.capabilities.networkTool.NetworkToolDataProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;


public class NetworkTool extends Item {

    public NetworkTool(Properties properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(!stack.getCapability(NetworkToolDataProvider.NETWORK_TOOL_DATA_CAPABILITY).isPresent()) return;
        INetworkToolData capability = stack.getCapability(NetworkToolDataProvider.NETWORK_TOOL_DATA_CAPABILITY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
        tooltip.add(new StringTextComponent("Mode: ").applyTextStyle(TextFormatting.DARK_AQUA).appendSibling(capability.getIsLinking()
                ? new StringTextComponent("Linking").applyTextStyle(TextFormatting.DARK_GREEN)
                : new StringTextComponent("Unlinking").applyTextStyle(TextFormatting.DARK_RED)));
        tooltip.add(new StringTextComponent("Source: ").applyTextStyle(TextFormatting.DARK_AQUA).appendSibling(capability.getPos() == null
                ? new StringTextComponent("Not Set").applyTextStyle(TextFormatting.DARK_RED)
                : new StringTextComponent(String.format("%s, %s, %s", capability.getPos().getX(), capability.getPos().getY(), capability.getPos().getZ())).applyTextStyle(TextFormatting.DARK_GREEN)));
        // TODO Add Dimension Tooltip
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(playerIn.isSneaking() && playerIn.getPitch(1.0F) < -80) {
            INetworkToolData capability = playerIn.getHeldItem(handIn).getCapability(NetworkToolDataProvider.NETWORK_TOOL_DATA_CAPABILITY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
            capability.clearData();
            playerIn.sendStatusMessage(new StringTextComponent("Cleared Linked Coordinates!"), true);
            return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return new ActionResult<>(ActionResultType.PASS, playerIn.getHeldItem(handIn));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        INetworkToolData capability = context.getItem().getCapability(NetworkToolDataProvider.NETWORK_TOOL_DATA_CAPABILITY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));

        if(context.isPlacerSneaking()) {
            capability.setIsLinking(!capability.getIsLinking());
            capability.clearData();
            String modeName = capability.getIsLinking() ? "Linking" : "Unlinking";
            context.getPlayer().sendStatusMessage(new StringTextComponent(String.format("Mode Set To: %s!", modeName)), true);
            return ActionResultType.SUCCESS;
        }

        if(context.getWorld().getTileEntity(context.getPos()) instanceof IBluestoneTransmitter) {
            capability.setWorld(context.getWorld().getWorldType().getId());
            capability.setPos(context.getPos());
            context.getPlayer().sendStatusMessage(new StringTextComponent("Source Set Successfully!"), true);
            return ActionResultType.SUCCESS;
        } else if(context.getWorld().getBlockState(context.getPos()).getBlock() instanceof IBluestoneReceiver) {
            if(capability.getPos() == null) {
                context.getPlayer().sendStatusMessage(new StringTextComponent("Must Link Source First!"), true);
                return ActionResultType.SUCCESS;
            } else {
                BluestoneLink link = new BluestoneLink(capability.getWorld(), context.getWorld().getWorldType().getId(), capability.getPos(), context.getPos());
                // TODO Fix Interdimensional Linking
                IBluestoneTransmitter transmitterTE = (IBluestoneTransmitter) context.getWorld().getTileEntity(capability.getPos());
                if(capability.getIsLinking()) {
                    transmitterTE.addBluestoneLink(link);
                    context.getPlayer().sendStatusMessage(new StringTextComponent("Blocks Linked!"), true);
                } else {
                    if(transmitterTE.removeBluestoneLink(link)) {
                        context.getPlayer().sendStatusMessage(new StringTextComponent("Blocks Unlinked!"), true);
                    }
                    else context.getPlayer().sendStatusMessage(new StringTextComponent("Blocks Not Linked!"), true);
                }
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

}
