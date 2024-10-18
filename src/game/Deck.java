package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {
        initializeDeck();
        shuffle();
    }

    public void initializeDeck() {
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        
        // Add regular cards (0-9) for each color
        for (String color : colors) {
            for (int i = 0; i <= 9; i++) {
                cards.add(new RegularCard(color, i));
                if (i != 0) cards.add(new RegularCard(color, i)); // Two copies of each from 1-9
            }
        }

        // Add Wildcards
        for (int i = 0; i < 4; i++) {
            cards.add(new WildCard("+4"));
            cards.add(new WildCard("+2"));
            cards.add(new WildCard("Skip"));
            cards.add(new WildCard("Reverse"));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            initializeDeck(); // Reshuffle if the deck runs out
            shuffle();
        }
        return cards.remove(0);
    }

    public int getDeckSize() {
        return cards.size();
    }
}
