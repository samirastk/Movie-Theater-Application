import java.util.LinkedList;

public class Movie {
 
    private String name;
    private String time;
    private LinkedList<Ticket> tickets = new LinkedList<Ticket>();
    private Seats seats;
    private String theatre;
    private String date;
    private String day;
    private int id;
    private int tickNumber = 0;
    private boolean exclusive;
    
    Movie(String id, String Theatre, String Movie, String dateM, String dayM, String timeM, String seats, boolean exclusive){
        this.id = Integer.parseInt(id);
        theatre = Theatre;
        this.name = Movie;
        this.date = dateM;
        this.day = dayM;
        this.time = timeM;
        this.seats = new Seats(Integer.parseInt(seats));
        this.exclusive = exclusive;
        
    }

    public String getName(){
        return name;
    }  
    
    public String getDate(){
        return date;
    }

    public boolean getExclusive(){
        return exclusive;
    }

    public String getDay(){
        return day;
    }

    public String getTime(){
        return time;
    }
    
    public String details(){
        String details = "\nid: "+ id +" showing at "+theatre;
        details +="\ndate: " + date;
        details +="\ntime: " + time;
        details +="\n";
        return details;
    }


    public void decreaseSeats(String[] n){
         addTickets(n);
        seats.decrease(n);
    }

    public void increaseSeats(String n){
        seats.increase(n);
    }

    public Seats getSeats(){
        return seats;
    }

    public void addTickets(String[] n){

        for(int i = 0 ; i < n.length; i++){
            Ticket newT = new Ticket(n[i]);
            tickets.add(newT);
        }
    }

    public String getTicketDetails(String n){
        
        String ticketDetails ="";
       
        for(int i = 0 ; i < tickets.size(); i++){
            
            if(tickets.get(i).getSeat().equals(n)){
                ticketDetails += "Ticket for seat "+n;
                ticketDetails += " has code: "+ tickets.get(i).getCode()+"\n";
            }
        }

        return ticketDetails;
    }

    public String getTheatre(){
        return theatre;
    }
    public String removeTickets(String code){
        for(int i = 0 ; i < tickets.size(); i++){
            if(tickets.get(i).getCode().equals(code)){
                increaseSeats(tickets.get(i).getSeat());
                String s ="Booking for "+tickets.get(i).getSeat()+" was cancelled";
                tickets.remove(i);
                return s;
            }
        }
       return "Incorrect code!";
    }


    
}
