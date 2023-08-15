package gyber.websocket.ipfs;

import java.io.File;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class IPFSGateway {
    private String ipfsUrlAddress = "https://ipfs.infura.io:5001/api/v0/";
    
    
    public String saveUserData(File file){

        

        RestTemplate eRestTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(file));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        String responseHash = eRestTemplate.postForObject(ipfsUrlAddress + "add", requestEntity, String.class);



        return responseHash;
    }




    
}
