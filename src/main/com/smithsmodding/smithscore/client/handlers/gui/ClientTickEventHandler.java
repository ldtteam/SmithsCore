package com.smithsmodding.smithscore.client.handlers.gui;

import com.smithsmodding.smithscore.SmithsCore;
import com.smithsmodding.smithscore.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.annotation.Nonnull;

/**
 * Created by marcf on 12/28/2015.
 */
public class ClientTickEventHandler
{

    /**
     * Method event handler for the render tick event. Is only fired on the client side, so this code can safely cast the
     * current registry to a ClientRegistry.
     *
     * @param event The RenderTickEvent fired by MinecraftForge.
     */
    @SubscribeEvent
    public void handleEvent(@Nonnull TickEvent.RenderTickEvent event)
    {
        ((ClientRegistry) SmithsCore.getRegistry()).setPartialTickTime(event.renderTickTime);
    }
}
