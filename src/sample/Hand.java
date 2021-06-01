package sample;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    List<Card> hand = new ArrayList<>();
    int score;

    void getCard() {
        Deck deck = Deck.getDeck();
    }
}
