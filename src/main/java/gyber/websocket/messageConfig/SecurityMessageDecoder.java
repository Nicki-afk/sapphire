package gyber.websocket.messageConfig;


import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;



public class SecurityMessageDecoder implements Decoder.Text<SecurityMessage>{
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
    public SecurityMessage decode(String s) throws DecodeException {
        return this.gson.fromJson(s, SecurityMessage.class);
        
        
    }

    @Override
    public boolean willDecode(String s) {
        // TODO Auto-generated method stub
        return (s != null);
    }
    
}
