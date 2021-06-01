package sample;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> deck = new ArrayList<>();
    private static Deck singleDeck;
    int pointer = 0;

    static Deck getDeck() {
        if (singleDeck == null) {
            singleDeck = new Deck();
        }
        return singleDeck;
    }

    private Deck() {
        Deck d = Deck.getDeck();
        for (Card.Suit suit: Card.Suit.values()) {
            for (Card.Type type: Card.Type.values()) {
                d.deck.add(new Card(suit, type, new Image(Card.getImagePath(suit, type))));
            }
        }
    }

    public void shuffleCards() {

    }

    public Card giveCard() {
        if (getSize() == 0) {
            return null;
        }
        return deck.get(pointer++);
    }

    public int getSize() {
        return deck.size() - pointer;
    }

}
