public class Guest {
    protected Payment p;
   // private int movieCredit;

    Guest(Payment type){
        p = type;

    }

    void makePayment(String seatsToBook, Movie obj){
        p.makePayment(seatsToBook, obj);
    }

    void cancelPayment(String seatsToCancel, Movie obj){
        p.cancelPayment(seatsToCancel, obj);
    }

    String getTicketDetails(String n, Movie obj){
        String[] seatsArray = n.split("\n");
        String det="";
        
        for (String e : seatsArray){
            det+=obj.getTicketDetails(e);
        }
        
        return det;
        
    }


}