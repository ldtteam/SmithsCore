package com.smithsmodding.smithscore.core.interfaces;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;

/**
 * Interface describing a texture map.
 */
public interface ITextureMap
{
    TextureAtlasSprite getTextureExtry(String name);

    TextureAtlasSprite registerSprite(ResourceLocation location);

    boolean setTextureEntry(TextureAtlasSprite entry);
}
