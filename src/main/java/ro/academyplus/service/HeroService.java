package ro.academyplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.academyplus.dto.HeroDTO;
import ro.academyplus.model.Hero;
import ro.academyplus.model.User;
import ro.academyplus.model.artifacts.Artifacts;
import ro.academyplus.repository.ArtifactRepository;
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
    @Autowired
    ArtifactRepository artifactRepository;

    public ArrayList<Hero> listingHero(long userId) {

        ArrayList<Hero> heroes = heroRepository.findByUserId(userId);

        return heroes;
    }

    public Hero getHeroById(long heroId) {
        return heroRepository.findOneById(heroId);
    }

    public String getHeroDescription(long id) {
        Hero hero = heroRepository.findOneById(id);
        String wep = "none";
        String arm = "none";
            if(hero.getWeapon() > -1)
            {
                Artifacts artifacts = artifactRepository.findOneById( hero.getWeapon());
                wep = artifacts.getName();

            }
            if(hero.getArmor() > -1){
                Artifacts artifacts = artifactRepository.findOneById( hero.getArmor());
                arm = artifacts.getName();
            }
            return ("Name: " + hero.getName() + "\nType: " + hero.getType().toString() + "\nLevel: " +  hero.getLevel() + " ("  + hero.getExperience() + "%)\nHealth: " + hero.getHealth() + "\\"  + hero.getMaxHealth() + "\nDamage " + hero.getDamage() + "\nArmor: " + arm + "\nWeapon: " + wep );
    }

    public void updateHero(HeroDTO hero) {
        Hero theHero = heroRepository.findOneById(hero.getId());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findOneByEmail(auth.getName());
        if (user.getId() == theHero.getUserId()) {
            if (hero.getHeroName().length() > 0)
                theHero.setName(hero.getHeroName());
            else
                theHero.setName("Unnamed Hero");
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
        newHero.createHero(hero);
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
