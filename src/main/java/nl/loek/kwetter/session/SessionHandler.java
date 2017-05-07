/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.loek.kwetter.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Singleton;
import javax.websocket.Session;

/**
 *
 * @author Loek
 */
@Singleton
public class SessionHandler implements Serializable{
    
    private Map<String, Session> sessions = new HashMap();
     private static final Logger LOG = Logger.getLogger(SessionHandler.class.getName());

    public SessionHandler() {

    }

    public void addSession(String emailAddress, Session session) {
        Session s  = this.getSession(emailAddress);
        if(s == null){
              sessions.put(emailAddress, session);
        }
      
//        LOG.log(Level.INFO, "received session : {0}", emailAddress);
//        System.out.println("Starting session for: " + emailAddress + " with session-id: " + session.getId() + " total active sessions: " + sessions.size());
    }

    public void removeSession(Session session) {
        String emailAddress = "";
        String sessionId = session.getId();
        for (Map.Entry entry : sessions.entrySet()) {
            if (sessionId.equals(entry.getValue())) {
                emailAddress = entry.getKey().toString();
                break; 
            }
        }
        if (!emailAddress.isEmpty()) {
            sessions.remove(emailAddress);
            System.out.println("Removing session for: " + emailAddress + " with session-id: " + session.getId() + " total active sessions: " + sessions.size());
        }
    }

    public Session getSession(String emailAddress) {
        return sessions.get(emailAddress);
    }
}
