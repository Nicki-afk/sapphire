package gyber.sapphire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import gyber.sapphire.config.SpringConfig;

@SpringBootApplication
public class EntryPoint {

    public static void main(String[] args) {
        SpringApplication.run(SpringConfig.class , args);

        
    }


    
}
