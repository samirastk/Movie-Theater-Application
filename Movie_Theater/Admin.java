import java.util.LinkedList;

public class Admin {
    private static Admin onlyAdmin;
    private String username ="";
    private String pw="";

    private Admin(String u, String p){
        username = u;
        pw = p;
    }

    public static Admin getOnlyAdmin(){
        if (onlyAdmin==null){
            onlyAdmin= new Admin("smsm", "480");
        }
        return onlyAdmin;
    }
    
    public String getUsername(){
        return username;
    }

    public String getPW(){
        return pw;
    }

    void addMovie( String Theatre, String Movie, String dateM, String dayM, String timeM, String seats, boolean exclusive){
        MovieAppMain.addMovie( Theatre,  Movie,  dateM,  dayM,  timeM,  seats,  exclusive);
    }
}