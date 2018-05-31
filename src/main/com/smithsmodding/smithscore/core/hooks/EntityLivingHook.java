package com.smithsmodding.smithscore.core.hooks;

import com.smithsmodding.smithscore.common.events.EntityPickupTargetSlotEvent;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityLiving.class)
public abstract class EntityLivingHook extends EntityLivingBase
{
    public EntityLivingHook(final World worldIn)
    {
        super(worldIn);
    }

    @Inject(method = "getSlotForItemStack", at = @At("RETURN"), cancellable = true)
    private static void onGetSlotForItemStack(ItemStack stack, CallbackInfoReturnable<EntityEquipmentSlot> cbi)
    {
        EntityPickupTargetSlotEvent event = new EntityPickupTargetSlotEvent(stack, cbi.getReturnValue());
        event.PostCommon();

        cbi.setReturnValue(event.getSlot());
    }
}
