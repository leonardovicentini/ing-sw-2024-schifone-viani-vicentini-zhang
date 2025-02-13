package ingsw.codex_naturalis.model.objectiveCards;
import ingsw.codex_naturalis.server.model.Game;
import ingsw.codex_naturalis.server.model.cards.Corner;
import ingsw.codex_naturalis.server.model.cards.initialResourceGold.PlayableCard;
import ingsw.codex_naturalis.server.model.cards.initialResourceGold.PlayableSide;
import ingsw.codex_naturalis.server.model.cards.initialResourceGold.back.Back;
import ingsw.codex_naturalis.server.model.cards.objective.ObjectiveCard;
import ingsw.codex_naturalis.server.model.cards.objective.PatternObjectiveCard;
import ingsw.codex_naturalis.common.enumerations.PlayableCardType;
import ingsw.codex_naturalis.common.enumerations.Symbol;
import ingsw.codex_naturalis.server.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LPatternCalcExtraPointsStrategyTest {
    Player player;
    Game game;
    PlayableCard initialCard;
    ObjectiveCard patternObjectiveCard;
    @BeforeEach
    void setUp(){
        player = new Player("Test");
        game = new Game(0,4);
        game.addPlayer(player);
        initialCard = new PlayableCard(
                "ITest",
                PlayableCardType.INITIAL,
                Symbol.EMPTY,
                new PlayableSide(
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false)),
                new Back(
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        List.of(Symbol.EMPTY,Symbol.EMPTY,Symbol.EMPTY)));
        player.setInitialCard(initialCard);
        player.playInitialCard();
    }
    private PlayableCard fungiResourceCard(){
        PlayableCard resourceCard;
        resourceCard = new PlayableCard(
                "RTest",
                PlayableCardType.RESOURCE,
                Symbol.FUNGI,
                new PlayableSide(
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false)),
                new Back(
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        List.of(Symbol.FUNGI))
        );
        return(resourceCard);
    }
    private PlayableCard insectResourceCard(){
        PlayableCard resourceCard;
        resourceCard= new PlayableCard(
                "RTest",
                PlayableCardType.RESOURCE,
                Symbol.INSECT,
                new PlayableSide(
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false)),
                new Back(
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        List.of(Symbol.INSECT))
        );
        return(resourceCard);
    }
    private PlayableCard plantResourceCard(){
        PlayableCard resourceCard;
        resourceCard = new PlayableCard(
                "RTest",
                PlayableCardType.RESOURCE,
                Symbol.PLANT,
                new PlayableSide(
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false)),
                new Back(
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        List.of(Symbol.PLANT))
        );
        return(resourceCard);
    }
    private PlayableCard animalResourceCard(){
        PlayableCard resourceCard;
        resourceCard= new PlayableCard(
                "RTest",
                PlayableCardType.RESOURCE,
                Symbol.ANIMAL,
                new PlayableSide(
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false)),
                new Back(
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        new Corner(Symbol.EMPTY,false),
                        List.of(Symbol.ANIMAL))
        );
        return(resourceCard);
    }
    private ObjectiveCard bottomLeftLObjectiveCard(){
        return (new PatternObjectiveCard(
                "OTest",
                3,
                List.of(List.of(0,0),List.of(1,1),List.of(1,3)),
                List.of(Symbol.INSECT,Symbol.PLANT,Symbol.PLANT),
                1,
                3,
                0,
                0
        ));
    }
    private ObjectiveCard bottomRightLObjectiveCard(){
        return (new PatternObjectiveCard(
                "OTest",
                3,
                List.of(List.of(1,0),List.of(0,1),List.of(0,3)),
                List.of(Symbol.PLANT,Symbol.FUNGI,Symbol.FUNGI),
                1,
                3,
                0,
                0));
    }
    private ObjectiveCard topLeftLObjectiveCard(){
        return (new PatternObjectiveCard(
                "OTest",
                3,
                List.of(List.of(0,3),List.of(1,2),List.of(1,0)),
                List.of(Symbol.ANIMAL,Symbol.INSECT,Symbol.INSECT),
                1,
                3,
                0,
                0));
    }
    private ObjectiveCard topRightLObjectiveCard(){
        return (new PatternObjectiveCard(
                "OTest",
                3,
                List.of(List.of(0,0),List.of(0,2),List.of(1,3)),
                List.of(Symbol.ANIMAL,Symbol.ANIMAL,Symbol.FUNGI),
                1,
                3,
                0,
                0));
    }
    private void bottomLeftL(){
        player.getPlayerArea().setCardOnCoordinates(insectResourceCard(),1,1);
        player.getPlayerArea().setCardOnCoordinates(plantResourceCard(),2,2);
        player.getPlayerArea().setCardOnCoordinates(plantResourceCard(),1,3);
        player.getPlayerArea().setCardOnCoordinates(plantResourceCard(),2,4);
    }
    private void bottomRightL(){
        player.getPlayerArea().setCardOnCoordinates(plantResourceCard(),-1,1);
        player.getPlayerArea().setCardOnCoordinates(fungiResourceCard(),-2,2);
        player.getPlayerArea().setCardOnCoordinates(fungiResourceCard(),-3,3);
        player.getPlayerArea().setCardOnCoordinates(fungiResourceCard(),-2,4);
    }
    private void topLeftL(){
        player.getPlayerArea().setCardOnCoordinates(animalResourceCard(),1,-1);
        player.getPlayerArea().setCardOnCoordinates(insectResourceCard(),2,-2);
        player.getPlayerArea().setCardOnCoordinates(insectResourceCard(),1,-3);
        player.getPlayerArea().setCardOnCoordinates(insectResourceCard(),2,-4);
    }
    private void topRightL(){
        player.getPlayerArea().setCardOnCoordinates(fungiResourceCard(),-1,-1);
        player.getPlayerArea().setCardOnCoordinates(animalResourceCard(),-2,-2);
        player.getPlayerArea().setCardOnCoordinates(animalResourceCard(),-1,-3);
        player.getPlayerArea().setCardOnCoordinates(animalResourceCard(),-2,-4);
    }
    @Test
    void bottomLeftLTest(){
        bottomLeftL();
        patternObjectiveCard = bottomLeftLObjectiveCard();
        patternObjectiveCard.gainPoints(List.of(player.getPlayerArea()));
        assertEquals(3,player.getPlayerArea().getExtraPoints());
    }
    @Test
    void bottomRightLTest(){
        bottomRightL();
        patternObjectiveCard = bottomRightLObjectiveCard();
        patternObjectiveCard.gainPoints(List.of(player.getPlayerArea()));
        assertEquals(3,player.getPlayerArea().getExtraPoints());
    }
    @Test
    void topLeftLTest(){
        topLeftL();
        patternObjectiveCard = topLeftLObjectiveCard();
        patternObjectiveCard.gainPoints(List.of(player.getPlayerArea()));
        assertEquals(3,player.getPlayerArea().getExtraPoints());
    }
    @Test
    void topRightLTest(){
        topRightL();
        patternObjectiveCard = topRightLObjectiveCard();
        patternObjectiveCard.gainPoints(List.of(player.getPlayerArea()));
        assertEquals(3,player.getPlayerArea().getExtraPoints());
    }
}
