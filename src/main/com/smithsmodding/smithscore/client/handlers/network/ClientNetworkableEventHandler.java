/*
 * Copyright (c) 2015.
 *
 * Copyrighted by SmithsModding according to the project License
 */

package com.smithsmodding.smithscore.client.handlers.network;

import com.smithsmodding.smithscore.common.events.network.NetworkableEvent;
import com.smithsmodding.smithscore.common.handlers.network.CommonNetworkableEventHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;

public class ClientNetworkableEventHandler extends CommonNetworkableEventHandler
{

    @Override
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onEvent(@Nonnull NetworkableEvent pEvent)
    {
        if (pEvent.getCommunicationMessage(Side.SERVER) == null)
        {
            return;
        }

        if (pEvent.isCanceled())
        {
            return;
        }

        pEvent.handleClientToServerSide();
    }
}
