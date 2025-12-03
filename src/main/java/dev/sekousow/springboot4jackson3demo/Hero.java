package dev.sekousow.springboot4jackson3demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Sékou Sallah Sow <sowsekou@hotmail.com>
 */
public record Hero(
        String heroName,
        String realName,
        BigDecimal powerLevel,
        HeroAlignment alignment,
        Boolean isFlying,
        LocalDateTime lastSeen,
        String favoriteGadgetName,
        BigDecimal favoriteGadgetEnergyOutput,
        Boolean favoriteGadgetIsPrototype,
        String homeBase
) {
    public enum HeroAlignment {
        HERO, CHAOTIC, VILLAIN
    }
}
