/*
 * Copyright (c) 2015.
 *
 * Copyrighted by SmithsModding according to the project License
 */

package com.smithsmodding.smithscore.client.handlers.gui;

import com.smithsmodding.smithscore.client.events.gui.ContainerGuiOpenedEvent;
import com.smithsmodding.smithscore.common.inventory.ContainerSmithsCore;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ContainerGUIOpenedEventHandler
{

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onPlayerOpenenedContainerGUIClientSide(@Nonnull ContainerGuiOpenedEvent event)
    {
        if (!(FMLClientHandler.instance().getClientPlayerEntity().openContainer instanceof ContainerSmithsCore))
        {
            return;
        }

        if (!event.getContainerID().equals(((ContainerSmithsCore) FMLClientHandler.instance().getClientPlayerEntity().openContainer).getContainerID()))
        {
            return;
        }

        ContainerSmithsCore container = ((ContainerSmithsCore) FMLClientHandler.instance().getClientPlayerEntity().openContainer);
        container.getManager().onGuiOpened(event.getPlayerID());
    }
}
