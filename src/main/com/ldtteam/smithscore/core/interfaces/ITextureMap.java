package com.ldtteam.smithscore.core.interfaces;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;

/**
 * Interface describing a texture map.
 */
public interface ITextureMap
{
    TextureAtlasSprite getTextureViaName(String name);

    TextureAtlasSprite addNewTextureFromResourceLocation(ResourceLocation location);

    boolean setEntry(TextureAtlasSprite entry);
}
