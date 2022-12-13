import java.nio.charset.Charset;
import java.util.Random;
import java.lang.Math.*;
import java.util.*;
// import javax.mail.*;
// import javax.mail.internet.*;
// import javax.activation.*;

public class Ticket {
    private String code;
    private String seat;

    Ticket(String s){
        //new String(array, Charset.forName("UTF-8"));
        seat = s;
        createCode();
    }

    public void createCode(){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder s = new StringBuilder(5);
        for (int i =0; i<5;i++){
            int letter = (int)(letters.length()* Math.random());

            s.append(letters.charAt(letter));
        }
        code = s.toString();
    }

    public String getCode(){
        return code;
    }

    public String getSeat(){
        return seat;
    }

    // public static void emailTicket(String t, String movie, String details){
    //     String host="smtp.gmail.com";  
    //     final String user="ensf480project@gmail.com";//change accordingly  
    //     final String password="ensf480!";//change accordingly  
            
    //     String to="muteeba.jamal02@gmail.com";//change accordingly  
        
    //     //Get the session object  
    //     Properties props = new Properties();  
    //     props.put("mail.smtp.host",host);  
    //     props.put("mail.smtp.auth", "true");  
            
    //     Session session = Session.getDefaultInstance(props,  
    //     new javax.mail.Authenticator() {  
    //     protected PasswordAuthentication getPasswordAuthentication() {  
    //     return new PasswordAuthentication(user,password);  
    //     }  
    //     });  
  

    // //compose the message  
    //     try{  
    //     MimeMessage message = new MimeMessage(session);  
    //     message.setFrom(new InternetAddress(user));  
    //     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
    //     message.setSubject("Booking confirmation");  
    //     message.setText("Hello, this is booking confirmation for: "+movie+". Info: "+ details+". Thank you!");  

    //     // Send message  
    //     Transport.send(message);  
    //  //   System.out.println("message sent successfully....");  

    //     }catch (MessagingException mex) {mex.printStackTrace();}  
    // }  

    
}
