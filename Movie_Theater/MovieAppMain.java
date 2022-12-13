/*
 * @authour: muteeba jamal <a href="mailto:muteeba.jamal@ucalgary.ca">
 * muteeba.jamal@ucalgary.ca</a>
 * @authour: shahzill naveed <a href="mailto:shahzill.naveed@ucalgary.ca">
 * shahzill.naveed@ucalgary.ca</a>
 * @authour: michele pham <a href="mailto:michele.pham@ucalgary.ca">
 * michele.pham@ucalgary.ca</a>
 * @authour: samira khan <a href="mailto:samira.khan@ucalgary.ca">
 * samira.khan@ucalgary.ca</a>
 * @version 1.0
 * @since 1.0
 */

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.*;

public class MovieAppMain{
    private static GUI gui;
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;
    private static MovieAppMain myJDBC;

    private static Connection dbConnect;
    private ResultSet results;

     // this is a linked list of all the movies in the database 
     private static LinkedList<Movie> movies = new LinkedList<Movie>();
     private static String movieFound = "no";
     // this is a linked list of all the theatres in the database 
     private static LinkedList<Theatre> theatres = new LinkedList<Theatre>();
     private static LinkedList<RegisteredUser> regUsers = new LinkedList<RegisteredUser>();
     ////////////////////////////////////////////////////////////////////
    /*
     * Start of Methods required to access and manipulate the SQL Databse. 
     */
    ////////////////////////////////////////////////////////////////////
    public MovieAppMain(String url, String user, String pw){
        // Database URL
        this.DBURL = url;

        //  Database credentials
        this.USERNAME = user;
        this.PASSWORD = pw;
    }

	//Must create a connection to the database, no arguments, no return value    
    public void initializeConnection(){
        try{
            dbConnect = DriverManager.getConnection(getDburl(),getUsername(),getPassword()); 
        }
        catch(SQLException e){
            e.printStackTrace();
        }             
    }

    String getUsername(){
        return this.USERNAME;
    }
    
    String getDburl(){
        return this.DBURL;
    }

    String getPassword(){
        return this.PASSWORD;
    }



