package sample;

import javafx.scene.image.Image;

public class Card {
    Suit suit;
    Type type;
    Image picture;

    enum Suit {
        HEARTS("h"), DIAMONDS("d"), CLUBS("c"), SPADES("s");
        final String value;
        Suit(String suit) {
            value = suit;
        }
    }

    enum Type {
        TWO(2, "2"), THREE(3, "3"), FOUR(4, "4"), FIVE(5, "5"), SIX(6, "6"), SEVEN(7, "7"),
        EIGHT(8, "8"), NINE(9, "9"), JACK(10, "j"), QUEEN(10, "q"), KING(10, "k"), ACE(11, "a");
        final int value;
        final String type;
        Type (int x, String y) {
            value = x;
            type = y;
        }
    }

    static public String getImagePath(Suit suit, Type type) {
        return "file:/Users/mariafilippova/Java/Blackjack/src/resources/" + type.type + suit.value + ".gif";
    }

    Card(Suit suit, Type type, Image image) {
        this.suit = suit;
        this.type = type;
        this.picture = image;
    }
}
