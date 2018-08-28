package com.ldtteam.smithscore.client.events.texture;

import com.ldtteam.smithscore.common.events.SmithsCoreEvent;
import com.ldtteam.smithscore.core.interfaces.ITextureMap;

/**
 * Event fired when the textures for the stitching have been collected yet not processed.
 */
public class TextureStitchCollectedEvent extends SmithsCoreEvent
{
    private final ITextureMap map;

    public TextureStitchCollectedEvent(final ITextureMap map)
    {
        this.map = map;
    }

    public ITextureMap getMap()
    {
        return map;
    }
}
