package game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public void drawCard(Deck deck) {
        hand.add(deck.drawCard());
    }

    public boolean playCard(Card card, Card topCard, Game game) {
        if (card.canBePlayedOn(topCard)) {
            hand.remove(card);
            card.applyEffect(game);
            return true;
        }
        return false;
    }

    public List<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public int getHandSize() {
        return hand.size();
    }

    public boolean hasUno() {
        return hand.size() == 1;
    }

    @Override
    public String toString() {
        return name + " with " + hand.size() + " cards";
    }
}
