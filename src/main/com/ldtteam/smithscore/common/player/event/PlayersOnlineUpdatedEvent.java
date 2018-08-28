/*
 * Copyright (c) 2015.
 *
 * Copyrighted by SmithsModding according to the project License
 */

package com.ldtteam.smithscore.common.player.event;

import com.ldtteam.smithscore.common.events.network.StandardNetworkableEvent;
import com.ldtteam.smithscore.common.player.management.PlayerManager;
import com.ldtteam.smithscore.util.common.helper.NetworkHelper;
import io.netty.buffer.ByteBuf;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayersOnlineUpdatedEvent extends StandardNetworkableEvent
{

    private List<UUID> commonSidedOnlineMap = new ArrayList<UUID>();

    public PlayersOnlineUpdatedEvent() {}

    public PlayersOnlineUpdatedEvent(@Nonnull PlayerManager manager)
    {
        commonSidedOnlineMap = manager.getCommonSidedOnlineMap();
    }

    /**
     * Getter for the list of online Players
     *
     * @return The list of online Players
     */
    @Nonnull
    public List<UUID> getCommonSidedOnlineMap()
    {
        return commonSidedOnlineMap;
    }

    /**
     * Function used by the instance created on the receiving side to reset its state from the sending side stored
     * by it in the Buffer before it is being fired on the NetworkRelayBus.
     *
     * @param pMessageBuffer The ByteBuffer from the IMessage used to Synchronize the implementing events.
     */
    @Override
    public void readFromMessageBuffer(@Nonnull ByteBuf pMessageBuffer)
    {
        int pairCount = pMessageBuffer.readInt();

        for (int pairIndex = 0; pairIndex < pairCount; pairIndex++)
        {
            commonSidedOnlineMap.add(NetworkHelper.readUUID(pMessageBuffer));
        }
    }

    /**
     * Function used by the instance on the sending side to write its state top the Buffer before it is send to the
     * retrieving side.
     *
     * @param pMessageBuffer The buffer from the IMessage
     */
    @Override
    public void writeToMessageBuffer(@Nonnull ByteBuf pMessageBuffer)
    {
        pMessageBuffer.writeInt(commonSidedOnlineMap.size());

        for (UUID id : commonSidedOnlineMap)
        {
            NetworkHelper.writeUUID(pMessageBuffer, id);
        }
    }
}
