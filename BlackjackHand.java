
public class BlackjackHand {

    private Card[] cards = new Card[11];
    private int nextCardIndex = 0;

    public BlackjackHand() {
        for (int i = 0; i < cards.length; i++) {
            cards[i] = null;
        }
    }

    public void printHand() {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                break;
            }
            System.out.println("\t\t\t | " + cards[i]);
        }
    }

    public Card getFirstCard() {
        Card result = null;
        if (nextCardIndex > 0) {
            result = cards[0];
        }
        return result;
    }

    public void addCard(Card card) {
        if (!isFull()) {
            cards[nextCardIndex] = card;
            nextCardIndex++;
        }
    }

    public boolean isFull() {
        return (nextCardIndex == cards.length);
    }

    public int getCardCount() {
        return nextCardIndex;
    }

    private boolean hasAces() {
        boolean result = false;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                break;
            }
            if (cards[i].getValue() == 1) {
                result = true;
                break;
            }
        }
        return result;
    }

    public int getPoints() {
        int result = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == null) {
                break;
            }
            result += Math.min(10, cards[i].getValue());
        }
        if (result <= 11 && this.hasAces()) {
            result += 10;
        }
        return result;
    }

    public int getDealerShowsPoints() {
        int result=0;
        result += Math.min(10, cards[0].getValue());
        if (cards[0].getValue() == 1) {
            result = 11;
        }
        return result; //return the result or ten or 11 for ace
    }

    public static boolean hasBlackjack(BlackjackHand hand) {
        if (hand.getCardCount() != 2) {
            return false;
        } else if (hand.getPoints() == 21) {
            return true;
        } else {
            return false;
        }

    }
}