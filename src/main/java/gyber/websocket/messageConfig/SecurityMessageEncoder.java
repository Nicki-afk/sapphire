package gyber.websocket.messageConfig;

import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;


public class SecurityMessageEncoder implements Encoder.Text<SecurityMessage>{
    private static Gson gson = new Gson();

    @Override
    public void init(EndpointConfig config) {
        // TODO Auto-generated method stub

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
      
    }

    @Override
    public String encode(SecurityMessage object) throws EncodeException {
        return this.gson.toJson(object);
        
    }
    
}
