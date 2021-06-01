package sample;

public class Blackjack {
    public static final int TWENTY_ONE = 21;

    public static void main(String[] args) {
        Hand dealer = new Hand();
        Hand player = new Hand();
        Deck deck = Deck.getDeck();
        deck.shuffleCards();
    }
}
