package com.smithsmodding.smithscore.client.render.layers;

import com.smithsmodding.smithscore.client.events.render.LayerCustomHeadRenderEvent;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.entity.EntityLivingBase;

/**
 * Layer replaces the LayerCustomHead to make it cancalable.
 */
public class CancelableLayerCustomHead extends LayerCustomHead
{
    public CancelableLayerCustomHead(final ModelRenderer modelRenderer)
    {
        super(modelRenderer);
    }

    @Override
    public void doRenderLayer(
                               final EntityLivingBase entitylivingbaseIn,
                               final float limbSwing,
                               final float limbSwingAmount,
                               final float partialTicks,
                               final float ageInTicks,
                               final float netHeadYaw,
                               final float headPitch,
                               final float scale)
    {
        if ((new LayerCustomHeadRenderEvent(entitylivingbaseIn).PostClient()))
        {
            return;
        }

        super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
    }
}
