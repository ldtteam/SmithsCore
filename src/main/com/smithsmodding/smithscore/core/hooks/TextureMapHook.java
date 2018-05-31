package com.smithsmodding.smithscore.core.hooks;

import com.smithsmodding.smithscore.client.events.texture.TextureStitchCollectedEvent;
import com.smithsmodding.smithscore.core.interfaces.ITextureMap;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.renderer.texture.TextureMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Hook Class for the TextureMap.
 */
@Mixin(TextureMap.class)
public abstract class TextureMapHook extends AbstractTexture implements ITickableTextureObject, ITextureMap
{

    @Inject(method = "loadTextureAtlas", at = @At("HEAD"))
    public void onLoadTextureAtlas(CallbackInfo cbi)
    {
        new TextureStitchCollectedEvent(this).PostClient();
    }
}
