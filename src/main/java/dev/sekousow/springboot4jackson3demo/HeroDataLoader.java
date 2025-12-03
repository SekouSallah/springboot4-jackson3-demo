package dev.sekousow.springboot4jackson3demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

/**
 * @author Sékou Sallah Sow <sowsekou@hotmail.com>
 */
@Component
public class HeroDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(HeroDataLoader.class);
    private static final String DATA_PATH = "classpath:data/marvel-heroes-flat.json";

    private final JsonMapper jsonMapper;
    private final ResourceLoader resourceLoader;

    private List<Hero> heroes;

    public HeroDataLoader(JsonMapper jsonMapper, ResourceLoader resourceLoader) {
        this.jsonMapper = jsonMapper;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            var resource = resourceLoader.getResource(DATA_PATH);

            if (!resource.exists()) {
                logger.warn("Heroes resource not found at: {}", DATA_PATH);
                return;
            }

            this.heroes = jsonMapper.readValue(resource.getInputStream(), new TypeReference<>() {
            });

            heroes.forEach(System.out::println);
        } catch (Exception e) {
            logger.error("Error loading heroes from {}", DATA_PATH);
            throw e;
        }
    }

    public List<Hero> getHeroes() {
        return this.heroes;
    }
}
