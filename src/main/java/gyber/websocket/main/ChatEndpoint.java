package gyber.websocket.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint( value = "/chat/{username}")
public class ChatEndpoint {
    private Session session;
    // private static final Set<ChatEndpoint> clients = new CopyOnWriteArraySet<>();
    // private static Map<String,Integer>userLogins = new LinkedHashMap<>(); 
    
    private static Map<String , ChatEndpoint>clientMap = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Session open successful : ID :  " + session.getId());
        this.session = session;
        System.out.println("Query String : " + session.getQueryString());
        this.clientMap.put(session.getQueryString(), this);

    }

    @OnClose
    public void onClose(Session session) {
        clientMap.remove(this);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("ERROR : " + throwable.getLocalizedMessage());
    }

    @OnMessage
    public void onMessage(Message message) {
        //System.out.println("Session ID " + session.getId());
    
        System.out.println("Server permit message : " + message);


       // sendMessageToAll("Hello Server!");
    }

    private void sendMessageToAll(String message) {
        // System.out.println("sendMessageToAll()");
        // clients.forEach(endpoint -> {
        //     synchronized (endpoint) {
        //         try {
        //             endpoint.session.getBasicRemote().sendText(message);
        //         } catch (IOException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // });
    }


    private boolean loginUser(AuthorizedMessage authorizedMessage){

        

        return false;

    }

    private void sendToCompanion(Message message){
        // ...

    }

}
