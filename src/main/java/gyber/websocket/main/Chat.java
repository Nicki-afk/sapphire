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

    public Chat(Integer chatId , String personOne , String personTwo){
        this.chatId = chatId;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((chatId == null) ? 0 : chatId.hashCode());
        result = prime * result + ((personOne == null) ? 0 : personOne.hashCode());
        result = prime * result + ((personTwo == null) ? 0 : personTwo.hashCode());
        result = prime * result + ((messageQueue == null) ? 0 : messageQueue.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Chat other = (Chat) obj;
        if (chatId == null) {
            if (other.chatId != null)
                return false;
        } else if (!chatId.equals(other.chatId))
            return false;
        if (personOne == null) {
            if (other.personOne != null)
                return false;
        } else if (!personOne.equals(other.personOne))
            return false;
        if (personTwo == null) {
            if (other.personTwo != null)
                return false;
        } else if (!personTwo.equals(other.personTwo))
            return false;
        if (messageQueue == null) {
            if (other.messageQueue != null)
                return false;
        } else if (!messageQueue.equals(other.messageQueue))
            return false;
        return true;
    }

    

    


    
}
