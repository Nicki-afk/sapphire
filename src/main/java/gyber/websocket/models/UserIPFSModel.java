package gyber.websocket.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserIPFSModel {
    private Long id;
    private NetStatus onliNetStatus;
    private String hashUserFile;


    @JsonIgnore
    private User user;

    public UserIPFSModel(User user){
        this.user = user;
        this.id = user.getId();
        this.onliNetStatus = user.getOnlineStatus();
        // IPFS logic

    }


}
