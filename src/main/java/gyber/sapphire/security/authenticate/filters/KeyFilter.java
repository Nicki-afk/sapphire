package gyber.sapphire.security.authenticate.filters;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gyber.sapphire.entities.User;
import gyber.sapphire.exceptions.BetaTestKeyException;
import gyber.sapphire.exceptions.ErrorRestResponse;
import gyber.sapphire.exceptions.TokenLocalStorageException;
import gyber.sapphire.security.authenticate.tokenManagement.TokenPairObject;
import gyber.sapphire.security.beta.BetaTestKeyManager;


@Service
public class KeyFilter extends CustomAbstractPerRequestFilter{


    @Autowired
    private BetaTestKeyManager keyManager;

    @Autowired 
    private ObjectMapper objMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

            if(thisURLCanBeUsedWithoutAFilter((request.getRequestURI()))){
                filterChain.doFilter(request, response);
                return;
            }

                
       

           String headerKeyParam = request.getHeader("Beta-key");

            if(headerKeyParam == null || headerKeyParam.isEmpty()){
                constructErrorResponse(
                    response,
                    "Your request does not contain a beta test key. The project is in the early stages of testing and a test key is required to access the server", 
                    new BetaTestKeyException("Beta test key not found")
                );
                return;

            }


            /*
             * В сегменте кода ниже , выполняется условие проверки ключа 
             * если ключ по каким то причинам не валиден , он выкинет BetaTestKeyException / OtherException в 
             * котором будет объяснено почему ключ не прошел проверку. 
             * В случае если ключ валиден , он вернет true и фильтр пропустит запрос 
             * дальше по цепочке  
            */

            try{
                this.keyManager.isBetaKeyIsValid(headerKeyParam);

                filterChain.doFilter(request, response);
                return;

            }catch(Exception e){
                constructErrorResponse(response, "An error occurred while processing the key", e);
                return;
            }
    }


  
    
}
