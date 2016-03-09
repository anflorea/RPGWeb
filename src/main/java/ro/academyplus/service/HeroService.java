package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.academyplus.dto.HeroDTO;
import ro.academyplus.model.Hero;
import ro.academyplus.model.User;
import ro.academyplus.repository.HeroRepository;
import ro.academyplus.repository.UserRepository;

import java.util.ArrayList;

/**
 * Created by Bb on 3/8/2016.
 */
@Service
public class HeroService {


    @Autowired
    HeroRepository heroRepository;
    @Autowired
    UserRepository userRepository;


    public ArrayList<Hero> listingHero(long userId) {

        ArrayList<Hero> heroes = heroRepository.findByUserId(userId);

        return heroes;
    }

    public String getHeroDescription(long id) {
        Hero hero = heroRepository.findOneById(id);
        return (hero.toString());
    }

    public void updateHero(HeroDTO hero) {
        Hero theHero = heroRepository.findOneById(hero.getId());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findOneByEmail(auth.getName());
        if (user.getId() == hero.getUserId()) {
            theHero.setName(hero.getHeroName());
            heroRepository.flush();
        }
    }

    public void deleteHero(long heroId) {
        Hero hero = heroRepository.findOneById(heroId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findOneByEmail(auth.getName());
        if (user.getId() == hero.getUserId()) {
            heroRepository.delete(heroId);
            heroRepository.flush();
        }
    }

    public Hero addNewHero(HeroDTO hero) {
        Hero newHero = new Hero();
        newHero.setName(hero.getHeroName());
        newHero.setType(hero.getType());
        newHero.setHealth(100);
        newHero.setMaxHealth(100);
        newHero.setDamage(10);
        newHero.setUserId(hero.getUserId());
        return heroRepository.save(newHero);
    }
    public void deleteHero(HeroDTO hero) {
        Hero theHero = heroRepository.findOneById(hero.getId());
        heroRepository.delete(theHero.getId());
        heroRepository.flush();
    }
    public String listingstatusHero(HeroDTO hero) {
        Hero theHero = heroRepository.findOneById(hero.getId());
        String  statusHero = theHero.toString();
        System.out.println(statusHero);
        return  (statusHero);

    }
}
