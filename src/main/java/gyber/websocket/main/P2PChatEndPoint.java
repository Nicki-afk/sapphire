package gyber.websocket.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import gyber.websocket.messageConfig.MessageDecoder;
import gyber.websocket.messageConfig.MessageEncoder;

@ServerEndpoint(value = "/chatfor/" , encoders = MessageEncoder.class , decoders = MessageDecoder.class)
public class P2PChatEndPoint  {
    private Chat chat;
    private Session session;

    private List<Chat>chatsList = new ArrayList<Chat>();
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private static final Set<P2PChatEndPoint> clients = new CopyOnWriteArraySet<>();



    @OnOpen
    public void onOpen(Session session){
        Map<String , List<String>>onOpenData = session.getRequestParameterMap();

        String personOne = onOpenData.get("personOne").get(0);
        String personTwo = onOpenData.get("personTwo").get(0);
        String chatId = onOpenData.get("chatId").get(0);

        session.setMaxIdleTimeout(120000);
        this.session = session;

        clients.add(this);


        if(chatId == null){
            this.chat = new Chat(atomicInteger.incrementAndGet() , personOne, personTwo);
            this.chatsList.add(this.chat);



        }


        


    }
    
}
