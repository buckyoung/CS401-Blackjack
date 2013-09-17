
public class Card {

    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;

    public enum Suit {

        DIAMONDS, CLUBS, SPADES, HEARTS
    };
    private Suit suit;
    private int value;

    public Card(int v, Suit s) {
        this.value = v;
        this.suit = s;
    }

    public int getValue() {
        return value; //1-13
    }

    public Suit getSuit() {
        return suit;
    }

    public String toString() {
        return getValueAsString() + " of " + getSuitAsString();
    }

    private String getValueAsString() {
        String result = Integer.toString(value);
        if (value == 11) {
            result = "Jack";
        } else if (value == 12) {
            result = "Queen";
        } else if (value == 13) {
            result = "King";
        } else if (value == 1) {
            result = "Ace";
        }
        return result;
    }

    private String getSuitAsString() {
        String result = "Clubs";
        if (suit == Suit.DIAMONDS) {
            result = "Diamonds";
        } else if (suit == Suit.SPADES) {
            result = "Spades";
        } else if (suit == Suit.HEARTS) {
            result = "Hearts";
        }
        return result;
    }

}