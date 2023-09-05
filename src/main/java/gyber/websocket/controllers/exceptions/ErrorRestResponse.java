package gyber.websocket.controllers.exceptions;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import gyber.websocket.models.User;
import gyber.websocket.security.authenticate.tokenManagement.TokenPairObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorRestResponse  {


    @JsonProperty("primary_erro_data")  private Map<String , Object>primapyErrorData = new HashMap<>();
    @JsonProperty("error_data_link")    private Map<String , Object>errorDataLink = new HashMap<>();


   

    public ErrorRestResponse(String message, User exceptionUserArgument,
            TokenPairObject exceptionTokenPairArgument , Exception e) {

        this.primapyErrorData.put("error_time", LocalDateTime.now());
        this.primapyErrorData.put("status_code", 401);
        this.primapyErrorData.put("discribe_message" , message);


        this.errorDataLink.put("user_exception_argument", exceptionUserArgument);
        this.errorDataLink.put("token_pair_exception_argument", exceptionTokenPairArgument);
        this.errorDataLink.put("short_stack_trace", Arrays.copyOf(e.getStackTrace(), 1));
    }


    public ErrorRestResponse(String message , Map<String , Object> errorDataLink , int statusCode){
        this.primapyErrorData.put("error_time", LocalDateTime.now());
        this.primapyErrorData.put("status_code", statusCode);
        this.primapyErrorData.put("discribe_message" , message);

        this.errorDataLink = errorDataLink;

    }

    public ErrorRestResponse(String message , Exception exception){
        internalServerError();

        this.errorDataLink.put("short_stack_trace" , Arrays.copyOf(exception.getStackTrace() , 2));
        this.errorDataLink.put("local_message", exception.getLocalizedMessage());
        

    }


    public ErrorRestResponse addPrimaryErrorData(String nameProperty , Object valueProperty){
        this.primapyErrorData.put(nameProperty, valueProperty);
        return this;
    }

    public ErrorRestResponse addErrorDataLink(String nameProperty , Object valueProperty){
        this.errorDataLink.put(nameProperty , valueProperty);
        return this;
    
    }

    public ErrorRestResponse internalServerError(){
        this.primapyErrorData.put("error_time", LocalDateTime.now());
        this.primapyErrorData.put("status_code", 500);
        this.primapyErrorData.put("discribe_message" , "An error occurred while executing the request on the server side");
        return this;
    }



    

    
    
}
