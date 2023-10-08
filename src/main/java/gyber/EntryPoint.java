package gyber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import gyber.sapphire.config.SpringConfig;


public class EntryPoint {

    public static void main(String[] args) {
        SpringApplication.run(SpringConfig.class , args);

        
    }


    
}
