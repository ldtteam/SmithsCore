package com.ldtteam.smithscore.client.book.deserialization.implementations;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ldtteam.smithscore.client.book.deserialization.BookDeserializationUtil;
import com.ldtteam.smithscore.client.book.deserialization.IPageComponentDeserializer;
import com.ldtteam.smithscore.client.book.deserialization.context.PageDeserializationContext;
import com.ldtteam.smithscore.client.gui.components.implementations.ComponentBorder;
import com.ldtteam.smithscore.util.CoreReferences;
import com.ldtteam.smithscore.util.client.color.MinecraftColor;
import com.ldtteam.smithscore.util.common.positioning.Coordinate2D;

import javax.annotation.Nonnull;

/**
 * Created by marcf on 8/3/2016.
 */
public class BorderDeserializer implements IPageComponentDeserializer<ComponentBorder>
{

    @Nonnull
    @Override
    public ComponentBorder deserialize(@Nonnull JsonElement element, @Nonnull PageDeserializationContext context)
    {
        JsonObject borderObject = element.getAsJsonObject();

        MinecraftColor backGroundColor = (MinecraftColor) context.getContextData(CoreReferences.BookData.General.BACKGROUND);

        ComponentBorder.CornerTypes generalCorner = getCornerTypeFromString(BookDeserializationUtil.getElementAsString(borderObject, CoreReferences.BookData.Border.CORNERS));
        ComponentBorder.CornerTypes topLeft =
          (BookDeserializationUtil.hasLocation(borderObject, CoreReferences.BookData.Border.CORNERTOPLEFT) ? getCornerTypeFromString(BookDeserializationUtil.getElementAsString(
            borderObject,
            CoreReferences.BookData.Border.CORNERTOPLEFT)) : generalCorner);
        ComponentBorder.CornerTypes topRight =
          (BookDeserializationUtil.hasLocation(borderObject, CoreReferences.BookData.Border.CORNERTOPRIGHT) ? getCornerTypeFromString(BookDeserializationUtil.getElementAsString(
            borderObject,
            CoreReferences.BookData.Border.CORNERTOPRIGHT)) : generalCorner);
        ComponentBorder.CornerTypes bottomLeft =
          (BookDeserializationUtil.hasLocation(borderObject, CoreReferences.BookData.Border.CORNERBOTTOMLEFT) ? getCornerTypeFromString(BookDeserializationUtil.getElementAsString(
            borderObject,
            CoreReferences.BookData.Border.CORNERBOTTOMLEFT)) : generalCorner);
        ComponentBorder.CornerTypes bottomRight =
          (BookDeserializationUtil.hasLocation(borderObject, CoreReferences.BookData.Border.CORNERBOTTOMRIGHT) ? getCornerTypeFromString(BookDeserializationUtil.getElementAsString(
            borderObject,
            CoreReferences.BookData.Border.CORNERBOTTOMRIGHT)) : generalCorner);

        Coordinate2D location = BookDeserializationUtil.getCoordinate(borderObject, CoreReferences.BookData.General.LOCATION);

        int width = BookDeserializationUtil.getElementAsInt(borderObject, CoreReferences.BookData.General.WIDTH);
        int height = BookDeserializationUtil.getElementAsInt(borderObject, CoreReferences.BookData.General.HEIGHT);

        String uniqueId = context.getPage().getId().toString() + "-" + context.getNextComponentIndex();

        return new ComponentBorder(uniqueId, context.getGui(), location, width, height, backGroundColor, topLeft, topRight, bottomRight, bottomLeft);
    }

    @Nonnull
    private ComponentBorder.CornerTypes getCornerTypeFromString(@Nonnull String cornerType)
    {
        switch (cornerType.toLowerCase())
        {
            case "inwards":
                return ComponentBorder.CornerTypes.Inwards;
            case "outwards":
                return ComponentBorder.CornerTypes.Outwards;
            case "horizontal":
                return ComponentBorder.CornerTypes.StraightHorizontal;
            case "vertical":
                return ComponentBorder.CornerTypes.StraightVertical;
            default:
                return ComponentBorder.CornerTypes.Inwards;
        }
    }
}
