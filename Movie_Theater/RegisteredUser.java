import java.util.HashMap;
import java.util.LinkedList;
import java.util.*;
public class RegisteredUser extends Guest{
    private RegisteredPayment test1;
    private String email;
    private String password;
    private String cardNumber;
    private String cvv;
    private String expiry;
    private Boolean feePaid;
    //private LinkedLink<Movie> moviesBooked = new LinkedList<Movie>();
    private Map<Movie, String> moviesBooked = new HashMap<Movie, String>(); 

    RegisteredUser(RegisteredPayment type, String email, String password, String cardNumber, String cvv, String expiry, Boolean feePaid ) {
        
        super(type);
        test1 = type;
        this.email = email;
        this.password = password;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiry = expiry;
        this.feePaid = feePaid;
    }


    public void annualFee(){
        feePaid= true;
        MovieAppMain.feePayment(email);
        test1.annualFee();
    }

    public String getEmail(){
        return email;
    }
    
    public String getPassword(){
        return password;
    }

    public String getCardNumber(){
        return cardNumber;
    }

    public String getCVV(){
        return cvv;
    }

    public String getExpiry(){
        return expiry;
    }

    public Boolean getFeePaid(){
        return feePaid;
    }

    public void addMovie(Movie mov, String seats){
        
        String[] seatsArray = seats.split("\n");
        String details ="";
        for (int i = 0 ; i < seatsArray.length; i++){
            details += mov.getTicketDetails(seatsArray[i]);
            details += "\n";

        // UNCOMMENT ONCE YOU FIGURE OUT THE HOST
        //    Ticket.emailTicket(email, mov.getName(), mov.getTicketDetails(seatsArray[i]));
        }
        moviesBooked.put(mov, details);
    }

    //// this isnt wokring!!!
    public String getMovies(){
        String all ="";
  
        for (Map.Entry<Movie, String> s : moviesBooked.entrySet()) {
            all += s.getKey().getName();
            all += ": ";
            all += s.getValue();
        }
        
        return all;
    }

}
