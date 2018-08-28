package com.ldtteam.smithscore.client.gui.hosts;

import com.ldtteam.smithscore.client.gui.components.implementations.ComponentContentArea;

import javax.annotation.Nonnull;

/**
 * Created by Marc on 11.02.2016.
 */
public interface IContentAreaHost extends IGUIBasedComponentHost
{
    void registerContentComponents(@Nonnull ComponentContentArea host);
}
