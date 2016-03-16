package ro.academyplus.service;

import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.stereotype.Service;
import ro.academyplus.model.Hero;
import ro.academyplus.model.Monster;
import ro.academyplus.repository.HeroRepository;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * Created by azaha on 14.03.2016.
 */
@Service
public class MissionService {

    @Autowired
    HttpServletRequest request;
    @Autowired
    HeroRepository heroRepository;

    public int[][] initMap(int mapSize) {

        Random random = new Random();
        int numberOfMonsters = 0;

        int[][] map = new int[mapSize][mapSize];

        while (numberOfMonsters < mapSize) {
            int i = random.nextInt(mapSize - 1);
            int j = random.nextInt(mapSize - 1);
            map[i][j] = 2;
            numberOfMonsters++;
        }

        return (map);
    }

    public String movetheHero (String move) {

        int hero_x = (Integer) request.getSession().getAttribute("hero_x");
        int hero_y = (Integer) request.getSession().getAttribute("hero_y");
        int desired_x = hero_x;
        int desired_y = hero_y;
        int mapSize = (Integer) request.getSession().getAttribute("mapSize");
        int map[][] = (int[][]) request.getSession().getAttribute("map");

        if (move.equals("UP")) {
            desired_x = hero_x - 1;
            desired_y = hero_y;
        }

        if (move.equals("DOWN")) {
            desired_x = hero_x + 1;
            desired_y = hero_y;
        }

        if (move.equals("LEFT")) {
            desired_x = hero_x;
            desired_y = hero_y - 1;
        }

        if (move.equals("RIGHT")) {
            desired_x = hero_x;
            desired_y = hero_y + 1;
        }

        if (desired_x < 0 || desired_x >= mapSize || desired_y < 0 || desired_y >= mapSize)
            return("WIN");

        if (map[desired_x][desired_y] == 2) {
            return("MONSTER");
        }

        if (map[desired_x][desired_y] == 0) {
            request.getSession().setAttribute("old_x", hero_x);
            request.getSession().setAttribute("old_y", hero_y);
            hero_x = desired_x;
            hero_y = desired_y;
            request.getSession().setAttribute("desired_x", desired_x);
            request.getSession().setAttribute("desired_y", desired_y);
            request.getSession().setAttribute("hero_x", hero_x);
            request.getSession().setAttribute("hero_y", hero_y);
            return ("OK");
        }

        return null;
    }

    public int getMapSize(int heroLevel) {

        int mapSize;

        if (heroLevel >= 10)
            mapSize = 21;
        else if (heroLevel >= 5)
            mapSize = 15;
        else
            mapSize = 11;

        return mapSize;

    }

    public void levelPass(Hero hero) {

        Hero newHero = heroRepository.findOneById(hero.getId());
        newHero.setExperience(45);
        heroRepository.flush();
    }

    public String heroFightMonster(Hero hero, Monster monster) {

        String status = "default";

        while (hero.getHealth() > 0 && monster.getHealth() > 0) {
            monster.setHealth(hero.getDamage());
            if (monster.getHealth() < 0)
                status = "monsterDefeated";
            if (monster.getHealth() > 0) {
                hero.setHealth(monster.getDamage());
                if (hero.getHealth() < 0 && monster.getHealth() > 0)
                    status = "heroDefeated";
            }
        }
        if (status.equals("monsterDefeat")) {
            int hero_x = (Integer) request.getSession().getAttribute("hero_x");
            int hero_y = (Integer) request.getSession().getAttribute("hero_x");

            hero_x = (Integer) request.getSession().getAttribute("desired_x");
            hero_y = (Integer) request.getSession().getAttribute("desired_y");
            hero.setExperience(monster.getDropedExperience());

        }
        return status;
    }
}
