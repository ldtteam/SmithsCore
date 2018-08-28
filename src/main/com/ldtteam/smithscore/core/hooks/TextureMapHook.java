package com.ldtteam.smithscore.core.hooks;

import com.ldtteam.smithscore.client.events.texture.TextureStitchCollectedEvent;
import com.ldtteam.smithscore.core.interfaces.ITextureMap;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

/**
 * Hook Class for the TextureMap.
 */
@Mixin(TextureMap.class)
public abstract class TextureMapHook extends AbstractTexture implements ITextureMap
{

    @Inject(method = "loadTextureAtlas", at = @At("HEAD"))
    public void onLoadTextureAtlas(CallbackInfo cbi)
    {
        new TextureStitchCollectedEvent(this).PostClient();
    }

    @Shadow
    public abstract TextureAtlasSprite registerSprite(final ResourceLocation location);

    @Shadow
    @Nullable
    public abstract TextureAtlasSprite getTextureExtry(final String name);

    @Shadow
    public abstract boolean setTextureEntry(final TextureAtlasSprite entry);

    @Override
    public TextureAtlasSprite getTextureViaName(final String name)
    {
        return getTextureExtry(name);
    }

    @Override
    public TextureAtlasSprite addNewTextureFromResourceLocation(final ResourceLocation location)
    {
        return registerSprite(location);
    }

    @Override
    public boolean setEntry(final TextureAtlasSprite entry)
    {
        return setTextureEntry(entry);
    }
}
