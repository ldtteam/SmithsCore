package com.ldtteam.smithscore.client.gui.tabs.implementations;

import com.ldtteam.smithscore.client.gui.hosts.IGUIBasedComponentHost;
import com.ldtteam.smithscore.client.gui.hosts.IGUIBasedTabHost;
import com.ldtteam.smithscore.client.gui.state.IGUIComponentState;
import com.ldtteam.smithscore.util.client.color.MinecraftColor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * Created by Marc on 17.01.2016.
 */
public class DummyTab extends CoreTab
{

    public DummyTab(
      @Nonnull String uniqueID,
      @Nonnull IGUIBasedTabHost root,
      @Nonnull IGUIComponentState state,
      @Nonnull ItemStack displayStack,
      @Nonnull MinecraftColor tabColor,
      @Nonnull String toolTipString)
    {
        super(uniqueID, root, state, displayStack, tabColor, toolTipString);
    }

    /**
     * Function used to register the sub components of this ComponentHost
     *
     * @param host This ComponentHosts host. For the Root GUIObject a reference to itself will be passed in..
     */
    @Override
    public void registerComponents(@Nonnull IGUIBasedComponentHost host)
    {
        getRootGuiObject().registerComponents(this);
    }
}
