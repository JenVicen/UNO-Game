package game;

public class WildCard extends Card {
    private String effectType; // Possible effects: "+4", "Skip", "Reverse", "+2"

    public WildCard(String effectType) {
        super("Wild");  // Wild cards have no fixed color initially
        this.effectType = effectType;
    }

    public String getEffectType() {
        return effectType;
    }

    @Override
    public boolean canBePlayedOn(Card card) {
        return true; // Wildcards can be played on any card
    }

    @Override
    public void applyEffect(Game game) {
        switch (effectType) {
            case "+4":
                game.forceDrawCards(game.getNextPlayer(), 4);
                game.skipNextPlayer();
                break;
            case "+2":
                game.forceDrawCards(game.getNextPlayer(), 2);
                break;
            case "Skip":
                game.skipNextPlayer();
                break;
            case "Reverse":
                game.reverseTurnOrder();
                break;
        }
    }

    @Override
    public String toString() {
        return "Wild " + effectType;
    }
}
