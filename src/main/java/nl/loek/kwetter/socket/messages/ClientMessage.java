package nl.loek.kwetter.socket.messages;

import java.io.Serializable;
import nl.loek.kwetter.model.User;
import nl.loek.kwetter.service.KwetterService;

public class ClientMessage extends Message implements Serializable{

    private String message;
    private String sender;
    private String title;

  
    
    private KwetterService kwetterService;

    public ClientMessage(String message , String sender, String title) {
        this.message = message;
        this.sender = sender;
        this.title =  title;
    }
    
      public String getTitle() {
        return title;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    /* For logging purposes */
    @Override
    public String toString() {
        return "[ClientMessage] " + "-" + message;
    }
}
