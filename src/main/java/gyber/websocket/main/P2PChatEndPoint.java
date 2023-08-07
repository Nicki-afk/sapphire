package gyber.websocket.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import gyber.websocket.messageConfig.Message;
import gyber.websocket.messageConfig.MessageDecoder;
import gyber.websocket.messageConfig.MessageEncoder;

@ServerEndpoint(value = "/chatfor/" , encoders = MessageEncoder.class , decoders = MessageDecoder.class)
public class P2PChatEndPoint  {
  //  private Chat chat;
    private Session session;

    // private List<Chat>chatsList = new ArrayList<Chat>();
    private Logger logger = Logger.getLogger("P2PChat");
    // private Map<String , Chat>sessionIdAndChat = new HashMap<>();
   //  private AtomicInteger atomicInteger = new AtomicInteger(0);
    private static final Set<P2PChatEndPoint> clients = new CopyOnWriteArraySet<>();
    private Map<String , Session>usernameAndUserSession = new HashMap<>();



    @OnOpen
    public void onOpen(Session session){
        String username = session.getRequestParameterMap().get("username").get(0);

        this.session = session;
        this.session.setMaxIdleTimeout(120000);

        this.clients.add(this);
        this.usernameAndUserSession.put(username, session);


    }


    @OnMessage
    public void onMessage(Session session , Message message){

            
    }




    public void send(Message message){

       
        



    }
    
}
