package com.smithsmodding.smithscore.common.events;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EntityPickupTargetSlotEvent extends SmithsCoreEvent
{
    private final ItemStack           stack;
    private       EntityEquipmentSlot slot;

    public EntityPickupTargetSlotEvent(final ItemStack stack, final EntityEquipmentSlot slot)
    {
        this.stack = stack;
        this.slot = slot;
    }

    public ItemStack getStack()
    {
        return stack;
    }

    public EntityEquipmentSlot getSlot()
    {
        return slot;
    }

    public void setSlot(final EntityEquipmentSlot slot)
    {
        this.slot = slot;
    }
}
