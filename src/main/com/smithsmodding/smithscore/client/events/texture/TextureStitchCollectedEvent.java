package com.smithsmodding.smithscore.client.events.texture;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.client.event.TextureStitchEvent;

/**
 * Event fired when the textures for the stitching have been collected yet not processed.
 */
public class TextureStitchCollectedEvent extends TextureStitchEvent
{
    public TextureStitchCollectedEvent(final TextureMap map)
    {
        super(map);
    }
}
