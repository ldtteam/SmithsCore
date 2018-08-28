package com.ldtteam.smithscore.common.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.ldtteam.smithscore.common.events.AutomaticEventBusSubscriber.BusType.*;

/**
 * Created by marcf on 1/20/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AutomaticEventBusSubscriber
{

    BusType[] types() default {CLIENT, SERVER, NETWORK};

    /**
     * Optional value, only nessasary if tis annotation is not on the same class that has a @Mod annotation.
     * Needed to prevent early classloading of classes not owned by your mod.
     */
    String modid() default "";

    enum BusType
    {
        CLIENT,
        SERVER,
        NETWORK
    }
}
