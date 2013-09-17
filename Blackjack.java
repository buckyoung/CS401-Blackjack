
import java.io.IOException;
import java.util.Scanner;

public class Blackjack {

    private static Deck deck;
    private static BlackjackHand playerHand;
    private static BlackjackHand dealerHand;
    private static Chips playerChips;
    public static final int AUTO_BET = 10;
    private static Scanner in = new Scanner(System.in);
    public static boolean canDDcanAsk = true;

    public static void main(String[] args) throws IOException {
        //new game, chips set to starting value
        playerChips = new Chips();
        String cmd = "";
        deck = new Deck(); //first deck to play with
        deck.shuffle();
        //do/While you have enough chips to play
        do {

            cls(); //clear console screen
            System.gc(); //collect any old hands or decks laying around
            //new stuff each hand
            playerHand = new BlackjackHand();
            dealerHand = new BlackjackHand();
            if (deck.getCardsLeft() < 16) { //arbitrarily picked 16
                //grab a new deck and shuffle away!
                deck = new Deck();
                //shuffle the deck.
                deck.shuffle();
                System.out.println("*\t\tShuffling...");
                System.out.print("\n\n\n\n\n");
                enterToContinue();
                cls();

            }

            //PLACE BETS
            playerChips.printTotal();
            playerChips.placeBet(AUTO_BET);
            // Deal
            dealCards();
            //PLAY THIS HAND:
            canDDcanAsk = true;
            playHand(cmd);
            //Play again?
            playerChips.printTotal();
            //end of hand
            enterToContinue();


        } while (playerChips.total() >= Chips.MIN_BET); // out of chips
        //Clean up and thank the user
        cls();
        playerChips.printTotal();
        if (playerChips.total() < Chips.MIN_BET) {
            System.out.println(
                    "You cannot afford to place another bet! Better luck next time!");
        }
        System.out.println("\n\n*** Thanks for playing! ***\n\n");
    }

