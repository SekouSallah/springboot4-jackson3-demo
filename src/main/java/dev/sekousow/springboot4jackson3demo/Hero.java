package dev.sekousow.springboot4jackson3demo;

import com.fasterxml.jackson.annotation.JsonView;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Sékou Sallah Sow <sowsekou@hotmail.com>
 */
public record Hero(
        @JsonView(Views.Summary.class)
        String heroName,

        @JsonView(Views.Public.class)
        String realName,

        @JsonView(Views.Public.class)
        BigDecimal powerLevel,

        @JsonView(Views.Public.class)
        HeroAlignment alignment,

        @JsonView(Views.Public.class)
        Boolean isFlying,

        @JsonView(Views.Public.class)
        LocalDateTime lastSeen,

        @JsonView(Views.Summary.class)
        String favoriteGadgetName,

        @JsonView(Views.Public.class)
        BigDecimal favoriteGadgetEnergyOutput,

        @JsonView(Views.Public.class)
        Boolean favoriteGadgetIsPrototype,

        @JsonView(Views.Internal.class)
        String homeBase
) {
    public enum HeroAlignment {
        HERO, CHAOTIC, VILLAIN
    }
}
