package dev.sekousow.springboot4jackson3demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

/**
 * @author Sékou Sallah Sow <sowsekou@hotmail.com>
 */
@Configuration
public class JacksonConfig {

    /**
     * Prefer application.properties configuration instanceof bean initialization.
     * If you want to use configuration-based application properties, comment @Bean annotation
     * And uncomment in application.properties
     */
    @Bean
    public JsonMapper jsonMapper() {
        return JsonMapper
                .builder()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) // Not failed on unknown field in json. WARNING: It can be dangerous in some contexts
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .configure(SerializationFeature.INDENT_OUTPUT, true) // May produce a prettier json output
                .build();
    }
}
