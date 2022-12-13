import java.util.HashMap;
import java.util.LinkedList;
import java.util.*;

public class Seats {
    private int available;
    private LinkedList<String> seats= new LinkedList<String>();
    LinkedHashMap<String, Boolean> seat = new LinkedHashMap<String, Boolean>(); 
    
    String next = "A";

    Seats(int n){
        available = n;

        for (int i = 0; i < n; i++){
            String s = "A";
            if (i!= 0 && i%5 == 0){
                int charValue = s.charAt(0);
                next = String.valueOf( (char) (charValue + 1));      
            }
            s = next;
            s += String.valueOf(i+1);
            seat.put(s, true);
            seats.add(s);
        }
    }

    public void decrease( String[] seatsBooked){
       for (String s: seatsBooked){
            seat.put(s, false);
       }
    }

    public void increase(String seatCancelled){
       seat.put(seatCancelled, true);
   
    }

    public int getAvailable(){
        return available;
    }

    public Map<String, Boolean> getSeats(){
        return seat;
    }
}
