package com.ldtteam.smithscore.core.hooks;

import com.ldtteam.smithscore.SmithsCore;
import com.ldtteam.smithscore.common.capability.SmithsCoreCapabilityDispatcher;
import com.ldtteam.smithscore.core.util.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(ItemStack.class)
public abstract class ItemStackHook
{

    @Inject(method = "setTagCompound", at = @At("HEAD"))
    public void onSetTagCompound(@Nullable final NBTTagCompound nbt, final CallbackInfo info)
    {
        if (nbt == null)
        {
            return;
        }

        if (SmithsCore.isInDevEnvironment())
        {
            SmithsCore.getLogger().info("READ -> PRE: " + nbt.toString());
        }

        if (!nbt.hasKey(Constants.CONST_ITEMSTACK_SHARE_TAG_IN_COMPOUND_KEY))
        {
            if (SmithsCore.isInDevEnvironment())
            {
                SmithsCore.getLogger().info("READ -> Nbt does not contain Dispatcher data");
            }

            return;
        }

        if (!this.hasCapability(SmithsCoreCapabilityDispatcher.INSTANCE_CAPABILITY, null))
        {
            if (SmithsCore.isInDevEnvironment())
            {
                SmithsCore.getLogger().info("READ -> Item does not have dispatcher as cap.");
            }

            return;
        }


        final SmithsCoreCapabilityDispatcher.IInstanceCap instanceCap = this.getCapability(SmithsCoreCapabilityDispatcher.INSTANCE_CAPABILITY, null);
        if (instanceCap == null)
        {
            if (SmithsCore.isInDevEnvironment())
            {
                SmithsCore.getLogger().info("READ -> Instance holder cap is null.");
            }

            return;
        }

        final SmithsCoreCapabilityDispatcher dispatcher = instanceCap.getDispatcher();
        if (dispatcher == null)
        {
            if (SmithsCore.isInDevEnvironment())
            {
                SmithsCore.getLogger().info("READ -> Dispatcher is null.");
            }

            return;
        }

        dispatcher.deserializeNBT(nbt.getCompoundTag(Constants.CONST_ITEMSTACK_SHARE_TAG_IN_COMPOUND_KEY));
        nbt.removeTag(Constants.CONST_ITEMSTACK_SHARE_TAG_IN_COMPOUND_KEY);

        if (SmithsCore.isInDevEnvironment())
        {
            SmithsCore.getLogger().info("READ -> POST: " + nbt);
        }
    }

    @Shadow(remap = false)
    public abstract boolean hasCapability(final Capability<?> capability, @Nullable final EnumFacing facing);

    @Shadow(remap = false)
    @Nullable
    public abstract <T> T getCapability(final Capability<T> capability, @Nullable final EnumFacing facing);
}
