package com.smithsmodding.smithscore.client.handlers.gui;

import com.smithsmodding.smithscore.client.events.gui.GuiInputEvent;
import com.smithsmodding.smithscore.client.gui.GuiContainerSmithsCore;
import com.smithsmodding.smithscore.client.gui.components.implementations.ComponentButton;
import com.smithsmodding.smithscore.client.gui.components.implementations.ComponentScrollBar;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nonnull;

/**
 * Created by Marc on 10.02.2016.
 */
public class ButtonInputEventHandler
{

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onGuiInputEvent(@Nonnull GuiInputEvent event)
    {
        if (!(event.getTypes() == GuiInputEvent.InputTypes.BUTTONCLICKED))
            return;

        GuiContainerSmithsCore guiContainerSmithsCore = (GuiContainerSmithsCore) Minecraft.getMinecraft().currentScreen;
        ComponentButton button = (ComponentButton) guiContainerSmithsCore.getComponentByID(event.getComponentID());

        if (!(button.getComponentHost() instanceof ComponentScrollBar))
            return;

        if (!button.getID().endsWith(".Buttons.Up") && !button.getID().endsWith(".Buttons.ScrollDrag") && !button.getID().endsWith(".Buttons.Down"))
            return;

        ((ComponentScrollBar) button.getComponentHost()).onInternalButtonClick(button);

        event.setCanceled(true);
    }
}
