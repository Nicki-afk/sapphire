package gyber.websocket.messageConfig;

import java.sql.Date;

public class Message {
    private Integer chatId;
    private String from;
    private String to;
    private String content;
    private Date date;

    // С помощью этого свойства можно отправлть сообщение серверу 
    private String serverPrefix;






    public Message(){}



    public Message(String from, String prefixTo, String content) {
        this.from = from;
        this.to = prefixTo;
        this.content = content;
    }

    




    public Message(Integer chatId, String from, String to, String content, Date date) {
        this.chatId = chatId;
        this.from = from;
        this.to = to;
        this.content = content;
        this.date = date;
    }



    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String prefixTo) {
        this.to = prefixTo;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }



    public Integer getChatId() {
        return chatId;
    }



    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }



    public Date getDate() {
        return date;
    }



    public void setDate(Date date) {
        this.date = date;
    }


    



    @Override
    public String toString() {
        return "Message [chatId=" + chatId + ", from=" + from + ", to=" + to + ", content=" + content + ", date=" + date
                + "]";
    }



    public String getServerPrefix() {
        return serverPrefix;
    }



    public void setServerPrefix(String serverPrefix) {
        this.serverPrefix = serverPrefix;
    }

    

    



}