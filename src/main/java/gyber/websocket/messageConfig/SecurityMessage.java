package gyber.websocket.messageConfig;

import java.security.PublicKey;

public class SecurityMessage extends Message{

    
    private PublicKey publicKey;


    public SecurityMessage(String from , String to , PublicKey publicKey){
        super(from, to, " ", "PUBLIC_KEY_EXCHANGE");
    
        this.publicKey = publicKey;
    }


    public PublicKey getPublicKey() {
        return publicKey;
    }


    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
    
    

}