    //<editor-fold defaultstate="collapsed" desc="dealCards">
    /*
     * Deals cards, alternating
     */
    private static void dealCards() {
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
        playerHand.addCard(deck.drawCard());
        dealerHand.addCard(deck.drawCard());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="UserInterface">
    /*
     * Shows the "User Interface" -- has three modes:
     * 1: Dealer only showing First Card, player shows hand
     * 2: Dealer reveals his second Card, player shows hand
     * 3: Dealer draws more cards, player shows hand
     * 4: Final showing, without (enter to continue)
     * 5: Doubling Down
     * 6: Final showing SANS Dealer's Second card (for bust's and dealer bj's)
     */
    private static void UI(int UIMode) {
        //1: Dealer only showing First card
        if (UIMode == 1) {
            // Tell the user what card the dealer is showing (i.e., the dealer's first card).
            System.out.println("Dealers hand:\n\t\t\t | " + dealerHand.getFirstCard());
            System.out.println("\t\t\t | ############");
            // Tell user what cards are in hand:
            System.out.println("\nYour hand: ");
            playerHand.printHand();
            System.out.println("");
            //show total
            //System.out.println("*You have: " + playerHand.getPoints());
        } else if (UIMode == 2) {
            //show dealers second card
            System.out.println("#Dealer Shows: ");
            dealerHand.printHand();
            //print your hand
            System.out.println("\nYour hand: ");
            playerHand.printHand();
            //print VS
            System.out.println("");
            //System.out.println("*  You have: " + playerHand.getPoints() + "  *  Dealer has: " + dealerHand.getPoints() + "  *");
            enterToContinue();

        } else if (UIMode == 3) {
            //show dealer drawing
            System.out.println("#Dealer Draws: ");
            dealerHand.printHand();
            //show your hand
            System.out.println("\nYour hand: ");
            playerHand.printHand();
            //print VS
            System.out.println("");
            //System.out.println("*  You have: " + playerHand.getPoints() + "  *  Dealer has: " + dealerHand.getPoints() + "  *");
            enterToContinue();
        } else if (UIMode == 4) {
            //show dealer drawing
            System.out.println("Dealers hand: ");
            dealerHand.printHand();
            //show your hand
            System.out.println("\nYour hand: ");
            playerHand.printHand();
            //print VS
            System.out.println("");
            //System.out.println("*  You have: " + playerHand.getPoints() + "  *  Dealer has: " + dealerHand.getPoints() + "  *");
            //System.out.println("\n\n\n");
        } else if (UIMode == 5) {
            // Tell the user what card the dealer is showing (i.e., the dealer's first card).
            System.out.println("Dealers hand:\n\t\t\t | " + dealerHand.getFirstCard());
            System.out.println("\t\t\t | ############");
            // Tell user what cards are in hand:
            System.out.println("\nYour hand: ");
            playerHand.printHand();
            //show total
            //System.out.println("*You have: " + playerHand.getPoints());
            enterToContinue();
        } else if (UIMode == 6) {
            // Tell the user what card the dealer is showing (i.e., the dealer's first card).
            System.out.println("Dealers hand:\n\t\t\t | " + dealerHand.getFirstCard());
            System.out.println("\t\t\t | ############");
            // Tell user what cards are in hand:
            System.out.println("\nYour hand: ");
            playerHand.printHand();
            System.out.println("");
            //show total
            //System.out.println("*You have: " + playerHand.getPoints());
        }



    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="cls-screen">
    /*
     * Clears the console screen
     */
    private static void cls() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="enterToContinue">
    /*
     * Creates a "press enter to continue" during dealer reveals
     */
    @SuppressWarnings("empty-statement")
    private static void enterToContinue() {

        System.out.print("\n(enter to continue)");
        try {
            System.in.read();
        } catch (Exception e) {
        };
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Play Hand">
    /*
     * This is where the meat of the program is ... everything that has to do with playing the hand is in here
     */
    private static void playHand(String cmd) {
        do {
            //show UI
            UI(1);

            //CHECK FOR BLACKJACK player and dealer both.
            if (BlackjackHand.hasBlackjack(dealerHand)) { //if dealer has BJ, check for push
                if (BlackjackHand.hasBlackjack(playerHand)) { //push
                    System.out.println("#Dealer Shows:");
                    dealerHand.printHand();
                    System.out.println("\n*****  You both have BLACK JACK! Push!  *****\n");
                    playerChips.push();

                    break;
                } else { //no push, lose
                    System.out.println("#Dealer Shows:");
                    dealerHand.printHand();
                    System.out.println("\n*****  The dealer has BLACK JACK! You lose.  *****\n");
                    playerChips.loseBet();

                    break;
                }
            } else if (BlackjackHand.hasBlackjack(playerHand)) { //if player has BJ, payout
                System.out.println("\n*****  You have BLACK JACK! You Win!  *****\n");
                playerChips.winBlackjack(AUTO_BET);

                break;
            } else { //PLAY HAND WITH HIT/STAY IF NO BLACKJACK
                do { //DO WHILE ACCEPT USER COMMANDS for drawing cards

                    if (canDDcanAsk) {
                        System.out.println("(H)it, (S)tay, (A)dvice, or (D)oubleDown?");
                    } else{
                        System.out.println("(H)it or (S)tay?");
                    }
                        
                    cmd = in.next();
                    //check commands:
                    if (cmd.equalsIgnoreCase("hit") || cmd.equalsIgnoreCase("h")) {
                        //Give another card.
                        if (!playerHand.isFull()) {//check player doesnt have 11 cards in hand
                            playerHand.addCard(deck.drawCard());
                            cls();
                            UI(1);
                            canDDcanAsk = false; // sets to false after first iteration
                        } else {
                            System.out.println("&err& You have too many cards!");
                            break;
                        }

                    } else if (cmd.equalsIgnoreCase("stay") || cmd.equalsIgnoreCase("s")) {
                        break; //break out of this command loop
                    } else if ((cmd.equalsIgnoreCase("advice") || cmd.equalsIgnoreCase("a")) && canDDcanAsk) {
                        Advise.getAdvice(dealerHand.getDealerShowsPoints(), playerHand.getPoints());
                    } else if ((cmd.equalsIgnoreCase("doubledown") || cmd.equalsIgnoreCase("d")) && canDDcanAsk) {

                        if (!playerHand.isFull()) {//check player doesnt have 11 cards in hand
                            playerHand.addCard(deck.drawCard());
                            cls();
                            playerChips.doubleDown(AUTO_BET);
                            UI(5);
                            break;
                        } else {
                            System.out.println("&err& You have too many cards!");
                            break;
                        }

                    }
                    //check for 21 and bust
                    if (playerHand.getPoints() > 21) {
                        break;
                    }
                    
                } while (true); //done drawing
                //CHECK FOR WIN CONDITIONS AND BREAK FROM this DEAL
                if (playerHand.getPoints() > 21) { //check for player bust or deal rest to dealer
                    cls();
                    UI(6);
                    System.out.println("*****  BUSTED!  *****\n");
                    playerChips.loseBet();
                    break;
                } else {
                    //show dealer cards
                    cls();
                    UI(2);
                    //draw if less than 17
                    while (dealerHand.getPoints() < 17) {
                        if (!dealerHand.isFull()) { //dealer draws cards
                            dealerHand.addCard(deck.drawCard());
                        } else {
                            System.out.println("&err& Dealer has too many cards!");
                            break;
                        }
                        //show dealer cards
                        cls();
                        UI(3);

                    }

                    cls();
                    UI(4);

                    //Check dealer bust
                    if (dealerHand.getPoints() > 21) {
                        System.out.println("*****  DEALER BUSTS! YOU WIN!  *****\n");
                        playerChips.winBet();
                        break;
                    }

                    //WIN CONDITIONS
                    if (playerHand.getPoints() > dealerHand.getPoints()) {
                        System.out.println("*****  YOU WIN!  *****\n");
                        playerChips.winBet();
                        break;
                    } else if (playerHand.getPoints() < dealerHand.getPoints()) {
                        System.out.println("*****  YOU LOSE!  *****\n");
                        playerChips.loseBet();
                        break;
                    } else {
                        System.out.println("*****  PUSH!  *****\n");
                        playerChips.push();
                        break;
                    }
                }
            }
        } while (true);// done playing this deal
    }
    //</editor-fold>
}
