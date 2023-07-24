package gyber.websocket.main;

public class AuthorizedMessage extends Message{
        private String logInByName;


        public AuthorizedMessage(){}

        public AuthorizedMessage(String logInByName){
            super("Guest-Client", "Server" , logInByName);
        }


        public String getLogInByName() {
            return logInByName;
        }

        public void setLogInByName(String logInByName) {
            this.logInByName = logInByName;
        }


        

    
}
