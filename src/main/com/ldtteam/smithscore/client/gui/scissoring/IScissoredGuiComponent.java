package com.ldtteam.smithscore.client.gui.scissoring;

import com.ldtteam.smithscore.client.gui.hosts.IGUIBasedComponentHost;
import com.ldtteam.smithscore.util.common.positioning.Plane;

import javax.annotation.Nonnull;

/**
 * Created by Marc on 10.01.2016.
 */
public interface IScissoredGuiComponent extends IGUIBasedComponentHost
{

    boolean shouldScissor();

    @Nonnull
    Plane getGlobalScissorLocation();
}

