package ingsw.codex_naturalis.model.cards.gold;

import ingsw.codex_naturalis.model.Corner;
import ingsw.codex_naturalis.model.PlayerArea;
import ingsw.codex_naturalis.model.cards.playerAreaCardStrategy.calcPoints.CornerCalcPointsStrategy;
import ingsw.codex_naturalis.model.cards.playerAreaCardStrategy.coverCorners.SimpleCoverCornersStrategy;
import ingsw.codex_naturalis.model.cards.playerAreaCardStrategy.getSymbols.CornerResourcesGetSymbolsStrategy;
import ingsw.codex_naturalis.model.cards.playerAreaCardStrategy.isPlayable.RequirementsIsPlayableStrategy;
import ingsw.codex_naturalis.model.enumerations.Symbol;

import java.util.*;

/**
 * The front side of the gold card that gives points based on the corners covered
 */
public class GoldCardFrontCorners extends GoldCardFront {

    /**
     * Constructor
     * @param kingdom Kingdom
     * @param topLeftCorner Top left corner
     * @param topRightCorner Top right corner
     * @param bottomLeftCorner Bottom left corner
     * @param bottomRightCorner Bottom right corner
     * @param points Points
     * @param requirements Requirements
     */
    public GoldCardFrontCorners(Symbol kingdom, Corner topLeftCorner, Corner topRightCorner, Corner bottomLeftCorner, Corner bottomRightCorner, int points, HashMap<Symbol, Integer> requirements){
        super(kingdom, topLeftCorner, topRightCorner, bottomLeftCorner, bottomRightCorner, points, requirements);
    }


    /**
     * The gold card has been drawn
     * @param playerArea The player area that will eventually have the gold card
     */
    @Override
    public void drawn(PlayerArea playerArea){
        setIsPlayableStrategy(new RequirementsIsPlayableStrategy(playerArea, getRequirements()));
    }

    /**
     * The front side of the gold card has been played
     * @param playerArea The player area that now has the gold card
     * @param x Coordinate x on the area
     * @param y Coordinate y on the area
     */
    @Override
    public void played(PlayerArea playerArea, int x, int y){
        setCoverCornersStrategy(new SimpleCoverCornersStrategy(playerArea));
        setGetSymbolsStrategy(new CornerResourcesGetSymbolsStrategy(playerArea, getTopLeftCorner(), getTopRightCorner(), getBottomLeftCorner(), getBottomRightCorner()));
        setCalcPointsStrategy(new CornerCalcPointsStrategy(playerArea, x, y, getPoints()));
    }
}