    /* this method stores all our data from the database into the linked lists */
    public String storeData(String tableName){     

        StringBuilder comp = new  StringBuilder();         

        if(tableName.equals("projectData")){
            try{
                Statement statement = dbConnect.createStatement();  
                results = statement.executeQuery("SELECT Theatre, Movie, dateM, dayM, timeM, Seats, memberOnly, Item from projectData");
                int i = 0;
                boolean flag = false;
                while(results.next()){
                    // a movie object is created and added to the linked list
                    Movie mov = new Movie(results.getString("Item"), results.getString("Theatre"), results.getString("Movie"),
                                 results.getString("dateM"), results.getString("dayM"),
                                 results.getString("timeM"), results.getString("Seats"), results.getBoolean("memberOnly"));
                    movies.add(mov);
                    
                    // if its not the first item in the database then we will traverse through the linked list of theatres
                    if (i != 0){
                             
                        for (int x = 0; x<theatres.size(); x++) {
                            // if a theatre is already found to be in the linked list, then the movie object will be appended to it
                            // i did it this way because it makes sense to have multiple movie objects since each will have different timings but,
                            // it doesnt make sense to have multiple theatre objects --- 1 theatre has 0..* movies
                            if (theatres.get(x).getName().contains(results.getString("Theatre"))) {
                                theatres.get(x).addMovie(mov);
                                flag = true;
                                break;
                            }
                        }

                        // if the flag is false this means that the theatre isnt already in the list so we go ahead and add it
                        if (flag == false){
                            Theatre th = new Theatre(results.getString("Theatre"));
                            th.addMovie(mov);
                            theatres.add(th);
                        }
                    }

                    // this is for the first item in the database
                    else {
                        Theatre th = new Theatre(results.getString("Theatre"));
                        th.addMovie(mov);
                        theatres.add(th);
                    }

                    // flag is reset to false 
                    flag = false;

                    // set to 1 after the first item is added, so consecutive runs will go through the first if statement
                    i = 1;
                }
                statement.close();
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
            String str = comp.toString();
            return str.trim();
        }


        else if(tableName.equals("userData")){
            try{
                Statement statement = dbConnect.createStatement();  
                results = statement.executeQuery("SELECT Email, Password, CNumber, Cvv, Expiry, FeePaid from userData");
                int i = 0;
                boolean flag = false;
                while(results.next()){
                    // a movie object is created and added to the linked list
                    RegisteredUser reg = new RegisteredUser(new RegisteredPayment(),results.getString("Email"),results.getString("Password"),
                    results.getString("CNumber"), results.getString("Cvv"), results.getString("Expiry"), results.getBoolean("FeePaid"));
regUsers.add(reg);
                    
                }
                statement.close();
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
            String str = comp.toString();
            return str.trim();
        }


        else{
            String str = "Wrong input";
            return str;
        }
    }
    public static void registerUser(String email, String password, String cardNumber, String expiry, String cvv){
        try {
            int id = regUsers.size()+1;
            Statement stmt = dbConnect.createStatement();
            String str = "INSERT INTO userData " + 
                        //"VALUES (5,'shah@gmail.com', 'shah', '350519263647', '333', '06/25', 0)";
                        "VALUES ("+id+",'" + email +"','"+password+"','"+cardNumber+"','"+cvv+"','"+expiry+"',"+0+")";
            stmt.executeUpdate(str);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void addMovie(String Theatre, String Movie, String dateM, String dayM, String timeM, String seats, boolean exclusive){
        try {
            Statement stmt = dbConnect.createStatement();
            // String str = "INSERT INTO projectData (Theatre, Movie, dateM, dayM, timeM, Seats, memberOnly) VALUES ("+ id+", " +Theatre +", " +Movie +", "+dateM+","+dayM+","+timeM+","+seats+","+exclusive+");";
            int idEnter = movies.size()+1;
            int excl;
            if (exclusive) excl = 1;
            else excl = 0;
            String str = "INSERT INTO projectData VALUES ("+ idEnter+",' " +Theatre +"',' " +Movie +"',' "+dateM+"','"+dayM+"','"+timeM+"','"+seats+"','"+excl+"')";
            stmt.executeUpdate(str);

            theatres.clear();
            movies.clear();
            System.out.println(myJDBC.storeData("projectData"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     }
    ////////////////////////////////////////////////////////////////////
    /*
     * End of Methods required to access and manipulate the SQL Databse. 
     */
    ////////////////////////////////////////////////////////////////////
    
    // this is the search function used to search for a movie
    /*public static String search(String movieName){
        System.out.println();
        String details = "Search results for "+ movieName;
        // traverses through the theatres list and searches each theatre for a movie
        // you can see how the display happens in the theatre/movie classes
        for(int i=0; i<theatres.size(); i++){
            details += theatres.get(i).searchMovies(movieName);
        }

        return details;
    }*/
    public static LinkedList<Theatre> getThreatres(){
        return theatres;
    }

    // this is the search function used to search for a movie
    public static LinkedList<Movie> search(String movieName, RegisteredUser exclusive){
        LinkedList<Movie> allFound = new LinkedList<Movie>();
        String details = "Search results for "+ movieName;
        // traverses through the theatres list and searches each theatre for a movie
        // you can see how the display happens in the theatre/movie classes
        
        int exists = 0;
        for(int i=0; i<theatres.size(); i++){
           if(exclusive == null){
            allFound.addAll(theatres.get(i).searchMovies(movieName, "guest"));
           }
           else{
            allFound.addAll(theatres.get(i).searchMovies(movieName, "registered"));
           }
           
        }

        if(allFound.size() == 0){
            details = "Movie not found. Please enter a valid movie name.";
            movieFound = "no";
        }
        else{
             
            movieFound = "yes";
        }

        // if(movieName == null){
        //     details = "Please enter a movie name.";
        // }
        // Theatre t = new Theatre(movieName);
        // t.searchMovies(details);
        // int exists = t.getExist();
        // System.out.println("Whats exists: " + exists);
        
        return allFound;
    }
    static LinkedList<Movie> getMovies(){
        return movies;
    }
    public static String getFoundMovie(){
        return movieFound;
    }
    public static String register(){
        String done =  "registered!!!";
        return done;       
    }
    public static Movie get(){
        return movies.get(1);
    }

    public static RegisteredUser userSearch(String email, String password){
        //System.out.println(email + " " + password);

        regUsers.clear();
        System.out.println(myJDBC.storeData("userData"));
        for(int i = 0; i < regUsers.size(); i++){

            if(regUsers.get(i).getEmail().equals(email) && regUsers.get(i).getPassword().equals(password)){
                return regUsers.get(i);
            }
            
        }
        return null;
    }
    public static boolean checkEmail(String email){
        regUsers.clear();
        System.out.println(myJDBC.storeData("userData"));
        for(int i = 0; i < regUsers.size(); i++){ 
            if(regUsers.get(i).getEmail().equals(email)){
                return false;
            }
        }
        return true;

    }

    public static void feePayment(String email){
        try {
            Statement stmt = dbConnect.createStatement();
            String str = "Update userData set FeePaid = 1 Where Email = '" + email + "'";
            stmt.executeUpdate(str);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String str = "Update userData set FeePaid = 1 Where Email = '" + email + "'";  
    }

    public static void main(String[] args) throws FileNotFoundException {
        GUI gui = new GUI();
        
        //Use the following account information: Username = user1, Password = ensf
        myJDBC = new MovieAppMain("jdbc:mysql://localhost:3306/data_cinema","user1","ensf");
        myJDBC.initializeConnection();

        System.out.println("------------------------------");
        System.out.println();
        
       // Ticket.emailTicket("muteeba", "test", "String details");
        System.out.println(myJDBC.storeData("projectData"));
        System.out.println(myJDBC.storeData("userData"));
             
         GUI.run();
    }
}