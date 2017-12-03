package com.smithsmodding.smithscore.client.events.texture;

import com.smithsmodding.smithscore.common.events.SmithsCoreEvent;
import com.smithsmodding.smithscore.core.interfaces.ITextureMap;

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
