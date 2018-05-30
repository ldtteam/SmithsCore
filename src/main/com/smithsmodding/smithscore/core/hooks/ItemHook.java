package com.smithsmodding.smithscore.core.hooks;

import com.smithsmodding.smithscore.SmithsCore;
import com.smithsmodding.smithscore.common.capability.SmithsCoreCapabilityDispatcher;
import com.smithsmodding.smithscore.core.util.Constants;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemHook
{
    /**
     * Ensures that the share tag is created regardless of item.
     *
     * @return always true
     * @author Orion
     */
    @Overwrite
    public boolean getShareTag()
    {
        return true;
    }

    @Inject(method = "getNBTShareTag", at = @At("RETURN"), cancellable = true)
    public void onGetNBTShareTag(final ItemStack stack, final CallbackInfoReturnable<NBTTagCompound> callbackInfoReturnable)
    {
        NBTTagCompound compound = callbackInfoReturnable.getReturnValue();
        if (compound == null)
        {
            compound = new NBTTagCompound();
        }

        if (SmithsCore.isInDevEnvironment())
        {
            SmithsCore.getLogger().info("WRITE -> PRE: " + compound);
        }

        if (!stack.hasCapability(SmithsCoreCapabilityDispatcher.INSTANCE_CAPABILITY, null))
        {
            if (SmithsCore.isInDevEnvironment())
            {
                SmithsCore.getLogger().info("WRITE -> NO Dispatcher found.");
            }

            return;
        }

        final SmithsCoreCapabilityDispatcher.IInstanceCap instanceCap = stack.getCapability(SmithsCoreCapabilityDispatcher.INSTANCE_CAPABILITY, null);
        if (instanceCap == null)
        {
            if (SmithsCore.isInDevEnvironment())
            {
                SmithsCore.getLogger().info("WRITE -> Capability is null.");
            }

            return;
        }

        final SmithsCoreCapabilityDispatcher dispatcher = instanceCap.getDispatcher();
        if (dispatcher == null)
        {
            if (SmithsCore.isInDevEnvironment())
            {
                SmithsCore.getLogger().info("WRITE -> Dispatcher is null.");
            }

            return;
        }

        final NBTTagCompound dispatcherData = dispatcher.serializeNBT();
        if (SmithsCore.isInDevEnvironment())
        {
            SmithsCore.getLogger().info("WRITE -> Dispatcher data: " + dispatcherData);
        }

        if (dispatcherData != null)
        {
            compound.setTag(Constants.CONST_ITEMSTACK_SHARE_TAG_IN_COMPOUND_KEY, dispatcherData);
        }

        if (SmithsCore.isInDevEnvironment())
        {
            SmithsCore.getLogger().info("WRITE -> POST: " + compound);
        }

        callbackInfoReturnable.setReturnValue(compound);
    }
}
