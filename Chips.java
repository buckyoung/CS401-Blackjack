//Keeps track of chips and betting
public class Chips {
    
    private static final int STARTING_CHIPS = 250;
    public static final int MIN_BET = 10;

    private int chips;
    private int currentBet;
    
    public Chips(){
       chips = STARTING_CHIPS;
       currentBet=0;
    }
    
    public void printTotal(){
        System.out.println("*\t  You have " + chips + " chips.");
    }
    
    public int total(){
        return chips;
    }
    
    public void placeBet(int bet){
        System.out.println("*\t   You bet " + bet + " chips.\n");
        currentBet += bet;
       
    }
    
    public void doubleDown(int bet){
        System.out.println("*\t   You doubled down with " + bet + " more chips.\n");
        currentBet += bet;
    }
    
    public void winBet(){
        chips += currentBet;
        currentBet =0;//reset current
        
    }
    public void winBlackjack(int bet){
       chips += ((bet/2) * 3); //payout 3:2 // will truncate, oh well!
       //also it is ok to add this straight to chips because you will never
       //double down and win a blackjack
        
    }
    
    public void loseBet(){
        chips -= currentBet;
        currentBet = 0; //reset current
        
    }
    
    public void push(){
        //reset current
        currentBet=0;
    }
    
}
