/*
 * Copyright (c) 2015.
 *
 * Copyrighted by SmithsModding according to the project License
 */

package com.ldtteam.smithscore.common.handlers.network;

import com.ldtteam.smithscore.common.events.network.NetworkableEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.Nonnull;

/**
 * Eventhandler used to catch Networkable events on the Server Side.
 */
public class CommonNetworkableEventHandler
{

    /**
     * events handler for events on one Side that gets fired by the CommonBus to send a Message to All clients or from one client to the Server.
     *
     * @param pEvent The event that should be Synchronized
     */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onEvent(@Nonnull NetworkableEvent pEvent)
    {
        if (pEvent.getCommunicationMessage(Side.CLIENT) == null)
        {
            return;
        }

        pEvent.handleServerToClientSide();
    }
}
