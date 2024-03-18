package ingsw.codex_naturalis.model.cards.objective;

import ingsw.codex_naturalis.model.cards.PlayerAreaCard;
import ingsw.codex_naturalis.model.enumerations.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ObjectiveCard's class
 * An objective card gives points for each objective achieved
 * The objective can be: to have a certain number of symbols on player's area or to compose a certain pattern with cards
 * The patterns can be two type: an L or a diagonal.If we image to represent the pattern ad a 3x3 matrix,
 * It is not necessary to have an attribute to represent the color of the PlayerAreaCard, because we can know it from
 * the kingdom in which it belongs to.
 * Finally, ObjectiveCard has an interface that call the right algorithms at runtime.
 */
public abstract class ObjectiveCard {
    private final int points;
    private final PatternStrategy patternStrategy;

    /**
     * Constructor
     * @param points points of ObjectiveCard
     * @param patternStrategy the patter
     */
    public ObjectiveCard(int points, PatternStrategy patternStrategy){
        this.points=points;
        this.patternStrategy=patternStrategy;
    }

    /**
     * Invoke at runtime the right algorithm depending on the type of card the player has.
     * @return number of extraPoints
     */
    public int execute(){
        return patternStrategy.run();
    }

    /**
     * Getter
     * @return how many points the card gives for one objective achieved
     */
    public int getPoints() {
        return points;
    }
}
