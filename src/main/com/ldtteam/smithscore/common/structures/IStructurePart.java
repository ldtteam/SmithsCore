package com.ldtteam.smithscore.common.structures;

import com.ldtteam.smithscore.common.pathfinding.IPathComponent;
import com.ldtteam.smithscore.util.common.positioning.Coordinate3D;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Author Orion (Created on: 25.06.2016)
 */
public interface IStructurePart<S extends IStructure> extends IPathComponent
{

    @Nonnull
    Class<S> getStructureType();

    @Nullable
    S getStructure();

    void setStructure(@Nullable S structure);

    @Nonnull
    World getEnvironment();

    @Nonnull
    Coordinate3D getLocation();
}
