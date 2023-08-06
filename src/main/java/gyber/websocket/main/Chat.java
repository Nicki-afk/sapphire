package gyber.websocket.main;

import java.util.ArrayDeque;
import java.util.Queue;

import gyber.websocket.messageConfig.Message;

public class Chat {

    private Integer chatId;
    private String personOne;
    private String personTwo;


    private Queue<Message>messageQueue = new ArrayDeque<>();

    public Chat(){}

    public Chat(String personOne , String personTwo){
        this.personOne = personOne;
        this.personTwo = personTwo;
    }


    public void addAMessageToChat(Message message){
        this.messageQueue.add(message);

    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getPersonOne() {
        return personOne;
    }

    public void setPersonOne(String personOne) {
        this.personOne = personOne;
    }

    public String getPersonTwo() {
        return personTwo;
    }

    public void setPersonTwo(String personTwo) {
        this.personTwo = personTwo;
    }

    public Queue<Message> getMessageQueue() {
        return messageQueue;
    }

    public void setMessageQueue(Queue<Message> messageQueue) {
        this.messageQueue = messageQueue;
    }

    


    
}
