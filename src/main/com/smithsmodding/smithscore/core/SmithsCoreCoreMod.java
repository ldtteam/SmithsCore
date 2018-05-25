package com.smithsmodding.smithscore.core;

import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.ModMetadata;

import java.util.Collections;

public class SmithsCoreCoreMod extends DummyModContainer
{

    public static final  String ID      = "smithscore-core";
    private static final String NAME    = "SmithsCore - Core Modification";
    private static final String VERSION = "1.0";
    private static SmithsCoreCoreMod instance;

    public SmithsCoreCoreMod()
    {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = ID;
        meta.name = NAME;
        meta.version = VERSION;
        meta.credits = "TimTheBrick and Asherslab";
        meta.authorList = Collections.singletonList("SmithsModding-Team");
        meta.description = "CoreMod for SmithsCores modification of the MC Source.";
        meta.url = "";
        meta.screenshots = new String[0];
        meta.logoFile = "";

        instance = this;
    }

    @Override
    public Disableable canBeDisabled()
    {
        return Disableable.NEVER;
    }

    public static SmithsCoreCoreMod getInstance()
    {
        return instance;
    }

    @Override
    public String getVersion()
    {
        return VERSION;
    }

    @Override
    public String getName()
    {
        return NAME;
    }

    @Override
    public String getModId()
    {
        return ID;
    }
}
