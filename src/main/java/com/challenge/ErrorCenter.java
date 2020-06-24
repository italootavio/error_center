package com.challenge;

import com.challenge.model.EventLog;
import com.challenge.enums.Level;
import com.challenge.repository.EventLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class ErrorCenter {

    @Bean
    public CommandLineRunner init(@Autowired EventLogRepository event){
        return args -> {
            /*event.save(new EventLog(Level.info,"ds","ds",new Integer(1),LocalDateTime.now(),"LOG1"));
            event.save(new EventLog(Level.error,"ds","ds",new Integer(1),LocalDateTime.now(),"LOG2"));
            System.out.println(event.existsById(1L));

             */
        };

    }


    public static void main(String[] args) {
        SpringApplication.run(ErrorCenter.class, args);
    }
}
