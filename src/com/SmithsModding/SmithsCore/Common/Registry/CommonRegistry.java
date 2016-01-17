/*
 * Copyright (c) 2015.
 *
 * Copyrighted by SmithsModding according to the project License
 */

package com.smithsmodding.smithscore.common.registry;

import com.smithsmodding.smithscore.common.handlers.network.*;
import com.smithsmodding.smithscore.network.event.*;
import net.minecraftforge.fml.common.eventhandler.*;

/**
 * Central point of Data for the smithscore mod.
 * Also manages the Eventbusses for the different purposes.
 */
public class CommonRegistry {

    //Eventbus used on the server side to handle events that are fired on the client side and then send through the network to be handled on the server side as well.
    //This is for example used when a User clicks a button.
    private final EventBus iNetworkRelayBus = new EventBus();

    //Eventbus used for events that are fired commonly on both sides of Minecraft. It is for example used the handle Container and TE Initialization or
    //to handle access right management for blocks that implement it.
    private final EventBus iCommonEventBus = new EventBus();

    /**
     * Function used to register the EventHandlers
     */
    public void registerEventHandlers()
    {
        getCommonBus().register(new CommonNetworkableEventHandler());
    }

    /**
     * Function used to initialize the network components of smithscore.
     */
    public void initializeNetwork()
    {
        EventNetworkManager.Init();
    }


    /**
     * Function used to handle specific events that happened on the client side, and were synced over because they could have an impact on the server side.
     * A good example of this is a Button that is being clicked on the Server side which changes the Mode of a TE on the Server Side.
     *
     * @return The EventBus that is used for network Relaying.
     */
    public EventBus getNetworkBus() {
        return iNetworkRelayBus;
    }

    /**
     * The EventBus used for events that can be fired at both Sides.
     *
     * @return The common EventBus.
     */
    public EventBus getCommonBus() {
        return iCommonEventBus;
    }

    /**
     * The event bus used for client events. Under common code this is the standard common event bus.
     *
     * @return The common EventBus
     */
    public EventBus getClientBus() {
        return iCommonEventBus;
    }
}
