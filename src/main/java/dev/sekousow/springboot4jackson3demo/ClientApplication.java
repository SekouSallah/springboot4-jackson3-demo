package dev.sekousow.springboot4jackson3demo;

import com.fasterxml.jackson.annotation.JsonView;
import org.jspecify.annotations.NullMarked;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Sékou Sallah Sow <sowsekou@hotmail.com>
 */
public class ClientApplication implements ApplicationRunner {

    private final RestClient restClient;

    public ClientApplication(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://localhost:8080").build();
    }

    /**
     * I use this method to avoid to create a separate
     * module for client-app to consume server hero APIs
     */
    static void main(String[] args) {
        new SpringApplicationBuilder(ClientApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    @NullMarked
    public void run(ApplicationArguments args) {
        IO.println("Client application started");
        var hero = new Hero(
                "Iron Man",
                "Tony Stark",
                BigDecimal.valueOf(999.99),
                Hero.HeroAlignment.HERO,
                true,
                LocalDateTime.now().plusMinutes(10),
                "Arc Reactor",
                BigDecimal.valueOf(3.14159),
                false,
                "Malibu Mansion");

        Hero response = this.restClient.post()
                .uri("/api/heroes")
                .hint(JsonView.class.getName(), Views.Summary.class)
                .body(hero)
                .retrieve()
                .toEntity(Hero.class).getBody();
        IO.println("Hero response: " + response);

    }
}
