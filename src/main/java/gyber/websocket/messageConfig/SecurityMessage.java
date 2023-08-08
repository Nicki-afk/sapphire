package gyber.websocket.messageConfig;

import java.security.PublicKey;
import java.util.Base64;

public class SecurityMessage extends Message{

    
    private String pubKey;



    public SecurityMessage(String from , String to , PublicKey publicKey){
        super(from, to, " ", "PUBLIC_KEY_EXCHANGE");

        this.pubKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());

    }

    
   


    public String getPubKey() {
        return pubKey;
    }


    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }


  

    


    
    
    

}
