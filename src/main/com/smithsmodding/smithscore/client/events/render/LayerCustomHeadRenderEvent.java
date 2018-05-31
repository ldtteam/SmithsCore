package com.smithsmodding.smithscore.client.events.render;

import com.smithsmodding.smithscore.common.events.SmithsCoreEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

/**
 * Event fired before the LayerCustomHead is rendered.
 */
@Cancelable
public class LayerCustomHeadRenderEvent extends SmithsCoreEvent
{

    private final EntityLivingBase entityLivingBase;

    public LayerCustomHeadRenderEvent(final EntityLivingBase entityLivingBase) {this.entityLivingBase = entityLivingBase;}

    /**
     * The entity for which the custom head is rendered.
     *
     * @return The entity for this the custom head is rendered.
     */
    public EntityLivingBase getEntityLivingBase()
    {
        return entityLivingBase;
    }
}
