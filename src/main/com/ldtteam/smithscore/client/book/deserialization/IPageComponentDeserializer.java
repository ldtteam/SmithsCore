package com.ldtteam.smithscore.client.book.deserialization;

import com.google.gson.JsonElement;
import com.ldtteam.smithscore.client.book.deserialization.context.PageDeserializationContext;
import com.ldtteam.smithscore.client.gui.components.core.IGUIComponent;

import javax.annotation.Nonnull;

/**
 * Created by marcf on 7/31/2016.
 */
public interface IPageComponentDeserializer<C extends IGUIComponent>
{
    @Nonnull
    C deserialize(@Nonnull JsonElement element, @Nonnull PageDeserializationContext context);
}
