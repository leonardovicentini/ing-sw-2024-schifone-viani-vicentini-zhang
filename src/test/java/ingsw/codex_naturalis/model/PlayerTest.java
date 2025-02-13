package ingsw.codex_naturalis.model;
import ingsw.codex_naturalis.server.exceptions.NotYourTurnException;
import ingsw.codex_naturalis.server.exceptions.NotPlayableException;
import ingsw.codex_naturalis.server.exceptions.NotYourPlayTurnStatusException;
import ingsw.codex_naturalis.server.model.cards.Corner;
import ingsw.codex_naturalis.server.model.cards.initialResourceGold.PlayableCard;
import ingsw.codex_naturalis.server.model.cards.initialResourceGold.PlayableSide;
import ingsw.codex_naturalis.server.model.cards.initialResourceGold.back.Back;
import ingsw.codex_naturalis.common.enumerations.Color;
import ingsw.codex_naturalis.common.enumerations.PlayableCardType;
import ingsw.codex_naturalis.common.enumerations.Symbol;
import ingsw.codex_naturalis.common.enumerations.TurnStatus;
import ingsw.codex_naturalis.server.model.player.Player;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player = new Player("TestPlayer");
    Player player1 = new Player("TestPlayer1");
    private PlayableCard initialCard() {
        PlayableCard initialCard;
        initialCard = new PlayableCard(
                "I00",
                PlayableCardType.INITIAL,
                Symbol.EMPTY,
                new PlayableSide(
                        new Corner(Symbol.EMPTY, false),
                        new Corner(Symbol.EMPTY, false),
                        new Corner(Symbol.EMPTY, false),
                        new Corner(Symbol.EMPTY, false)),
                new Back(
                        new Corner(Symbol.EMPTY, false),
                        new Corner(Symbol.EMPTY, false),
                        new Corner(Symbol.EMPTY, false),
                        new Corner(Symbol.EMPTY, false),
                        List.of(Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY)));
        return (initialCard);
    }
    private PlayableCard initialCoveredCard() {
        PlayableCard initialCard;
        initialCard = new PlayableCard(
                "I00",
                PlayableCardType.INITIAL,
                Symbol.EMPTY,
                new PlayableSide(
                        new Corner(Symbol.EMPTY, true),
                        new Corner(Symbol.EMPTY, true),
                        new Corner(Symbol.EMPTY, true),
                        new Corner(Symbol.EMPTY, true)),
                new Back(
                        new Corner(Symbol.EMPTY, true),
                        new Corner(Symbol.EMPTY, true),
                        new Corner(Symbol.EMPTY, true),
                        new Corner(Symbol.EMPTY, true),
                        List.of(Symbol.EMPTY, Symbol.EMPTY, Symbol.EMPTY)));
        return (initialCard);
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

    /**
     * Tests setColor (), getNickname (), setTurnStatus (), getTurnStatus (), getPlayerArea ()
     */
    @Test
    void testPlayerAttributes() {
        assertNotNull(player.getPlayerArea());
        player.setColor(Color.RED);
        assertEquals(Color.RED, player.getColor());
        assertEquals("TestPlayer", player.getNickname());
        player.setTurnStatus(TurnStatus.PLAY);
        assertEquals(TurnStatus.PLAY, player.getTurnStatus());
        player.setTurnStatus(TurnStatus.DRAW);
        assertEquals(TurnStatus.DRAW, player.getTurnStatus());
    }
    /**
     * Tests flipInitialCard ()
     */
    @Test
    void testFlipInitialCard() {
        PlayableCard initialCard = initialCard();
        player.setInitialCard(initialCard);
        player.flipInitialCard();
        assertTrue(initialCard.isShowingFront());
        // showingFront è inizializzato a false nel costruttore di initialCard (),
        // quindi flippandolo deve mostrare true
    }

    /**
     * Tests flipCard (int index)
     */
    @Test
    void testFlipHandCard() {
        PlayableCard card1 = insectResourceCard();
        PlayableCard card2 = insectResourceCard();
        List<PlayableCard> hand = new ArrayList<>();
        hand.add(card1);
        hand.add(card2);
        player.setupHand(hand);

        player.flipCard(0);
        assertTrue(card1.isShowingFront());

        player.flipCard(1);
        assertTrue(card2.isShowingFront());
    }

    /**
     * Tests getInitialCard() and setInitialCard ()
     */
    @Test
    void testGetInitialCard() {
        PlayableCard initialCard = initialCard();
        player.setInitialCard(initialCard);
        assertEquals(initialCard, player.getInitialCard());
    }

    /**
     * Tests playInitialCard ()
     */
    @Test
    void testPlayInitialCard() {
        PlayableCard initialCard = initialCard();
        player.setInitialCard(initialCard);
        player.playInitialCard();
        assertNull(player.getInitialCard());
        assertEquals(initialCard, player.getPlayerArea().getCardOnCoordinates(0, 0));
    }

    /**
     * Tests playCard (), getHand (), setHand (), setupHand ()
     */
    @Test
    void testPlayCard() throws NotYourTurnException, NotYourPlayTurnStatusException, NotPlayableException {

        player.setTurnStatus(TurnStatus.PLAY);
        PlayableCard card1 = insectResourceCard();
        PlayableCard card2 = insectResourceCard();
        PlayableCard card3 = initialCard();
        PlayableCard card4 = initialCoveredCard();
        List<PlayableCard> hand = new ArrayList<>();
        hand.add(card1);
        hand.add(card2);
        player.setupHand(hand);
        assertEquals (hand, player.getHand());
        player1.setupHand(hand);
        assertEquals (hand, player1.getHand());
        player1.setInitialCard(card4);
        player.setInitialCard(card3);
        player.playInitialCard();
        player1.playInitialCard();
        player.playCard(0, 1, 1);
        assertFalse(player.getHand().contains(card1));
        assertEquals(card1, player.getPlayerArea().getCardOnCoordinates(1, 1));
        player.setTurnStatus(TurnStatus.DRAW);
        assertThrows(NotYourPlayTurnStatusException.class,()->{player.playCard(1,-1,-1);});
        assertThrows(NotPlayableException.class, () ->{player1.playCard(0, 1,1);});

    }

    /**
     * Tests drawCard (PlayableCard playableCard)
     */
    @Test
    void testDrawCard() {
        PlayableCard card = insectResourceCard();
        player.drawCard(card);
        assertTrue(player.getHand().contains(card));
    }

    /**
     * Tests isInGame (), setInGame (), setReady ()
     */
    @Test
    void testIsInGame() {
        assertTrue(player.isInGame());
        player.setReady(true);
        player.setInGame(false);
        assertFalse(player.isInGame());
    }

}