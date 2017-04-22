package com.smithsmodding.smithscore.core.hooks;

import com.smithsmodding.smithscore.SmithsCore;
import com.smithsmodding.smithscore.client.events.texture.TextureStitchCollectedEvent;
import net.minecraft.client.renderer.texture.TextureMap;

/**
 * Hook Class for the TextureMap.
 */
public class TextureMapHook
{

    /**
     * Hook called by the ASM Transformer to notify SmithsCore mods of the execution of a Stitch.
     * Is fired when all textures (including the the texture for missing textures have been collected,
     * but before the actual stitch occurs).
     *
     * @param map The texture map that is about to be stitch.
     */
    public static void onTextureStichCollected(TextureMap map)
    {
        SmithsCore.getLogger().info("Collection completed. Calling additional collection hook.");
        SmithsCore.getRegistry().getClientBus().post(new TextureStitchCollectedEvent(map));
    }
}
