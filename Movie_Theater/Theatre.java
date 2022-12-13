import java.util.LinkedList;


public class Theatre {
    private String name;
    private int exists = 0;
    private LinkedList<Movie> movies = new LinkedList<Movie>();

    Theatre(String n){
       name = n;
    }

    void addMovie(Movie mov){
        movies.add(mov);
    }

    public String getName(){
        return name;
    }

    public void getMovies(){
        for(int i=0; i<movies.size(); i++){
        } 
    }

    public LinkedList<Movie> searchMovies(String search, String userStatus){
        String movDetails ="";
        exists = 0;
        LinkedList<Movie> found = new LinkedList<Movie>();
        for(int i=0; i<movies.size(); i++){
            exists = 1;

            if(userStatus.equals("registered")){
                if(movies.get(i).getName().contains(search)){
                
                    found.add(movies.get(i));
    
                }
            }
            else{
                if(movies.get(i).getName().contains(search) && movies.get(i).getExclusive() == false){
                
                    found.add(movies.get(i));
    
                }
            }
            
        }
        
        return found;
    }
    public int getExist(){
        return exists;
    }

}
