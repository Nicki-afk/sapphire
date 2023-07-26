package gyber.websocket.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Logger;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint( value = "/chat/{username}" , encoders = MessageEncoder.class , decoders = MessageDecoder.class)
public class ChatEndpoint {
    private Session session;
     private static final Set<ChatEndpoint> clients = new CopyOnWriteArraySet<>();
     private static Map<String,Session>users = new LinkedHashMap<>(); 


     private static Logger logger = Logger.getLogger("ChatEndPoint");

    
    @OnOpen
    public void onOpen(Session session , @PathParam("username") String username) {
        logger.info("INIT... NEW SEESSION ON " + username );

        this.session = session;
      
        logger.info("INIT SESSION SUCCESSFUL ! SESSION ID : " + session.getId());

        clients.add(this);                                       // добавляем экземпляр сессий в коллекцию 
        users.put(username, session);                           // Добавляем имя пользователя и его id сессий в мапу


    }

    @OnClose
    public void onClose(Session session) {

        logger.info("SESSION CLOSING INIT ... ");
        clients.remove(this);
        users.remove(session);

        logger.info("SESSIONS SIZE : " + users.size());
        logger.info("SESSION CLOSE SUCCESSFUL!");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {

        if(throwable != null){
           logger.warning("EXCEPTION IN WORK : " + throwable);
            logger.warning("FULL STACK TRACE : ");
            throwable.printStackTrace();
        }

    }

    @OnMessage
    public void onMessage(Session session , Message message) {
        

        logger.info("USER : ".concat(message.getTo()).concat("SEND MESSAGE TO -> " + message.getFrom()));
        logger.info("SEND MESSAGE ...");

        sendToCompanion(this.users.get(message.getFrom()) , message);                       // отправлем сообщение 

        logger.info("MESSAGE USER : " + message.getTo() + " TO USER : " + message.getFrom() + " SENT SUCCESSFUL");

    }

    private void sendMessageToAll(String message) {
    
    }


    

    private void sendToCompanion( Session session , Message message){
    

        try{

            try{

                session.getBasicRemote().sendObject(message);
               
            }catch(Exception e){

                e.printStackTrace();

                this.session.getBasicRemote().sendObject(new Message("SERVER" , message.getTo(), "ERROR User not found : " + message.getFrom()));

            }

            

            


        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    

}
