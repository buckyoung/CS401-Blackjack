
public class Advise {

    public static void getAdvice(int dealerShows, int playersHand) {
        System.out.println("Dealer shows: " + dealerShows + " and you have: " + playersHand + "\n" + returnHint(dealerShows, playersHand));
    }

    private static String returnHint(int d, int p) {
        String result = "Sorry, I have no advice for you...";

        if(p>=17){
            result = "I suggest you stay.";
        } else if (p<=8){
            result = "I suggest you hit.";
        } else if (p == 11){
            result = "I suggest you double-down.";
        } else if (d<7 && p>=12){
            result = "I suggest you stay.";
        } else if (d>=7 && p>=12){
            result = "I suggest you hit.";
        } else if (p== 10 && d<10){
            result = "I suggest you double-down.";
        } else if (p == 10 && d>=10){
            result = "I suggest you hit.";
        } else if (p == 9 && d>2 && d<7){
            result = "I suggest you double-down.";
        } else if (p == 9 && (d==2||d>=7)){
            result = "I suggest you hit.";
        }
        
        return result;
    }
}
