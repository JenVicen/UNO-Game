package game;

public class RegularCard extends Card {
    private int number;

    public RegularCard(String color, int number) {
        super(color);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean canBePlayedOn(Card card) {
        if (card instanceof RegularCard) {
            RegularCard otherCard = (RegularCard) card;
            return this.color.equals(otherCard.getColor()) || this.number == otherCard.getNumber();
        }
        return false;
    }

    @Override
    public void applyEffect(Game game) {
        // No special effect for regular cards
    }

    @Override
    public String toString() {
        return color + " " + number;
    }
}

