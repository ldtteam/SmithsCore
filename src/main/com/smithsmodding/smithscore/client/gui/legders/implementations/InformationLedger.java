package com.smithsmodding.smithscore.client.gui.legders.implementations;

import com.smithsmodding.smithscore.client.gui.components.implementations.ComponentContentArea;
import com.smithsmodding.smithscore.client.gui.components.implementations.ComponentLabel;
import com.smithsmodding.smithscore.client.gui.components.implementations.ComponentScrollableArea;
import com.smithsmodding.smithscore.client.gui.hosts.IGUIBasedComponentHost;
import com.smithsmodding.smithscore.client.gui.hosts.IGUIBasedLedgerHost;
import com.smithsmodding.smithscore.client.gui.legders.core.LedgerConnectionSide;
import com.smithsmodding.smithscore.client.gui.state.CoreComponentState;
import com.smithsmodding.smithscore.client.gui.state.IGUIComponentState;
import com.smithsmodding.smithscore.client.gui.state.LedgerComponentState;
import com.smithsmodding.smithscore.util.client.StringUtils;
import com.smithsmodding.smithscore.util.client.Textures;
import com.smithsmodding.smithscore.util.client.color.MinecraftColor;
import com.smithsmodding.smithscore.util.common.positioning.Coordinate2D;
import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Marc on 12.01.2016.
 */
public class InformationLedger extends CoreLedger
{

    @Nonnull
    ArrayList<String> translatedDisplayedStrings = new ArrayList<String>();

    public InformationLedger(
                              @Nonnull String uniqueID,
                              @Nonnull IGUIBasedLedgerHost root,
                              LedgerConnectionSide side,
                              @Nonnull String translatedGuiOwner,
                              @Nonnull MinecraftColor color,
                              @Nonnull ArrayList<String> translatedDisplayedStrings)
    {
        super(uniqueID, new LedgerComponentState(), root, side, Textures.Gui.Basic.INFOICON, translatedGuiOwner, color);

        for (String line : translatedDisplayedStrings)
        {
            Collections.addAll(this.translatedDisplayedStrings,
              StringUtils.SplitString(line, closedLedgerWidth + Minecraft.getMinecraft().fontRenderer.getStringWidth(translatedGuiOwner) - 15));

            if (translatedDisplayedStrings.indexOf(line) != translatedDisplayedStrings.size() - 1)
            {
                this.translatedDisplayedStrings.add("");
            }
        }
    }

    /**
     * Function used to register the sub components of this ComponentHost
     *
     * @param host This ComponentHosts host. For the Root GUIObject a reference to itself will be passed in..
     */
    @Override
    public void registerComponents(@Nonnull IGUIBasedComponentHost host)
    {
        super.registerComponents(host);

        registerNewComponent(new Contents(getID() + ".Contents",
                                           this,
                                           new CoreComponentState(),
                                           new Coordinate2D(8, closedLedgerHeight),
          closedLedgerWidth + Minecraft.getMinecraft().fontRenderer.getStringWidth(this.translatedLedgerHeader) - 8,
                                           87));
    }

    /**
     * Method used by the rendering and animation system to determine the max size of the Ledger.
     *
     * @return An int bigger then 16 plus the icon componentWidth that describes the maximum componentWidth of the Ledger when expanded.
     */
    @Override
    public int getMaxWidth()
    {
        return closedLedgerWidth + Minecraft.getMinecraft().fontRenderer.getStringWidth(translatedLedgerHeader) + 8;
    }

    /**
     * Method used by the rendering and animation system to determine the max size of the Ledger.
     *
     * @return An int bigger then 16 plus the icon componentWidth that describes the maximum componentHeight of the Ledger when expanded.
     */
    @Override
    public int getMaxHeight()
    {
        return 121;
    }

    public class Contents extends ComponentScrollableArea
    {
        public Contents(
                         @Nonnull String uniqueID,
                         @Nonnull IGUIBasedComponentHost parent,
                         @Nonnull IGUIComponentState state,
                         @Nonnull Coordinate2D rootAnchorPixel,
                         int width,
                         int height)
        {
            super(uniqueID, parent, state, rootAnchorPixel, width, height);
        }

        @Override
        public void registerContentComponents(@Nonnull ComponentContentArea host)
        {
            for (int i = 0; i < translatedDisplayedStrings.size(); i++)
            {
                String line = translatedDisplayedStrings.get(i);

                host.registerNewComponent(new ComponentLabel(getID() + ".line." + i,
                                                              host,
                                                              new CoreComponentState(null),
                  new Coordinate2D(0, i * (Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 3)),
                                                              new MinecraftColor(MinecraftColor.WHITE),
                  Minecraft.getMinecraft().fontRenderer,
                                                              line));
            }
        }
    }
}
