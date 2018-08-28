package com.ldtteam.smithscore.client.gui.management;

import com.ldtteam.smithscore.client.gui.hosts.IGUIBasedLedgerHost;
import com.ldtteam.smithscore.client.gui.legders.core.IGUILedger;
import com.ldtteam.smithscore.client.gui.legders.core.LedgerConnectionSide;
import com.ldtteam.smithscore.util.common.positioning.Coordinate2D;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;

/**
 * Created by marcf on 12/29/2015.
 */
public interface ILedgerManager
{

    @Nonnull
    IGUIBasedLedgerHost getHost();

    void setHost(@Nonnull IGUIBasedLedgerHost host);

    void registerLedgerRightSide(@Nonnull IGUILedger ledger);

    void registerLedgerLeftSide(@Nonnull IGUILedger ledger);

    int getLedgerIndex(LedgerConnectionSide side, @Nonnull String uniqueID);

    @Nonnull
    LinkedHashMap<String, IGUILedger> getRightLedgers();

    @Nonnull
    LinkedHashMap<String, IGUILedger> getLeftLedgers();

    @Nonnull
    Coordinate2D getLedgerLocalCoordinate(LedgerConnectionSide side, @Nonnull String uniqueID);

    /**
     * Method returns the Global coorindate of the RootAnchor pixel of the given Ledger (if it exists on the given side
     * else null)
     * <p>
     * For a Ledger on the left side of the gui it will return the top most right pixel that is visible.
     * For a Ledher on the right side of the gui it will return the top most left pixel that is visible.
     *
     * @param side     The side you want a Ledger coordinate for.
     * @param uniqueID The unique ID of the ledger.
     * @return See method description.
     */
    @Nonnull
    Coordinate2D getLedgerGlobalCoordinate(LedgerConnectionSide side, @Nonnull String uniqueID);

    /**
     * Method called by a ledger to indicate that the opened ledger should be changed.
     *
     * @param ledger The ledger in which was clicked, either to indicate that this one should be opened (and a other one
     *               closed) or this one should be closed.
     */
    void onLedgerClickedInside(@Nonnull IGUILedger ledger);
}
