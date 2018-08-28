package com.ldtteam.smithscore.client.gui.components.implementations;

import com.ldtteam.smithscore.client.gui.components.core.IGUIComponent;
import com.ldtteam.smithscore.client.gui.hosts.IContentAreaHost;
import com.ldtteam.smithscore.client.gui.state.IGUIComponentState;
import com.ldtteam.smithscore.util.common.positioning.Coordinate2D;

import javax.annotation.Nonnull;

public class ComponentStack extends ComponentContentArea
{
    private Orientation orientation;
    private int         elementPadding;

    public ComponentStack(
      @Nonnull final String uniqueID,
      @Nonnull final IContentAreaHost parent,
      @Nonnull final IGUIComponentState state,
      @Nonnull final Coordinate2D rootAnchorPixel,
      final int width,
      final int height,
      final Orientation orientation,
      final int elementPadding)
    {
        super(uniqueID, parent, state, rootAnchorPixel, width, height);

        this.orientation = orientation;
        this.elementPadding = elementPadding;
    }

    public enum Orientation
    {
        VERTICAL,
        HORIZONTAL
    }

    @Override
    public void update(final int mouseX, final int mouseY, final float partialTickTime)
    {
        if (orientation == Orientation.VERTICAL)
        {
            updateVertically();
            return;
        }

        updateHorizontally();
    }

    private void updateVertically()
    {
        int height = 0;
        for (IGUIComponent component : getAllComponents().values())
        {
            final Coordinate2D newPosition = new Coordinate2D(component.getLocalCoordinate().getXComponent(), height);
            final int componentHeight = component.getSize().getHeigth();

            component.setLocalCoordinate(newPosition);

            height += (componentHeight + elementPadding);
        }
    }

    private void updateHorizontally()
    {
        int width = 0;
        for (IGUIComponent component : getAllComponents().values())
        {
            final Coordinate2D newPosition = new Coordinate2D(width, component.getLocalCoordinate().getYComponent());
            final int componentHeight = component.getSize().getWidth();

            component.setLocalCoordinate(newPosition);

            width += (componentHeight + elementPadding);
        }
    }
}
