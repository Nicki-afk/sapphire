package gyber.websocket.messageConfig;

public class Message {
    private String from;
    private String prefixTo;
    private String content;




    public Message(){}



    public Message(String from, String prefixTo, String content) {
        this.from = from;
        this.prefixTo = prefixTo;
        this.content = content;
    }


    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getPrefixTo() {
        return prefixTo;
    }
    public void setPrefixTo(String prefixTo) {
        this.prefixTo = prefixTo;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }



}