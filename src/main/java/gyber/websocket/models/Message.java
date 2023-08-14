package gyber.websocket.models;
import java.time.LocalDateTime;


public class Message {
    private Long id;
    private String from;
    private String to;
    private String content;
    private LocalDateTime dateSent;
    private LocalDateTime dateReceived;
    private Boolean isRead = false;
    private Boolean isDelivered = false;

 



    public Message(){}





    public Message(Long id, String from, String to, String content, LocalDateTime dateSent, LocalDateTime dateReceived,
            Boolean isRead) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.content = content;
        this.dateSent = dateSent;
        this.dateReceived = dateReceived;
        this.isRead = isRead;
    }





    public Long getId() {
        return id;
    }





    public void setId(Long id) {
        this.id = id;
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





    public void setTo(String to) {
        this.to = to;
    }





    public String getContent() {
        return content;
    }





    public void setContent(String content) {
        this.content = content;
    }





    public LocalDateTime getDateSent() {
        return dateSent;
    }





    public void setDateSent(LocalDateTime dateSent) {
        this.dateSent = dateSent;
    }





    public LocalDateTime getDateReceived() {
        return dateReceived;
    }





    public void setDateReceived(LocalDateTime dateReceived) {
        this.dateReceived = dateReceived;
    }





    public Boolean getIsRead() {
        return isRead;
    }





    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }





    public Boolean getIsDelivered() {
        return isDelivered;
    }





    public void setIsDelivered(Boolean isDelivered) {
        this.isDelivered = isDelivered;
    }





}