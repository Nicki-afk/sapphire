package gyber.websocket.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
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
        logger.info("INIT NEW P2P SESSION ...");
        String username = session.getRequestParameterMap().get("username").get(0);
        logger.info("USER CONNECT TO SERVER IN NICKNAME : " + username);


        this.session = session;
        this.session.setMaxIdleTimeout(120000);
        logger.info("P2P SESSION CONFIG SUCCESSFUL !");

        this.clients.add(this);
        this.usernameAndUserSession.put(username, session);
        logger.info("SESSION REGISTER SUCCESSFUL IN SERVER !");


    }


    @OnMessage
    public void onMessage(Session session , Message message){
        logger.info("NEW MESSAGE IN USER : " + message.getFrom());
        send(message);

            
    }


    @OnClose
    public void onClose(Session session){
        logger.info("SESSION CLOSING INIT ... ");
        clients.remove(this);
        this.usernameAndUserSession.remove(session);

        logger.info("SESSIONS SIZE : " + usernameAndUserSession.size());
        logger.info("SESSION CLOSE SUCCESSFUL!");

    }

    @OnError
    public void onError(Session session , Throwable wThrowable){

        if(wThrowable != null){
            logger.warning("EXCEPTION IN WORK : " + wThrowable);
             logger.warning("FULL STACK TRACE : ");
             wThrowable.printStackTrace();
         }

    }




    public void send(Message message){

        try{
            String to = message.getTo();
            this.usernameAndUserSession.get(to).getBasicRemote().sendObject(message);
          //  this.session.getBasicRemote().sendObject(message);


        }catch(Exception e){
            e.printStackTrace();

        }

    

    }
    
}
