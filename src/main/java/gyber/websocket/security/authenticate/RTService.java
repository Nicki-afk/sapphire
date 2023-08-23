package gyber.websocket.security.authenticate;

import org.springframework.stereotype.Service;

import gyber.websocket.models.UserIPFSModel;

@Service
public class RTService implements TokenAuthenticate{

    @Override
    public String createToken(UserIPFSModel userIPFSModel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createToken'");
    }

    @Override
    public boolean validateToken(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateToken'");
    }
    
}
