package gyber.websocket.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

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
    
    @OnOpen
    public void onOpen(Session session , @PathParam("username") String username) {
        System.out.println("Session open successful : ID :  " + session.getId());
        this.session = session;
        System.out.println("Query String : " + session.getQueryString());

        clients.add(this);                                       // добавляем экземпляр сессий в коллекцию 
        users.put(username, session);                    // Добавляем имя пользователя и его id сессий в мапу

        Message message = new Message();
        message.setFrom(username);
        message.setContent("Connect successful to server!"); // Отправляем ответ о том что подключение успешно 

        sendToCompanion(session , message);

    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(this);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("ERROR : " + throwable.getLocalizedMessage());
    }

    @OnMessage
    public void onMessage(Session session , Message message) {
        //System.out.println("Session ID " + session.getId());
    
        System.out.println("Server permit message : " + message);

      //  message.setFrom(users.get(session.getId()));             // указываем получателя

        sendToCompanion(this.users.get(message.getFrom()) , message);                       // отправлем сообщение 


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

}
