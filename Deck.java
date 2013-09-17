//creates a deck of cards

public class Deck {

    private Card[] cards = new Card[52];
    private int topCard;

    public Deck() { //Constructor
        //initialize topcard
        topCard = 0;
        //initialize deck
        for (Card.Suit suit : Card.Suit.values()) { //cycles suit ... ".values()" is specifically for ENUMs
            for (int v = 1; v <= Card.KING; v++) { //cycles values
                cards[topCard++] = new Card(v, suit);
            }
        }
        //Note: topCard will be at 52 after for loops
    }

    public void shuffle() {
        for (int i = 0; i < 30000; i++) {
            swap(getRandom(cards.length), getRandom(cards.length));
        }
    }

    public Card drawCard() {
        //since topCard is at 52 (the array length), we must decrement in a PREprocess
        Card result = null;
        if (hasMoreCards()) {
            result = cards[--topCard];
        }
        return result;
    }

    public boolean hasMoreCards() {
        if (topCard > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void swap(int position1, int position2) {
        Card temp = cards[position1];
        cards[position1] = cards[position2];
        cards[position2] = temp;
    }

    private int getRandom(int size) {
        return (int) (Math.random() * size); //will return a value from 0-51!
    }
    
    public int getCardsLeft(){
        return topCard;
    }
}
