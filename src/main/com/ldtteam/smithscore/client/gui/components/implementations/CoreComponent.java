package com.ldtteam.smithscore.client.gui.components.implementations;

import com.ldtteam.smithscore.client.gui.components.core.IGUIComponent;
import com.ldtteam.smithscore.client.gui.hosts.IGUIBasedComponentHost;
import com.ldtteam.smithscore.client.gui.state.IGUIComponentState;
import com.ldtteam.smithscore.util.common.positioning.Coordinate2D;
import com.ldtteam.smithscore.util.common.positioning.Plane;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

/**
 * Created by marcf on 1/16/2016.
 */
public abstract class CoreComponent implements IGUIComponent
{
    protected           String                 uniqueID;
    protected           IGUIComponentState     state;
    protected transient IGUIBasedComponentHost parent;
    protected           Coordinate2D           rootAnchorPixel;
    protected           int                    width;
    protected           int                    height;

    public CoreComponent(
      @Nonnull String uniqueID,
      @Nonnull IGUIBasedComponentHost parent,
      @Nonnull IGUIComponentState state,
      @Nonnull Coordinate2D rootAnchorPixel,
      @Nonnull int width,
      @Nonnull int height)
    {
        this.rootAnchorPixel = rootAnchorPixel;
        this.height = height;
        this.uniqueID = uniqueID;
        this.state = state;
        this.width = width;
        this.parent = parent;

        this.state.setComponent(this);
    }

    @Nonnull
    @Override
    public String getID()
    {
        return uniqueID;
    }

    @Nonnull
    @Override
    public IGUIComponentState getState()
    {
        return state;
    }

    @Nonnull
    @Override
    public IGUIBasedComponentHost getComponentHost()
    {
        return parent;
    }

    @Override
    public void setComponentHost(@Nonnull final IGUIBasedComponentHost host)
    {
        this.parent = host;
    }

    @Nonnull
    @Override
    public Coordinate2D getGlobalCoordinate()
    {
        return parent.getGlobalCoordinate().getTranslatedCoordinate(getLocalCoordinate());
    }

    @Nonnull
    @Override
    public Coordinate2D getLocalCoordinate()
    {
        return rootAnchorPixel;
    }

    @Override
    public void setLocalCoordinate(@Nonnull final Coordinate2D coordinate)
    {
        this.rootAnchorPixel = coordinate;
    }

    @Nonnull
    @Override
    public Plane getAreaOccupiedByComponent()
    {
        return new Plane(getGlobalCoordinate(), width, height);
    }

    @Nonnull
    @Override
    public Plane getSize()
    {
        return new Plane(0, 0, width, height);
    }

    @Override
    public abstract void update(@Nonnull int mouseX, @Nonnull int mouseY, @Nonnull float partialTickTime);

    @Override
    public abstract void drawBackground(@Nonnull int mouseX, @Nonnull int mouseY);

    @Override
    public abstract void drawForeground(@Nonnull int mouseX, @Nonnull int mouseY);

    @Override
    @Nonnull
    public boolean handleMouseClickedInside(@Nonnull int relativeMouseX, @Nonnull int relativeMouseY, @Nonnull int mouseButton)
    {
        return false;
    }

    @Override
    @Nonnull
    public boolean handleMouseClickedOutside(@Nonnull int relativeMouseX, @Nonnull int relativeMouseY, @Nonnull int mouseButton)
    {
        return false;
    }

    @Override
    @Nonnull
    public boolean requiresForcedMouseInput()
    {
        return false;
    }

    @Override
    @Nonnull
    public boolean handleKeyTyped(@Nonnull char key, @Nonnull int keyCode)
    {
        return false;
    }

    @Nonnull
    @Override
    public boolean handleMouseWheel(final int relativeMouseX, @Nonnull final int relativeMouseY, @Nonnull final int deltaWheel)
    {
        return false;
    }

    @Nullable
    @Override
    public ArrayList<String> getToolTipContent()
    {
        return new ArrayList<String>();
    }
}
