/*
 * Copyright (c) 2015.
 *
 * Copyrighted by SmithsModding according to the project License
 */

package com.smithsmodding.smithscore.common.pathfinding;

import com.smithsmodding.smithscore.util.common.positioning.Coordinate3D;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public interface IPathComponent
{
    @Nonnull
    Coordinate3D getLocation();

    @Nonnull
    ArrayList<IPathComponent> getValidPathableNeighborComponents();
}
