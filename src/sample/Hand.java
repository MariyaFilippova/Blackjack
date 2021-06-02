package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;


public class Hand {
    final ObservableList<Node> hand;
    SimpleIntegerProperty score = new SimpleIntegerProperty(0);

    public void takeCard() {
        Card card = Deck.getDeck().getCard();
        ImageView img = new ImageView(card.picture);
        hand.add(img);
        if (score.get() + card.type.value > 21 && card.type == Card.Type.ACE) {
            score.set(score.get() + card.type.value - 10);
        }
        else {
            score.set(score.get() + card.type.value);
        }
    }

    Hand(ObservableList<Node> hand) {
        this.hand = hand;
    }

    public void reset() {
        hand.clear();
        score.set(0);
    }
}
