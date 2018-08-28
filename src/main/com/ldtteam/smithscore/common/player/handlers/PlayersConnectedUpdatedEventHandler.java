/*
 * Copyright (c) 2015.
 *
 * Copyrighted by SmithsModding according to the project License
 */

package com.ldtteam.smithscore.common.player.handlers;

import com.ldtteam.smithscore.common.player.event.PlayersConnectedUpdatedEvent;
import com.ldtteam.smithscore.common.player.management.PlayerManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

public class PlayersConnectedUpdatedEventHandler
{

    /**
     * Method for handling the network event when it arrives on the client side.
     *
     * @param event The Event indicating that the ConnectedPlayers updated.
     */
    @SubscribeEvent
    public void onPlayersConnectedUpdated(@Nonnull PlayersConnectedUpdatedEvent event)
    {
        PlayerManager.getInstance().setCommonSidedJoinedMap(event.getCommonSidedJoinedMap());
    }
}
