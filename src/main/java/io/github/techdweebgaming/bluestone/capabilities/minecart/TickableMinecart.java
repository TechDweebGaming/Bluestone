package io.github.techdweebgaming.bluestone.capabilities.minecart;

import io.github.techdweebgaming.bluestone.Bluestone;
import io.github.techdweebgaming.bluestone.blocks.vanilla.BluestonePoweredRail;
import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.minecart.AbstractMinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.properties.RailShape;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TickableMinecart implements ITickableMinecart {

    private AbstractMinecartEntity minecart;

    public TickableMinecart(AbstractMinecartEntity minecart) {
        this.minecart = minecart;
    }

    @Override
    public void tick() {
        try {
            BlockPos railPos = new BlockPos(MathHelper.floor(minecart.posX), MathHelper.floor(minecart.posY), MathHelper.floor(minecart.posZ));
            BlockState railState = minecart.world.getBlockState(railPos);
            if(!(railState.getBlock() instanceof BluestonePoweredRail)) return;
            if (minecart.canUseRail() && railState.isIn(BlockTags.RAILS)) {
                if ((((BluestonePoweredRail) railState.getBlock()).isActivator)) {
                    minecart.onActivatorRailPass(railPos.getX(), railPos.getY(), railPos.getZ(), railState.get(PoweredRailBlock.POWERED));
                    return;
                }
            }

            boolean flag = railState.get(PoweredRailBlock.POWERED);
            boolean flag1 = !flag;
            AbstractRailBlock abstractRailBlock = (AbstractRailBlock)railState.getBlock();

            Entity entity = minecart.getPassengers().isEmpty() ? null : minecart.getPassengers().get(0);
            if (entity instanceof PlayerEntity && minecart.func_213296_b(entity.getMotion()) > 1.0E-4D && minecart.func_213296_b(minecart.getMotion()) < 0.01D) flag1 = false;

            if(flag1 && minecart.shouldDoRailFunctions()) {
                if (Math.sqrt(minecart.func_213296_b(minecart.getMotion())) < 0.03D) minecart.setMotion(Vec3d.ZERO);
                else minecart.setMotion(minecart.getMotion().mul(0.5D, 0.0D, 0.5D));
            }

            if(flag && minecart.shouldDoRailFunctions()) {
                Vec3d vec3d = minecart.getMotion();
                double d24 = Math.sqrt(minecart.func_213296_b(vec3d));
                if (d24 > 0.01D) {
                    minecart.setMotion(vec3d.add(vec3d.x / d24 * 0.06D, 0.0D, vec3d.z / d24 * 0.06D));
                } else {
                    double d17 = vec3d.x;
                    double d18 = vec3d.z;
                    RailShape railShape = abstractRailBlock.getRailDirection(railState, minecart.world, railPos, minecart);
                    Method func_213900_a = ObfuscationReflectionHelper.findMethod(AbstractMinecartEntity.class, "func_213900_a", BlockPos.class);
                    func_213900_a.setAccessible(true);
                    if (railShape == RailShape.EAST_WEST) {
                        if ((boolean)func_213900_a.invoke(minecart, railPos.west())) d17 = 0.02D;
                        else if ((boolean)func_213900_a.invoke(minecart,railPos.east())) d17 = -0.02D;
                    } else {
                        if (railShape != RailShape.NORTH_SOUTH) return;

                        if ((boolean)func_213900_a.invoke(minecart, railPos.north())) d18 = 0.02D;
                        else if ((boolean)func_213900_a.invoke(minecart, railPos.south())) d18 = -0.02D;
                    }

                    minecart.setMotion(d17, vec3d.y, d18);
                }
            }
        } catch (IllegalAccessException e) {
            Bluestone.logger.error(e.getMessage());
        } catch (InvocationTargetException e) {
            Bluestone.logger.error(e.getMessage());
        }
    }

    @Override
    public void onSpawn() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTick(TickEvent.WorldTickEvent event) {
        if(!event.phase.equals(TickEvent.Phase.END)) return;
        if(minecart == null || !minecart.isAlive()) {
            MinecraftForge.EVENT_BUS.unregister(this);
            return;
        }
        if(!minecart.world.equals(event.world)) return;

        tick();
    }

}
