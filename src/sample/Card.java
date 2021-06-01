package sample;

import javafx.scene.image.Image;

public class Card {
    Suit suit;
    Type type;
    Image picture;

    enum Suit {
        HEARTS("hearts"), DIAMONDS("diamonds"), CLUBS("clubs"), SPADES("spades");
        final String value;
        Suit(String suit) {
            value = suit;
        }
    }

    enum Type {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), JACK(10), QUEEN(10), KING(10), ACE(11);
        final int value;
        Type (int x) {
            value = x;
        }
    }

    static public String getImagePath(Suit suit, Type type) {
        return "/Users/mariafilippova/Java/Blackjack/src/resources" + suit.value + type.value;
    }

    Card(Suit suit, Type type, Image image) {
        this.suit = suit;
        this.type = type;
        this.picture = image;
    }
}
