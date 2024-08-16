package dev.lordmaryo.runnerz;

import dev.lordmaryo.runnerz.users.User;
import dev.lordmaryo.runnerz.users.UserHttpClient;
import dev.lordmaryo.runnerz.users.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        WelcomeMessage welcomeMessage = new WelcomeMessage();
        System.out.println(welcomeMessage.getMessage());
    }

    @Bean
    UserHttpClient userHttpClient() {
        RestClient restClient = RestClient.create("http://jsonPlaceHolder.typicode.com/");
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter
                        .create(restClient)).build();

        return factory.createClient(UserHttpClient.class);
    }

    @Bean
    CommandLineRunner runner(UserHttpClient client) {
        return args -> {
            List<User> users = client.findAll();
            System.out.println(users);

            User user = client.findById(2);
            System.out.println(user);
        };
    }
}
