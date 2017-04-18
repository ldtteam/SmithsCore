package com.smithsmodding.smithscore.client.gui.hosts;

import com.smithsmodding.smithscore.client.gui.components.implementations.ComponentContentArea;

import javax.annotation.Nonnull;

/**
 * Created by Marc on 11.02.2016.
 */
public interface IContentAreaHost extends IGUIBasedComponentHost
{
    void registerContentComponents(@Nonnull ComponentContentArea host);
}
