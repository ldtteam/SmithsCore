/*
 * Copyright (c) 2015.
 *
 * Copyrighted by SmithsModding according to the project License
 */

package com.smithsmodding.smithscore.common.events.network;

import com.smithsmodding.smithscore.common.events.*;
import com.smithsmodding.smithscore.util.common.*;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import net.minecraftforge.fml.relauncher.*;

import java.util.*;

/**
 * events used to tell Other mods that a NetworkManager is about to be Initialized.
 */
public class NetworkManagerInitializeEvent extends SmithsCoreEvent {

    private HashMap<Pair<Class, Class>, Side> iAdditionalMessage;
    private SimpleNetworkWrapper iChannel;

    /**
     * Constructor to create this event, takes the Channel as Parameter
     *
     * @param pChannel The ID of the Channel that is about to be initialized
     */
    public NetworkManagerInitializeEvent(SimpleNetworkWrapper pChannel) {
        iChannel = pChannel;
        iAdditionalMessage = new HashMap<Pair<Class, Class>, Side>();
    }

    /**
     * Function used by catching handlers to register additional messages to the channel that is about to be initialized
     * without having to have direct contact the the SimpleNetworkWrapper Instance that describes this Channel.
     * <p/>
     * Use this function instead of registering the Message directly so you don't have to worry about the Channel ID
     *
     * @param pHandlerClass  The Message handlers class on the receiving end.
     * @param pMessageClass  The Message Class.
     * @param pReceivingSide The Receiving side.
     */
    public void RegisterNewMessage(Class pHandlerClass, Class pMessageClass, Side pReceivingSide) {
        iAdditionalMessage.put(new Pair<Class, Class>(pHandlerClass, pMessageClass), pReceivingSide);
    }

    /**
     * Return a HashMap with all Additional messages for this Channel.
     *
     * @return A HashMap with all the additional messages.
     */
    public HashMap<Pair<Class, Class>, Side> getAdditionalMessages() {
        return iAdditionalMessage;
    }

    /**
     * The Channel that is being Initialized.
     *
     * @return The SimpleNetworkWrapper describing this Channel.
     */
    public SimpleNetworkWrapper getChannel() {
        return iChannel;
    }

}
