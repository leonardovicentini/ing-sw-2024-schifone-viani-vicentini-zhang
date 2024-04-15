package ingsw.codex_naturalis.model.cards.initialResourceGold.front;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ingsw.codex_naturalis.model.cards.Corner;
import ingsw.codex_naturalis.model.player.PlayerArea;
import ingsw.codex_naturalis.enumerations.Symbol;

import java.util.HashMap;

public class PointsGiverForObject extends Needy {

    private final Symbol object;

    //-------------------------------------------------------------------------------------------
    @JsonCreator
    public PointsGiverForObject(
            @JsonProperty("topLeftCorner") Corner topLeftCorner,
            @JsonProperty("topRightCorner") Corner topRightCorner,
            @JsonProperty("bottomLeftCorner") Corner bottomLeftCorner,
            @JsonProperty("bottomRightCorner") Corner bottomRightCorner,
            @JsonProperty("points") int points,
            @JsonProperty("requirements") HashMap<Symbol, Integer> requirements,
            @JsonProperty("object") Symbol object){
        super(topLeftCorner, topRightCorner, bottomLeftCorner, bottomRightCorner, points, requirements);
        this.object = object;
    }

    //-------------------------------------------------------------------------------------------
    @Override
    public String handCardToString(Symbol kingdom) {
        return null;
    }


    @Override
    protected void gainPoints(PlayerArea playerArea){
        playerArea.setPoints(playerArea.getPoints() + getPoints() * playerArea.getNumOfSymbol(object));
    }
}
