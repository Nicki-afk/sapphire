package gyber.websocket.messageConfig;

import java.sql.Date;

public class Message {
    private String from;
    private String to;
    private String content;
    private String serverPrefix;





    public Message(){}



    public Message(String from, String prefixTo, String content) {
        this.from = from;
        this.to = prefixTo;
        this.content = content;
    }

    

    



    public Message(String from, String to, String content, String serverPrefix) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.serverPrefix = serverPrefix;
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



    public String getServerPrefix() {
        return serverPrefix;
    }



    public void setServerPrefix(String serverPrefix) {
        this.serverPrefix = serverPrefix;
    }


    


    

    

    



}