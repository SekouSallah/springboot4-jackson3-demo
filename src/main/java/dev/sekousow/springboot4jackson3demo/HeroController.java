package dev.sekousow.springboot4jackson3demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sékou Sallah Sow <sowsekou@hotmail.com>
 */
@RestController
@RequestMapping
public class HeroController {
    private final HeroDataLoader heroDataLoader;

    public HeroController(HeroDataLoader heroDataLoader) {
        this.heroDataLoader = heroDataLoader;
    }

    @GetMapping(produces = "application/json")
    public List<Hero> getHeroes() {
        return heroDataLoader.getHeroes();
    }
}
