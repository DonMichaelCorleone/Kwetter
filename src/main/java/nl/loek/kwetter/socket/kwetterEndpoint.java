package nl.loek.kwetter.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import nl.loek.kwetter.model.Posting;
import nl.loek.kwetter.model.User;
import nl.loek.kwetter.service.KwetterService;
import nl.loek.kwetter.session.SessionHandler;
//import nl.loek.kwetter.session.SessionHandler;
import nl.loek.kwetter.socket.encoders.ClientMessageEncoder;
import nl.loek.kwetter.socket.messages.ClientMessage;
import nl.loek.kwetter.socket.encoders.ClientMessageDecoder;

@ServerEndpoint(
        value = "/atpendpoint",
        encoders = {ClientMessageEncoder.class},
        decoders = {ClientMessageDecoder.class}
)
public class kwetterEndpoint {

    private ObjectMapper mapper;
    @Inject
    private KwetterService kwetterService;
    @Inject
    private SessionHandler sessionHandler;
    private static String sessionInit = "InitializeSession";

    public kwetterEndpoint() {

//        sessionHandler = new SessionHandler();
        mapper = new ObjectMapper();
    }

    private static final Logger LOG = Logger.getLogger(kwetterEndpoint.class.getName());
    private static final Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void onMessage(final Session client, ClientMessage message) throws IOException {
        if (message != null) {
            String sender = message.getSender();
            String text = message.getMessage();
            String title = message.getTitle();
            
           
            sessionHandler.addSession(sender, client);

            LOG.log(Level.INFO, "received session : {0}", client);
            LOG.log(Level.INFO, "received message with text: {0}", text);
            LOG.log(Level.INFO, "received messasge with author: {0}", sender);
            LOG.log(Level.INFO, "received messasge with title: {0}", title);
            
            if(title != null){
                Posting p = new Posting(sender, title, text);
                kwetterService.createPosting(p);
                LOG.log(Level.INFO, "Saving Post : {0}", p.toString());
                User senderUser = this.kwetterService.findByUsername(sender);
                for(User follower : senderUser.getFollowers()){
                    Session s  =  sessionHandler.getSession(follower.getUserName());
                    if(s != null){
                         this.sendMessage(s, this.mapper.writeValueAsString(p));
                         LOG.log(Level.INFO, "Sending Post to Follower: {0}", follower.getUserName());
                    }                    
                }    
                this.sendMessage(client, this.mapper.writeValueAsString(p));
            }

        }

    }

    private void sendMessage(Session peer, Object send) {

        try {
            if (peer.isOpen()) {
                peer.getBasicRemote().sendObject(send);
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    @OnOpen
    public void onOpen(Session peer) {
//        sessionHandler.addSession(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        LOG.info("Connection closed ...");
        sessionHandler.removeSession(peer);
    }

    @OnError
    public void onError(Throwable t) {
        LOG.log(Level.INFO, "Foutje ...{0}", t.getMessage());
    }

    private void sent2All(Object answer) {
        for (Session p : peers) {
            sendMessage(p, answer);
        };
    }

}
