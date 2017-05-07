package nl.loek.kwetter.socket.encoders;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.DecodeException;
import javax.websocket.EncodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import com.google.gson.Gson;
import nl.loek.kwetter.socket.messages.ClientMessage;

public class ClientMessageDecoder implements Decoder.Text<ClientMessage> {

  private final Gson gson = new Gson();
    
    @Override
    public void init(EndpointConfig config) {

    }
    
    @Override
    public ClientMessage decode(String arg0) throws DecodeException {
        return gson.fromJson(arg0, ClientMessage.class);
    }

    @Override
    public boolean willDecode(String arg0) {
        return true;
    }


    @Override
    public void destroy() {

    }
    
}
