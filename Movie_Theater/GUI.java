/*
 * @authour: muteeba jamal <a href="mailto:muteeba.jamal@ucalgary.ca">
 * muteeba.jamal@ucalgary.ca</a>
 * @authour: shahzill naveed <a href="mailto:shahzill.naveed@ucalgary.ca">
 * shahzill.naveed@ucalgary.ca</a>
 * @authour: michele phan zia <a href="mailto:----@ucalgary.ca">
 * ----@ucalgary.ca</a>
 * @authour: samira khan <a href="mailto:-----@ucalgary.ca">
 * ----@ucalgary.ca</a>
 * @version 1.4
 * @since 1.0
 */
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.Flow;
import java.util.*;
import java.awt.FlowLayout;


public class GUI extends JFrame implements ActionListener{
    
	// variables required for implementation
    private String movieSearch;
    private Guest user1;
    private Admin appAdmin = Admin.getOnlyAdmin();
	
	// variables required for the GUI
    private JLabel instructions;
    private JLabel instructions1;
    

    private javax.swing.JPanel jPanel1;


    private JTextField email;   // for login
    private JTextField pw;

    private JTextField emailreg;       // for registering
    private JTextField pwreg;
    private JTextField cardno;
    private JTextField cvv;
    private JTextField expiry;
    private JTextField searchV;

    private JButton register;
    private JButton login;
    private JButton guest;
    private JButton admin;

    private JButton book;

    private RegisteredUser userChecker;
  
  //  private JFrame paymentScreen = new JFrame();
    JButton pay;
    
