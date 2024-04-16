package ingsw.codex_naturalis.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import ingsw.codex_naturalis.model.Deck;
import ingsw.codex_naturalis.model.cards.initialResourceGold.PlayableCard;
import ingsw.codex_naturalis.model.cards.objective.ObjectiveCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardTUIRappresentation {

    Deck<PlayableCard> initialCardsDeck;
    Deck<PlayableCard> resourceCardsDeck;
    Deck<PlayableCard> goldCardsDeck;
    Deck<ObjectiveCard> objectiveCardsDeck;

    @BeforeEach
    void setup(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String initialCardsJsonFilePath = "src/main/resources/ingsw/codex_naturalis/resources/initialCards.json";
            String resourceCardsJsonFilePath = "src/main/resources/ingsw/codex_naturalis/resources/resourceCards.json";
            String goldCardsJsonFilePath = "src/main/resources/ingsw/codex_naturalis/resources/goldCards.json";
            String objectiveCardsJsonFilePath = "src/main/resources/ingsw/codex_naturalis/resources/objectiveCards.json";

            List<PlayableCard> initialCards = objectMapper.readValue(new File(initialCardsJsonFilePath), new TypeReference<List<PlayableCard>>() {});
            List<PlayableCard> resourceCards = objectMapper.readValue(new File(resourceCardsJsonFilePath), new TypeReference<List<PlayableCard>>() {});
            List<PlayableCard> goldCards = objectMapper.readValue(new File(goldCardsJsonFilePath), new TypeReference<List<PlayableCard>>() {});
            List<ObjectiveCard> objectiveCards = objectMapper.readValue(new File(objectiveCardsJsonFilePath), new TypeReference<List<ObjectiveCard>>() {});

            initialCardsDeck = new Deck<>(initialCards);
            resourceCardsDeck = new Deck<>(resourceCards);
            goldCardsDeck = new Deck<>(goldCards);
            objectiveCardsDeck = new Deck<>(objectiveCards);
        } catch (IOException e){
            System.err.println("ERROR while opening json file");
        }
    }

    @Test
    void objectiveCardRappresentationTest(){
        ObjectiveCard card;
        for (int i = 0; i < 16; i++) {
            card = objectiveCardsDeck.drawACard("test");
            System.out.println(card.cardToString());
        }
    }

    @Test
    void resourceAndGoldCardBackRappresentationTest(){
        PlayableCard card;
        for (int i = 0; i < 40; i++) {
            card = resourceCardsDeck.drawACard("test");
            System.out.println(card.getBack().handCardToString(card.getKingdom()));
        }
        for (int i = 0; i < 40; i++) {
            card = goldCardsDeck.drawACard("test");
            System.out.println(card.getBack().handCardToString(card.getKingdom()));
        }
    }
}
