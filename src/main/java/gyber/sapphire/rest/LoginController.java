package gyber.sapphire.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {




    @GetMapping("/login")
    public String login(){

        System.out.println("\n\nOK\n\n");
      
        return "login";

    }


    @GetMapping("/simple")
    public String simple(){

        System.out.println("\n\n\nSIMPLE\n\n\n");
        return "simple";
    }


  

    
}
