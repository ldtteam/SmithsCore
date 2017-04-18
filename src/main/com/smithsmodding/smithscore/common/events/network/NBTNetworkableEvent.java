package com.smithsmodding.smithscore.common.events.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import javax.annotation.Nonnull;

/**
 * Author Orion (Created on: 25.06.2016)
 */
public abstract class NBTNetworkableEvent extends StandardNetworkableEvent {

    @Override
    public void readFromMessageBuffer(@Nonnull ByteBuf pMessageBuffer) {
        readSyncCompound(ByteBufUtils.readTag(pMessageBuffer));
    }

    protected abstract void readSyncCompound(@Nonnull NBTTagCompound compound);

    @Override
    public void writeToMessageBuffer(@Nonnull ByteBuf pMessageBuffer) {
        ByteBufUtils.writeTag(pMessageBuffer, getSyncCompound());
    }

    @Nonnull
    protected abstract NBTTagCompound getSyncCompound();
}
