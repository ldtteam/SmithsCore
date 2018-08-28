/*
 * Copyright (c) 2015.
 *
 * Copyrighted by SmithsModding according to the project License
 */

package com.ldtteam.smithscore.network.event.handlers;

import com.ldtteam.smithscore.network.event.messages.StandardNetworkableEventSyncMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class StandardNetworkableEventSyncMessageHandler implements IMessageHandler<StandardNetworkableEventSyncMessage, IMessage>
{

    /**
     * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
     * is needed.
     *
     * @param pMessage The message
     * @param pContext The messages context.
     * @return an optional return message
     */
    @Nullable
    @Override
    public IMessage onMessage(@Nonnull final StandardNetworkableEventSyncMessage pMessage, @Nonnull final MessageContext pContext)
    {
        if (pMessage.EVENT == null)
        {
            return null;
        }

        IThreadListener runnable;

        if (pContext.side == Side.CLIENT)
        {
            runnable = Minecraft.getMinecraft();
        }
        else
        {
            runnable = (IThreadListener) pContext.getServerHandler().player.world;
        }

        runnable.addScheduledTask(new Runnable()
        {
            @Override
            public void run()
            {
                pMessage.EVENT.handleCommunicationMessage(pMessage, pContext);
            }
        });

        return null;
    }
}
