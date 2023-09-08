package gyber.websocket.exceptions.exceptionHandlers;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import gyber.websocket.exceptions.BetaTestKeyException;
import gyber.websocket.exceptions.ErrorRestResponse;
import gyber.websocket.exceptions.TokenLocalStorageException;
import io.jsonwebtoken.JwtException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler{


    @ExceptionHandler(value = {TokenLocalStorageException.class })
    protected ResponseEntity<Object> handleTokenException(TokenLocalStorageException exception , WebRequest webRequest){
   
     ErrorRestResponse response = new ErrorRestResponse();
     response
     .simpleErrorPrimaryData("The error occurred as a result of working with tokens in the local storage of the server, see the error presented in the exceptions")
     .addErrorDataLink("short_stack_trace" , Arrays.copyOf(exception.getStackTrace(), 1))
     .addErrorDataLink("token_pair_exception_argument", exception.getTokenPairObject())
     .addErrorDataLink("user_exception_argument", exception.getUser());

      return ResponseEntity.badRequest().body(response);


    }

    @ExceptionHandler(value = {NullPointerException.class})
    protected ResponseEntity<ErrorRestResponse> handleNullPointerException(NullPointerException exception , WebRequest webRequest){
      ErrorRestResponse errorRestResponse = new ErrorRestResponse();
      errorRestResponse
      .simpleErrorPrimaryData("The server received invalid data. The data received by the server contains a null field, which is not allowed")
      .simpleErrorDataLink(exception);

      return ResponseEntity.badRequest().body(errorRestResponse);

    }

    public ResponseEntity<ErrorRestResponse> handleBetaTestKeyException(BetaTestKeyException betaTestKeyException , WebRequest webRequest){
      if(betaTestKeyException.getBetaTestKey() == null){

        ErrorRestResponse errorRestResponse = new ErrorRestResponse("Error when checking beta test key, read the exception for detailed error localization" , 401);
        errorRestResponse.addErrorDataLink("short_stack_trace", Arrays.copyOf(betaTestKeyException.getStackTrace(), 2));
        errorRestResponse.addErrorDataLink("local_message", betaTestKeyException.getLocalizedMessage());
        return ResponseEntity.status(401).body(errorRestResponse);

      
      }

      ErrorRestResponse errorRestResponse = new ErrorRestResponse("Error when checking beta test key, read the exception for detailed error localization" , 401);
      errorRestResponse.addErrorDataLink("short_stack_trace", Arrays.copyOf(betaTestKeyException.getStackTrace(), 2));
      errorRestResponse.addErrorDataLink("key_data", betaTestKeyException.getBetaTestKey());
      errorRestResponse.addErrorDataLink("local_message", betaTestKeyException.getLocalizedMessage());
      return ResponseEntity.status(401).body(errorRestResponse);



    }


    
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ErrorRestResponse> handleAnyException(Exception e , WebRequest webRequest){
      ErrorRestResponse errorRestResponse = new ErrorRestResponse("An error occurred while executing the request on the server side", e);
      return ResponseEntity.status(500).body(errorRestResponse);

    }



    
}
