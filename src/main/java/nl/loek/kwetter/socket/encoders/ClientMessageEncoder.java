package nl.loek.kwetter.socket.encoders;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import nl.loek.kwetter.socket.messages.ClientMessage;

public class ClientMessageEncoder implements Encoder.Text<ClientMessage> {

    @Override
    public void init(EndpointConfig ec) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(ClientMessage helloToAllMessage) throws EncodeException {
        StringWriter swriter = new StringWriter();
        try (JsonGenerator jsonGen = Json.createGenerator(swriter)) {
            jsonGen.writeStartObject()
                    .write("helloAll", helloToAllMessage.getMessage())
                    .writeEnd();
        }
        return swriter.toString();
    }
}
