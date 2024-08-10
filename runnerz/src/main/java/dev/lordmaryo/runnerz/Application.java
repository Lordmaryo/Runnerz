package dev.lordmaryo.runnerz;

import dev.lordmaryo.runnerz.run.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        WelcomeMessage welcomeMessage = new WelcomeMessage();
        System.out.println(welcomeMessage.getMessage());

        RunRepo runRepo = new RunRepo();
        RunController runController = new RunController();

        if (runController.getClass().isAnnotationPresent(VeryImportant.class)) {
            System.out.println("This class is very important");
        } else {
            System.out.println("This class is not so very important");
        }
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            Run run = new Run(1, "Runnerz", LocalDateTime.now(),
                    LocalDateTime.now().plusHours(1),
                    5, Location.OUTDOOR);
            log.info("Run: {}", run);
        };
    }
}
