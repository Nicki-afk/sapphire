package gyber.websocket.ipfs;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

//import com.fasterxml.jackson.databind.ObjectMapper;

// import gyber.websocket.models.User;

@Service
public class IPFSGateway {

    @Value("${ipfs.url.add}")
    private  String saveUrlPath;

    @Value("${ipfs.url.get}")
    private static String getUrlPath;

    private RestTemplate connector = new RestTemplate();

    
    
    public Object sendToIPFS(File file){

        

        RestTemplate eRestTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(file));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        Object responseHash = eRestTemplate.postForObject(saveUrlPath , requestEntity, Object.class);

        return responseHash;
    }


    // Non tested
    // public User getUserDataByHash(String hash){
    //     byte[] arrByte = this.connector.getForObject(getUrlPath + hash, byte[].class);
    //     ObjectMapper objectMapper = new ObjectMapper();

    //     User parseUser = null;
    //     try{
    //          parseUser = objectMapper.readValue(new String(arrByte), User.class);
    //     }catch(Exception e){
    //         e.printStackTrace();

    //     }



    //     return parseUser;

    // }




    
}
