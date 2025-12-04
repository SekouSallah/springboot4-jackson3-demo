package dev.sekousow.springboot4jackson3demo;

import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sékou Sallah Sow <sowsekou@hotmail.com>
 */
@RestController
@RequestMapping("/api/heroes")
public class HeroController {
    private final HeroDataLoader heroDataLoader;
    private final Logger logger = LoggerFactory.getLogger(HeroController.class);

    public HeroController(HeroDataLoader heroDataLoader) {
        this.heroDataLoader = heroDataLoader;
    }

    @GetMapping(path = "summary", produces = "application/json")
    @JsonView(Views.Summary.class)
    public List<Hero> getSummary() {
        return heroDataLoader.getHeroes();
    }

    @GetMapping(path = "public", produces = "application/json")
    @JsonView(Views.Public.class)
    public List<Hero> getPublic() {
        return heroDataLoader.getHeroes();
    }

    @GetMapping(path = "internal", produces = "application/json")
    @JsonView(Views.Internal.class)
    public List<Hero> getInternal() {
        return heroDataLoader.getHeroes();
    }

    /**
     * Send a summary hero, it's return an internal transformation.
     * But, check the output result :)
     */
    @PostMapping(produces = "application/json")
    @JsonView(Views.Internal.class)
    public Hero create(@RequestBody @JsonView(Views.Summary.class) Hero hero) {

        logger.info("Receive hero creation request");
        logger.debug("heroName: {}", hero.heroName());
        logger.debug("realName: {}", hero.realName());
        logger.debug("powerLevel: {}", hero.powerLevel());
        logger.debug("alignment: {}", hero.alignment());
        logger.debug("isFlying: {}", hero.isFlying()); // IMPORTANT: Check this boolean value :)
        logger.debug("lastSeen: {}", hero.lastSeen());
        logger.debug("favoriteGadgetName: {}", hero.favoriteGadgetName());
        logger.debug("favoriteGadgetEnergyOutput: {}", hero.favoriteGadgetEnergyOutput());
        logger.debug("favoriteGadgetIsPrototype: {}", hero.favoriteGadgetIsPrototype()); // IMPORTANT: Check this boolean value :)
        logger.debug("homeBase: {}", hero.homeBase());

        return new Hero(
                hero.heroName(),
                hero.realName(),
                hero.powerLevel(),
                hero.alignment(),
                hero.isFlying(),
                hero.lastSeen(),
                hero.favoriteGadgetName(),
                hero.favoriteGadgetEnergyOutput(),
                hero.favoriteGadgetIsPrototype(),
                hero.homeBase()
        );
    }
}
