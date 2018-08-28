package com.ldtteam.smithscore.client.gui.tabs.core;

import com.ldtteam.smithscore.client.gui.hosts.IGUIBasedComponentHost;
import com.ldtteam.smithscore.client.gui.hosts.IGUIBasedTabHost;
import com.ldtteam.smithscore.client.gui.management.ITabManager;
import com.ldtteam.smithscore.util.client.color.MinecraftColor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

/**
 * Created by marcf on 1/15/2016.
 */
public interface IGUITab extends IGUIBasedComponentHost
{

    /**
     * Method to get the host of this Tab
     *
     * @return The current tabs host.
     */
    @Nonnull
    IGUIBasedTabHost getTabHost();

    /**
     * Method to set the host of the tab.
     */
    void setTabHost(@Nonnull IGUIBasedTabHost host);

    /**
     * Method to get this tabs TabManger.
     *
     * @return The TabManager of this Tab.
     */
    @Nonnull
    ITabManager getTabManager();

    /**
     * Method to get the display stack.
     *
     * @return THe displayed stack.
     */
    @Nonnull
    ItemStack getDisplayStack();

    /**
     * Method to get the tabs color.
     *
     * @return The tabs color.
     */
    @Nonnull
    MinecraftColor getTabColor();

    /**
     * Function to get the tooltiptext of the gui System.
     *
     * @return The tooltip contents
     */
    @Nullable
    ArrayList<String> getIconToolTipText();
}