    /*
     * GUI() Constructor
     */ 
    public GUI(){
        super("Group 4 Project");
        setupGUI();
        setSize(400,260);

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
    
    /*
     * setupGUI() Method
     * 
     * Sets up the GUI for users to interact with.
     */ 
     public void setupGUI(){
        jPanel1 = new javax.swing.JPanel();
        jPanel1.setBackground(new java.awt.Color(117, 19, 8));
        jPanel1.setForeground(new java.awt.Color(255,255,255));
        
        this.setBackground(new java.awt.Color(117, 19, 8));

        instructions = new JLabel("Group 4 Project - ENSF480");
        instructions.setFont(new java.awt.Font("Segoe UI", 0, 17)); 
        instructions.setForeground(Color.WHITE);
        instructions.setForeground(Color.WHITE);
        instructions.setBackground(new java.awt.Color(117, 19, 8));

        instructions1 = new JLabel("\n Movie Booking" );
        instructions1.setFont(new java.awt.Font("Segoe UI", 0, 17));
        instructions1.setForeground(Color.WHITE);

        instructions.setBackground(new java.awt.Color(117, 19, 8));
        
        register = new JButton("Sign up here");
        register.addActionListener(this);

        login = new JButton("Login here");
        login.addActionListener(this);

        guest = new JButton("Continue as guest");
        guest.addActionListener(this);

        admin = new JButton("Admin Portal");
        admin.addActionListener(this);

    
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        headerPanel.setBackground(new java.awt.Color(117, 19, 8));

        
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new GridBagLayout());
        clientPanel.setBackground(new java.awt.Color(117, 19, 8));


        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());
        submitPanel.setBackground(new java.awt.Color(117, 19, 8));


        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new FlowLayout());
        exitPanel.setBackground(new java.awt.Color(117, 19, 8));

        
        headerPanel.add(instructions);
        headerPanel.add(instructions1);
        
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets= new Insets(2,0,0,0);

        clientPanel.add(guest, gbc);
        gbc.gridy = 1;
        clientPanel.add(login, gbc);
        gbc.gridy = 2;
        clientPanel.add(register, gbc);
        gbc.gridy = 3;
        gbc.insets= new Insets(2,0,40,0);
        clientPanel.add(admin, gbc);

        

        
        this.setLocationRelativeTo(null);

        this.add(headerPanel, BorderLayout.CENTER);
        this.add(clientPanel, BorderLayout.SOUTH);
        this.add(exitPanel, BorderLayout.EAST);
       
    }
    
    /*
     * actionPerformed(ActionEvent event) Method
     * 
     * for the buttons on the main page
     */ 
    public void actionPerformed(ActionEvent event){
        
        if(event.getSource().equals(guest)){
         //  userChecker = null;
            user1 = new Guest(new GuestPayment());
            searchScreen();

           // searchScreen();
          //  SearchMovie.run();
           // dispose();
        }

        if(event.getSource().equals(register)){
            registerAccount();
        }

        if(event.getSource().equals(login)){
            validateLogin();
        }

        if(event.getSource().equals(admin)){
            validateAdmin();
        }

    }

    public void error(String message){
        JPanel myPanel = new JPanel();
        JOptionPane.showMessageDialog(myPanel, message);

    }
    
    public void validateAdmin(){
        this.setVisible(false);
        JTextField em = new JTextField(10);
        JTextField p = new JTextField(10);
    
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Email"));
        myPanel.add(em);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Password"));
        myPanel.add(p);
    
        // var enteredEmail = JOptionPane.showInputDialog("Please enter your email: ");
         var userName = "";   

        // var enteredPW = JOptionPane.showInputDialog("Please enter your password: ");
         var userPW = "";
        
        int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Please Enter Username and Password", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            userName = em.getText();
            userPW = p.getText();
            
            if (userName.equals(appAdmin.getUsername()) && userPW.equals(appAdmin.getPW())){
                adminPortal();
            }
            else{
                error("Incorrect Credentials");
                showMainScreen();
            }
        }
        
        else if (result == JOptionPane.NO_OPTION) {showMainScreen();} 
        else {showMainScreen();}
       
    }

    public void adminPortal(){
        JDialog adminScreen = new JDialog(this, "Admin Portal - WIP");
        adminScreen.setLayout(new BorderLayout());
        JPanel content = new JPanel(new GridLayout(10,2));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets= new Insets(5,0,0,0);

        JLabel mainTxt = new JLabel("Fill in to add a movie:)");
        mainTxt.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        mainTxt.setForeground(Color.WHITE);

        JTextField id =  new JTextField(10);
        JTextField theatre =  new JTextField(10);
        JTextField name =  new JTextField(10);
        JTextField date = new JTextField(10);
        JTextField day =  new JTextField(10);
        JTextField time =  new JTextField(10);
        JTextField seats =  new JTextField(10);
        JTextField exclusive =  new JTextField(10);


        JLabel th = new JLabel("Theatre");
        th.setForeground(Color.WHITE);

        JLabel n =  new JLabel("Movie Name");
        n.setForeground(Color.WHITE);

        JLabel d = new JLabel("Date");
        d.setForeground(Color.WHITE);

        JLabel da =  new JLabel("Day");
        da.setForeground(Color.WHITE);

        JLabel ti =  new JLabel("Time");
        ti.setForeground(Color.WHITE);

        JLabel s =  new JLabel("Seats");
        s.setForeground(Color.WHITE);

        JLabel e =  new JLabel("Exclusive? (y/n)");
        e.setForeground(Color.WHITE);


        JButton addMov = new JButton("Add Movie");
        addMov.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if( !theatre.getText().matches(".{1,100}") || !date.getText().matches(".{1,100}") ||
                !day.getText().matches(".{1,100}") || ! time.getText().matches(".{1,100}") || !seats.getText().matches("[1-9]+") || 
                !exclusive.getText().matches("y|n") 
                
                ) {
                    error("Invalid input, movie wasn't added:(");
                }

                else{
                    boolean excl = false;
                    if (exclusive.getText().equals("yes")){excl = true;}
                    
                    appAdmin.addMovie( theatre.getText(), theatre.getText(), date.getText(), day.getText(), time.getText(), seats.getText(), excl);
                    JOptionPane.showMessageDialog(adminScreen,  "Successfully added movie: "+name.getText());
                }
            }
        });
        
        content.add(mainTxt);
        content.add(addMov);

        content.add(th);
        content.add(theatre);
        content.add(n);
        content.add(name);
        content.add(d);
        content.add(date);
        content.add(da);
        content.add(day);
        content.add(ti);
        content.add(time);
        content.add(s);
        content.add(seats);
        content.add(e);
        content.add(exclusive);

        content.setBackground(new java.awt.Color(117,19,8));

        adminScreen.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                showMainScreen();
            }
        });

        adminScreen.add(content, BorderLayout.CENTER);
        adminScreen.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        adminScreen.setSize(500,600);
        adminScreen.setLocationRelativeTo(null);
       

        adminScreen.setVisible(true);

    }

    public void registerAccount(){
        this.setVisible(false);
        emailreg = new JTextField(10);
        pwreg = new JTextField(10);
        cvv = new JTextField(10);
        cardno = new JTextField(10);
        expiry = new JTextField(10);

    
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Email"));
        myPanel.add(emailreg);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Password"));
        myPanel.add(pwreg);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Card Number"));
        myPanel.add(cardno);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Expiry"));
        myPanel.add(expiry);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("CVV"));
        myPanel.add(cvv);

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
        "Please enter the following details", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            var enteredEmail = emailreg.getText();
            var enteredPW = pwreg.getText();
            var enteredCardNumber = cardno.getText();
            var enteredExpiry = expiry.getText();
            var enteredCVV = cvv.getText();
            
            boolean emailCheck = MovieAppMain.checkEmail(enteredEmail);

            if(!enteredCardNumber.matches("[0-9]{5,100}") || !enteredExpiry.matches("[0-9]+") || !enteredCVV.matches("[0-9]+") ) {
                error("Invalid Input!");
            }
            else{
                if(emailCheck){
                    MovieAppMain.registerUser(enteredEmail, enteredPW, enteredCardNumber, enteredExpiry, enteredCVV);

                    RegisteredUser regUser = MovieAppMain.userSearch(enteredEmail, enteredPW);
                    user1 = regUser;
                    
                    userPortal(regUser);
                }
                else{
                    error("The email already has an account associated");
                    showMainScreen();
                }
            }
        }
        
        else if (result == JOptionPane.NO_OPTION) { showMainScreen();} 
        else { showMainScreen(); }
       
    }
 
    public void validateLogin(){
        this.setVisible(false);
        email = new JTextField(10);
        pw = new JTextField(10);
    
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Email"));
        myPanel.add(email);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Password"));
        myPanel.add(pw);
    
        // var enteredEmail = JOptionPane.showInputDialog("Please enter your email: ");
         var userEmail = "enteredEmail";   

        // var enteredPW = JOptionPane.showInputDialog("Please enter your password: ");
         var userPW = "enteredPW";
        
        int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Please Enter Email and Password", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            userEmail = email.getText();
            userPW = pw.getText();
            
            // checking datbase
            userChecker = MovieAppMain.userSearch(userEmail, userPW);

            if (userChecker != null){
                user1 = userChecker;
                userPortal(userChecker);
            }
            else{
                isNotAUser();
                showMainScreen();
            }
        }
        
        else if (result == JOptionPane.NO_OPTION) {showMainScreen();} 
        else {showMainScreen();}
       
    }

    public void isNotAUser(){
        JDialog failureTab = new JDialog(this, "Login Failed");

        JLabel i = new JLabel("Your credentials do not match our records. Please try again or register as a new user");
        i.setFont(new java.awt.Font("Segoe UI", 0, 13)); 
        i.setForeground(Color.WHITE);

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.add(i);
        top.setBackground(new java.awt.Color(117,19,8));
        
        failureTab.setBackground(new java.awt.Color(117,19,8));
       
        failureTab.add(top, BorderLayout.CENTER);
        failureTab.setSize(600,100);
        failureTab.setVisible(true);

    }

    public void userPortal(RegisteredUser user){
        
        JDialog userPortal = new JDialog(this, "User Portal");
        userPortal.setLayout((new BorderLayout()));
        
        JPanel top = new JPanel();
        top.setBackground(new java.awt.Color(117,19,8));
        JLabel title = new JLabel("Welcome to your user portal!");
        title.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        top.add(title);

        JLabel email = new JLabel("Email Address: " + user.getEmail());
        email.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        email.setForeground(Color.WHITE);

        JLabel card = new JLabel("Card Number: " + user.getCardNumber().substring(0,4)+"*********");
        card.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        card.setForeground(Color.WHITE);

        JLabel feeP = new JLabel("Membership Paid: " + user.getFeePaid());
        feeP.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        feeP.setForeground(Color.WHITE);

        JLabel movies = new JLabel(user.getMovies());
        movies.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        movies.setForeground(Color.WHITE);

        JButton continueNoPay =  new JButton("Browse without paying");
        JButton continuePay =  new JButton("Pay and Browse");
        JButton cont =  new JButton("Browse Movies");
        
        continueNoPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchScreen();
            }
        });

        continuePay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.annualFee();
                searchScreen();
            }
        });
        cont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchScreen();
            }
        });

        

        JPanel userInfo = new JPanel();
        userInfo.setLayout(new GridBagLayout());
        userInfo.setBackground(new java.awt.Color(117,19,8));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets= new Insets(10,0,0,0);
        userInfo.add(email, gbc);
        gbc.gridy = 1;
        userInfo.add(card, gbc);
        gbc.gridy = 2;
        userInfo.add(feeP, gbc);
        gbc.gridy = 3;
        userInfo.add(movies, gbc);


        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.PAGE_AXIS));
        bottom.setBackground(new java.awt.Color(117,19,8));

        if(!user.getFeePaid()){
            bottom.add(continueNoPay);
            bottom.add(continuePay);
        }
        else{
            bottom.add(cont);
        }
        
        userPortal.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                showMainScreen();
            }
        });

        userPortal.add(top, BorderLayout.NORTH);
        userPortal.add(userInfo, BorderLayout.CENTER);
        userPortal.add(bottom, BorderLayout.SOUTH);
        
        userPortal.setTitle("User Portal");
        userPortal.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        userPortal.setSize(600,600);
        userPortal.setMinimumSize(new Dimension(450,450));
        userPortal.setLocationRelativeTo(null);
        userPortal.setVisible(true);
    }
   
    public void searchScreen(){
        this.setVisible(false);
        JLabel i;

        if(userChecker == null){

            i = new JLabel("Logged in a guest. Please search a movie");
            i.setFont(new java.awt.Font("Segoe UI", 0, 17)); 
            i.setForeground(Color.WHITE);

        }
        else{
           i = new JLabel("Login was successful. Please search a movie");
            i.setFont(new java.awt.Font("Segoe UI", 0, 17)); 
            i.setForeground(Color.WHITE);

        }
        JDialog searchScreen = new JDialog(this, "Search");
        searchScreen.setLayout(new BorderLayout());
        

        JLabel s = new JLabel("Search a movie:");
        s.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
     
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(i);
        topPanel.add(s);
        topPanel.setBackground(new java.awt.Color(117,19,8));
       
        searchV = new JTextField("", 15);

        JButton searchBtn = new JButton("Search");
        searchBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                searchScreen.setVisible(false);
                searchMovie();
            }
        });
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1,0));
        searchPanel.add(searchV);
        searchPanel.add(searchBtn);
        searchPanel.setBackground(new java.awt.Color(117,19,8));

        JPanel centre = new JPanel();
  
        centre.setBackground(new java.awt.Color(117,19,8));
        centre.setForeground(new java.awt.Color(255,255,255));
        centre.setLayout(new GridBagLayout());

        JLabel mov1 = new JLabel("Smile", JLabel.CENTER);
        mov1.setForeground(Color.WHITE);
        JLabel mov2 = new JLabel("Black Adam", JLabel.CENTER);
        mov2.setForeground(Color.WHITE);

        JLabel mov3 = new JLabel("Exclusively for members:", JLabel.CENTER);
        mov3.setForeground(Color.WHITE);
        JLabel mov4 = new JLabel("Wakanda Forever", JLabel.CENTER);
        mov4.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets= new Insets(5,0,0,0);
       
        if(userChecker == null){
            centre.add(mov1, gbc);
            gbc.gridy = 1;      
            centre.add(mov2, gbc);
            gbc.gridy = 3;
        }
        else{
            centre.add(mov1, gbc);
            gbc.gridy = 1;      
            centre.add(mov2, gbc);
            gbc.gridy = 2;   

            centre.add(mov3, gbc);
            gbc.gridy = 3;
            centre.add(mov4, gbc);
            gbc.gridy = 4; 
        }

        gbc.insets= new Insets(10,0,0,0);
        centre.add(searchPanel, gbc);

        searchScreen.add(topPanel, BorderLayout.NORTH);
        searchScreen.add(centre, BorderLayout.CENTER);
      
        
        searchScreen.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        searchScreen.setSize(400,300);
        searchScreen.setLocationRelativeTo(null);
       
        searchScreen.addWindowListener(new WindowAdapter() 
        {
        public void windowClosing(WindowEvent e)
        {
            if(userChecker==null)
            showMainScreen();
            else{userPortal(userChecker);}
        }});

        searchScreen.setVisible(true);
    }

    public void showMainScreen(){
        this.setVisible(true);
    }

    public void searchMovie(){
        movieSearch = searchV.getText();  
            LinkedList<Movie> movies = MovieAppMain.search(movieSearch, userChecker);
            // MovieAppMain.getMovies();
          //  int check = 0;
            String check = MovieAppMain.getFoundMovie();
            Movie selected;

            if(check.equals("yes") && movieSearch.length() != 0){

            LinkedList<Movie> Theatres = MovieAppMain.search(movieSearch, userChecker);

            
            LinkedList<String> thea = new LinkedList<String>();
                for (int k = 0; k < Theatres.size();  k ++) {
                    if(!thea.contains(Theatres.get(k).getTheatre())){
                        thea.add(Theatres.get(k).getTheatre());
                    }
                }




        String[] arrayTh = thea.toArray(new String[thea.size()]);

        Object th;
        th = JOptionPane.showInputDialog(null, "Please select a Theatre.", 
                    "Theatres", JOptionPane.QUESTION_MESSAGE, null, arrayTh, arrayTh[0]);
    
      if(th==null){
        searchScreen();
        return;
      }
            
            Object d;
            Object t;

            LinkedList<String> dates = new LinkedList<String>();
            for (int i = 0; i < Theatres.size(); i ++) {

                if(Theatres.get(i).getTheatre().equals(th)){
                    if(!dates.contains(Theatres.get(i).getDate()))
                    dates.add(Theatres.get(i).getDate());
                }
            
            }

            String[] arrayD = dates.toArray(new String[dates.size()]);

            d = JOptionPane.showInputDialog(null, "Please select a date.", 
                "Dates", JOptionPane.QUESTION_MESSAGE, null, arrayD, arrayD[0]);

                if(d==null){
                    searchScreen();
                    return;
                  }

                LinkedList<String> times = new LinkedList<String>();
                for (int i = 0; i < Theatres.size(); i ++) {

                    if(Theatres.get(i).getTheatre().equals(th) && Theatres.get(i).getDate().equals(d)){
                        if(!times.contains(Theatres.get(i).getDate()))
                        times.add(Theatres.get(i).getTime());
                    }
                
                }

                String[] arrayt = times.toArray(new String[dates.size()]);

                t = JOptionPane.showInputDialog(null, "Please select a time.", 
                    "Times", JOptionPane.QUESTION_MESSAGE, null, arrayt, arrayt[0]);
                    if(t==null){
                        searchScreen();
                        return;
                      }

                    for (int i = 0; i < Theatres.size(); i ++) {
                        if (d.equals(Theatres.get(i).getDate()) && t.equals(Theatres.get(i).getTime())){
                            selected = movies.get(i);
                            options(selected);
                            
                            break;
    
                        }
                    }
    
            }
            else{
                JOptionPane.showMessageDialog(this,  "Movie not found!");
                searchScreen();
            }
    }

    public void options(Movie selected){
        JDialog optionsScreen = new JDialog(this, "Movie Options");

        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        top.setBackground(new java.awt.Color(117,19,8));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets= new Insets(5,0,0,0);

        JLabel movie = new JLabel(selected.getName());
        movie.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 20)); 
        movie.setForeground(Color.WHITE);
        
        JLabel date = new JLabel("showing on "+selected.getDate());
        date.setFont(new java.awt.Font("Segoe UI",  Font.BOLD, 20)); 
        date.setForeground(Color.WHITE);

        JLabel time = new JLabel("at " +selected.getTime());
        time.setFont(new java.awt.Font("Segoe UI",  Font.BOLD, 20)); 
        time.setForeground(Color.WHITE);

        top.add(movie, gbc);
        gbc.gridy = 1;
        top.add(date, gbc);
        gbc.gridy = 2;
        gbc.insets= new Insets(5,0,40,0);
        top.add(time, gbc);


        JPanel center = new JPanel();
        center.setLayout(new FlowLayout(FlowLayout.CENTER));
        center.setBackground(new java.awt.Color(117,19,8));

        JLabel i = new JLabel("Please select an option :)");
        i.setFont(new java.awt.Font("Segoe UI", 0, 14)); 
        i.setForeground(Color.WHITE);
    
        
        JButton book = new JButton("Make Booking");
        book.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                seatScreen(selected);
            }
        });

        JButton cancel = new JButton("Cancel Booking");
        cancel.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
              cancelScreen(selected);
            }
        });
        JButton newSearch = new JButton("New Search");
        newSearch.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                searchScreen();;
            }
        });

        center.add(book);
        center.add(cancel);
        center.add(newSearch);

        optionsScreen.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e)
            {
                optionsScreen.dispose();
                showMainScreen();
            }
        });

        

        optionsScreen.add(top, BorderLayout.NORTH);
        optionsScreen.add(center, BorderLayout.CENTER);
        optionsScreen.setSize(500,250);
        optionsScreen.setLocationRelativeTo(null);
        optionsScreen.setVisible(true);

    }
   
    public void cancelScreen(Movie selected){
       
        JDialog cancelScreen = new JDialog(this, "Ticket Cancellation");
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));

        JPanel top = new JPanel();
        top.setLayout(new GridBagLayout());
        top.setBackground(new java.awt.Color(117,19,8));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets= new Insets(5,0,0,0);

        JLabel movie = new JLabel(selected.getName());
        movie.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 20)); 
        movie.setForeground(Color.WHITE);
        
        JLabel date = new JLabel("showing on "+selected.getDate());
        date.setFont(new java.awt.Font("Segoe UI",  Font.BOLD, 20)); 
        date.setForeground(Color.WHITE);

        JLabel time = new JLabel("at " +selected.getTime());
        time.setFont(new java.awt.Font("Segoe UI",  Font.BOLD, 20)); 
        time.setForeground(Color.WHITE);

        top.add(movie, gbc);
        gbc.gridy = 1;
        top.add(date, gbc);
        gbc.gridy = 2;
        gbc.insets= new Insets(5,0,40,0);
        top.add(time, gbc);

        JLabel cancelCode = new JLabel("Please enter the cancellation code:");
        cancelCode.setForeground(Color.WHITE);
        JTextField cancelField = new JTextField("", 15);
       

        JButton cancelBtn = new JButton("Cancel Booking");
        cancelBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String seatCancelled = selected.removeTickets(cancelField.getText());
                user1.cancelPayment(cancelField.getText(), selected);
                JOptionPane.showMessageDialog(cancelScreen,  seatCancelled);
            }
        });

        content.setBackground( new java.awt.Color(117,19,8));

        content.add(cancelCode);
        content.add(cancelField);
        content.add(cancelBtn);
        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelScreen.add(top, BorderLayout.NORTH);
        cancelScreen.add(content,  BorderLayout.CENTER);
        cancelScreen.getContentPane().setBackground( new java.awt.Color(117,19,8));
        cancelScreen.setSize(400,300);
        cancelScreen.setVisible(true);
    }

    public void seatScreen(Movie selected){
        JDialog seatScreen = new JDialog(this, "Seat Map");
        seatScreen.setLayout((new FlowLayout()));

        JLabel screen = new JLabel("Screen");
        screen.setForeground(Color.WHITE); 
        JLabel screen1 = new JLabel("___________________________________________________________________");

        JPanel screenPanel = new JPanel();
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.LINE_AXIS));
        screenPanel.setBackground(new java.awt.Color(117,19,8));

        screenPanel.add(screen);
        screenPanel.add(screen1);

        JPanel seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(0,5,4,5));
        seatPanel.setBackground(new java.awt.Color(117,19,8));

        Map<String, Boolean> seats = selected.getSeats().getSeats();
        JButton btn = new JButton();
        JTextArea textArea = new JTextArea();

        for (Map.Entry<String, Boolean> s : seats.entrySet()) {
                btn = new JButton(s.getKey());
                btn.setOpaque(true);
                btn.setBackground(Color.BLACK);
                if (!s.getValue()) {btn.setEnabled(false);btn.setBackground(Color.GRAY);}
                btn.addActionListener(new ActionListener() {
            
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object o = e.getSource();
                        JButton b = null;
                        String buttonText = "";
        
                        if(o instanceof JButton)
                            b = (JButton)o;
        
                        if(b != null)
                            buttonText = b.getText();
                            b.setBackground(Color.GRAY);
                            b.setEnabled(false);
                            textArea.append(buttonText+ "\n");
                    }
                });
                seatPanel.add(btn);
        }
        
        JPanel submitPanel = new JPanel();
        submitPanel.setBackground(new java.awt.Color(117,19,8));

        book = new JButton("Book Seats");
        book.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                seatScreen.dispose();
                paymentScreen(textArea.getText(), selected);
            }
        });

        textArea.setEditable(false);
        submitPanel.add(textArea);
        submitPanel.add(book);
       
        JPanel vert = new JPanel();
        vert.setLayout(new GridBagLayout());
        vert.setBackground(new java.awt.Color(117,19,8));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets= new Insets(5,0,0,0);
        
        JPanel bottom = new JPanel();
        bottom.setBackground(new java.awt.Color(117,19,8));

        JLabel movieName = new JLabel(selected.getName());
        movieName.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 20));
        movieName.setForeground(Color.WHITE); 
        bottom.add(movieName);

        vert.add(seatPanel, gbc);
        gbc.gridy = 1;
        vert.add(submitPanel, gbc);
        gbc.gridy = 2;
        vert.add(bottom, gbc);
      

        seatScreen.add(screenPanel, BorderLayout.NORTH);
        seatScreen.add(vert, BorderLayout.CENTER);
    //    seatScreen.add(bottom, BorderLayout.SOUTH);
        seatScreen.getContentPane().setBackground( new java.awt.Color(117,19,8));
   
      //  seatScreen.add(seatPanel, BorderLayout.CENTER);
      //  seatScreen.add(submitPanel, BorderLayout.SOUTH);

        seatScreen.setTitle("Seat Chart");
        seatScreen.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        seatScreen.setSize(600,300);
        seatScreen.setMinimumSize(new Dimension(450,300));
        seatScreen.setLocationRelativeTo(null);
        seatScreen.setVisible(true);
    }

    public void paymentScreen(String seats, Movie movie){
        JDialog paymentTab = new JDialog(this, "Payment");
        
        JLabel title = new JLabel("Payment");
        title.setFont(new java.awt.Font("Segoe UI", 0, 17)); 
        title.setForeground(Color.WHITE);

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.add(title);

        JPanel content = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets= new Insets(5,0,0,0);
        
        JTextField ccInfo;

        JTextField email;
        
        JLabel mainTxt = new JLabel("Payment Information:");
        mainTxt.setFont(new java.awt.Font("Segoe UI", 0, 17)); 
        mainTxt.setForeground(Color.WHITE);


        pay = new JButton("Pay");
        pay.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                user1.makePayment(seats, movie);
                if(userChecker!= null) {userChecker.addMovie(movie, seats);}
               String tickDetails = user1.getTicketDetails(seats, movie);
               paymentTab.dispose();

               JOptionPane.showMessageDialog(paymentTab,  "Successfully processed transaction for: "+seats+"\n"+tickDetails);
            }
        });

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {  
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentTab.dispose();
                seatScreen(movie);
            }
        });

      //  GridBagConstraints c = new GridBagConstraints();
        if(userChecker==null){
            ccInfo  = new JTextField("ccinfo...", 15);
            ccInfo.setSize(20,100);

            email = new JTextField("email...", 15);
            email.setSize(20,100);
        }
        else{
            ccInfo  = new JTextField(userChecker.getCardNumber().substring(0, 4)+"*********", 15);
            ccInfo.setSize(20,100);
            ccInfo.setEditable(false);

            email = new JTextField(userChecker.getEmail(), 15);
            email.setSize(20,100);
            email.setEditable(false);
        }

        
        content.add(mainTxt, gbc);
        gbc.gridy =1;
        gbc.insets= new Insets(20,0,0,0);
        content.add(email, gbc);
        gbc.insets= new Insets(5,0,0,0);
        gbc.gridy =2;
        content.add(ccInfo, gbc);
        gbc.gridy = 3;
        content.add(pay, gbc);
        gbc.gridy = 4;
        content.add(cancel, gbc);
        content.setBackground( new java.awt.Color(117,19,8));


        paymentTab.add(top);
        paymentTab.add(content);
        //  paymentTab.add(email);
        paymentTab.setTitle("Payment");
        paymentTab.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        paymentTab.getContentPane().setBackground( new java.awt.Color(117,19,8));
        paymentTab.setSize(600,600);
        paymentTab.setMinimumSize(new Dimension(100,100));
        paymentTab.setLocationRelativeTo(null);
        paymentTab.setVisible(true);

    }

    
      
	/*
	 * run() Method
	 * 
	 * Runs the GUI.
	 */ 
    public static void run() { 
        GUI X = new GUI();
       // X.getContentPane().add(BorderLayout.NORTH, clientPanel);
       
        X.setVisible(true);
    }     
}