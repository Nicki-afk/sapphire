package gyber.websocket.controllers.exceptions.exceptionHandlers;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import gyber.websocket.controllers.exceptions.TokenLocalStorageException;
import gyber.websocket.controllers.exceptions.ErrorRestResponse;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler{

    // @ExceptionHandler(value 
    //   = { IllegalArgumentException.class, IllegalStateException.class })
    // protected ResponseEntity<Object> handaleConflict(RuntimeException ex, WebRequest request){
    //     String errorMessage = "Error ";
    //      return handleExceptionInternal(ex, errorMessage, 
    //          new HttpHeaders(), HttpStatus.CONFLICT, request);

    // }


    @ExceptionHandler(value = {TokenLocalStorageException.class})
    protected ResponseEntity<Object> handleTokenException(TokenLocalStorageException exception , WebRequest webRequest){
   
     ErrorRestResponse response = new ErrorRestResponse();
     response
     .addPrimaryErrorData("error_time", LocalDateTime.now())
     .addPrimaryErrorData("status_code", 400)
     .addPrimaryErrorData("error_message", "The error occurred as a result of working with tokens in the local storage of the server, see the error presented in the exceptions")
     .addErrorDataLink("short_stack_trace" , Arrays.copyOf(exception.getStackTrace(), 1))
     .addErrorDataLink("token_pair_exception_argument", exception.getTokenPairObject())
     .addErrorDataLink("user_exception_argument", exception.getUser());

      return ResponseEntity.badRequest().body(response);


    }



    
}
