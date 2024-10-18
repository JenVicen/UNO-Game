package game;

public abstract class Card {
    protected String color;

    public Card(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract boolean canBePlayedOn(Card card);

    public abstract void applyEffect(Game game);
}
