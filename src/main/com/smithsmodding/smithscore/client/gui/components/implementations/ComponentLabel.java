package com.smithsmodding.smithscore.client.gui.components.implementations;

import com.smithsmodding.smithscore.client.gui.hosts.IGUIBasedComponentHost;
import com.smithsmodding.smithscore.client.gui.state.IGUIComponentState;
import com.smithsmodding.smithscore.util.client.color.MinecraftColor;
import com.smithsmodding.smithscore.util.common.positioning.Coordinate2D;
import com.smithsmodding.smithscore.util.common.positioning.Plane;
import net.minecraft.client.gui.FontRenderer;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by marcf on 12/28/2015.
 */
public class ComponentLabel extends CoreComponent
{
    protected String         displayedText;
    protected MinecraftColor color;
    private   FontRenderer   renderer;

    private final boolean autoWrap;

    public ComponentLabel(
                           @Nonnull String uniqueID,
                           @Nonnull IGUIBasedComponentHost root,
                           @Nonnull IGUIComponentState state,
                           @Nonnull Coordinate2D localCoordinate,
                           @Nonnull MinecraftColor color,
                           @Nonnull FontRenderer renderer,
                           @Nonnull String displayedText)
    {
        super(uniqueID, root, state, localCoordinate, renderer.getStringWidth(displayedText), renderer.FONT_HEIGHT);

        this.displayedText = displayedText;
        this.color = color;
        this.renderer = renderer;
        this.autoWrap = false;
    }

    public ComponentLabel(
      @Nonnull String uniqueID,
      @Nonnull IGUIBasedComponentHost root,
      @Nonnull IGUIComponentState state,
      @Nonnull Coordinate2D localCoordinate,
      @Nonnull MinecraftColor color,
      @Nonnull FontRenderer renderer,
      @Nonnull String displayedText,
      final int maxWidth)
    {
        super(uniqueID, root, state, localCoordinate, maxWidth, renderer.FONT_HEIGHT);
        this.displayedText = displayedText;
        this.color = color;
        this.renderer = renderer;
        this.autoWrap = true;
    }

    @Nonnull
    @Override
    public Plane getSize()
    {
        if (!autoWrap)
            return super.getSize();

        final List<String> split = renderer.listFormattedStringToWidth(displayedText, super.getSize().getWidth());
        final int height = split.size() * (renderer.FONT_HEIGHT);

        return new Plane(0,0, super.getSize().getWidth(), height);
    }

    /**
     * Method gets called before the component gets rendered, allows for animations to calculate through.
     *
     * @param mouseX          The X-Coordinate of the mouse.
     * @param mouseY          The Y-Coordinate of the mouse.
     * @param partialTickTime The partial tick time, used to calculate fluent animations.
     */
    @Override
    public void update(int mouseX, int mouseY, float partialTickTime)
    {
        //NOOP
    }

    /**
     * Function used to draw this components background.
     * Usually this will incorporate all of the components visual objects.
     * A good example of a Component that only uses the drawBackground function is the
     * BackgroundComponent.
     *
     * @param mouseX The current X-Coordinate of the mouse
     * @param mouseY The current Y-Coordinate of the mouse
     */
    @Override
    public void drawBackground(int mouseX, int mouseY)
    {
        if (!autoWrap)
            renderer.drawString(displayedText, 0,0, color.getRGB());
        else
            renderer.drawSplitString(displayedText, 0, 0, super.getSize().getWidth(), color.getRGB());
    }

    /**
     * Function used to draw this components foreground.
     * Usually this will incorporate very few of teh components visual Objects.
     * A good example of a Component that only uses the drawForeground function is the
     * GUIDescriptionLabel (The labels that describe thins like your inventory and the TE's inventory).
     *
     * @param mouseX The current X-Coordinate of the mouse
     * @param mouseY The current Y-Coordinate of the mouse
     */
    @Override
    public void drawForeground(int mouseX, int mouseY)
    {
        //NOOP
    }
}
