package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> deck;
    private static final Deck singleDeck = new Deck();
    private int pointer = 0;

    static Deck getDeck() {
        return singleDeck;
    }

    private Deck() {
        deck = new ArrayList<>();
        for (Card.Suit suit: Card.Suit.values()) {
            for (Card.Type type: Card.Type.values()) {
                try {
                    deck.add(new Card(suit, type, new Image(Card.getImagePath(suit, type))));
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void shuffleCards() {
        List<Card> shuffledDeck = new ArrayList<>();
        while (deck.size() > 0) {
            int index = (int) (Math.random() * deck.size());
            shuffledDeck.add(deck.remove(index));
        }
        deck = shuffledDeck;
    }

    private void restoreDeck() {
        pointer = 0;
    }

    public void newDeck() {
        restoreDeck();
        shuffleCards();
    }

    public Card getCard() {
        if (getSize() == 0) {
            return null;
        }
        return deck.get(pointer++);
    }

    public int getSize() {
        return deck.size() - pointer;
    }
}
