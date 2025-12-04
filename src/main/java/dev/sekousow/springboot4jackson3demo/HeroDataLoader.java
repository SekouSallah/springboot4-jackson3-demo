package dev.sekousow.springboot4jackson3demo;

import org.jspecify.annotations.NullMarked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
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
    @NullMarked // Use of JSpecify
    public void run(String... args) throws Exception {
        try {
            var resource = resourceLoader.getResource(DATA_PATH);

            if (!resource.exists()) {
                logger.warn("Heroes resource not found at: {}", DATA_PATH);
                return;
            }

            this.heroes = jsonMapper.readValue(resource.getInputStream(), new TypeReference<>() {
            });

            heroes.forEach(IO::println);

            validateSerialization(this.heroes);

        } catch (JacksonException e) { // You can catch globally jackson exception, not only JsonProcessingException
            logger.error("Parsing error: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error loading heroes from {}", DATA_PATH);
            throw e;
        }
    }

    public List<Hero> getHeroes() {
        return this.heroes;
    }

    public void validateSerialization(List<Hero> heroes) {
        var hero = heroes.stream().findFirst();

        String json = jsonMapper.writeValueAsString(hero);

        logger.debug("JSON: {}", json); // What you remark in output ?
    }
}
