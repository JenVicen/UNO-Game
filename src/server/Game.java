package game;

import java.util.List;
import java.util.ArrayList;

public class Game {
    private List<Player> players = new ArrayList<>();
    private Deck deck = new Deck();
    private int currentPlayerIndex = 0;
    private boolean reverseOrder = false;
    private Card topCard;

    public Game(List<Player> players) {
        this.players = players;
        this.topCard = deck.drawCard(); // The first card on the table
    }

    public void startGame() {
        // Each player draws 7 cards
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck);
            }
        }
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextTurn() {
        if (reverseOrder) {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }

    public Player getNextPlayer() {
        int nextIndex = reverseOrder ? (currentPlayerIndex - 1 + players.size()) % players.size() 
                                     : (currentPlayerIndex + 1) % players.size();
        return players.get(nextIndex);
    }

    public void skipNextPlayer() {
        nextTurn();  // Skip player logic
    }

    public void reverseTurnOrder() {
        reverseOrder = !reverseOrder;
    }

    public void forceDrawCards(Player player, int count) {
        for (int i = 0; i < count; i++) {
            player.drawCard(deck);
        }
    }

    public boolean checkWinCondition(Player player) {
        return player.getHandSize() == 0;
    }

    public Card getTopCard() {
        return topCard;
    }

    public void setTopCard(Card card) {
        topCard = card;
    }
}
