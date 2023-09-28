package gyber.sapphire.entities;

import java.security.PublicKey;
import java.util.Base64;

public class KeyExchangeSystemMessage extends Message {

    private String pubKey;
 //   private String encoder = "Base64";


    public KeyExchangeSystemMessage(){}

    public KeyExchangeSystemMessage(PublicKey publicKey , String fromUser , String toUser){
        this.pubKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        setFrom(fromUser);
        setTo(toUser);
        
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    
    
}
