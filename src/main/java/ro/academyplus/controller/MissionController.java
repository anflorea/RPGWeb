package ro.academyplus.controller;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.academyplus.model.Hero;
import ro.academyplus.service.HeroService;
import ro.academyplus.service.MissionService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Flo on 11-Mar-16.
 */
@Controller
public class MissionController {

    @Autowired
    HeroService heroService;
    @Autowired
    MissionService missionService;

    @RequestMapping(value = "/startMission", method = RequestMethod.POST)
    public @ResponseBody boolean startMission(@RequestParam(value = "heroId") long heroId, Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getName().equalsIgnoreCase("anonymousUser"))
            model.addAttribute("isAuth", false);
        else
            model.addAttribute("isAuth", true);

        Hero hero = heroService.getHeroById(heroId);
        request.getSession().setAttribute("thisHero", hero);

        int mapSize = missionService.getMapSize(hero.getLevel());
        request.getSession().setAttribute("mapSize", mapSize);
        request.getSession().setAttribute("hero_x", mapSize / 2);
        request.getSession().setAttribute("hero_y", mapSize / 2);
        request.getSession().setAttribute("old_x", mapSize / 2);
        request.getSession().setAttribute("old_y", mapSize / 2);

        int map[][] = missionService.initMap(hero.getLevel());
        request.getSession().setAttribute("map", map);

        return true;
    }

    @RequestMapping(value = "/playMission", method = RequestMethod.GET)
    public String playMission(Model model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getName().equalsIgnoreCase("anonymousUser"))
            model.addAttribute("isAuth", false);
        else
            model.addAttribute("isAuth", true);

        Hero hero = (Hero) request.getSession().getAttribute("thisHero");
        model.addAttribute("thisHero", hero);
        int map[][] = (int[][]) request.getSession().getAttribute("map");
        map[(Integer) request.getSession().getAttribute("old_x")][(Integer) request.getSession().getAttribute("old_y")] = 0;
        map[(Integer) request.getSession().getAttribute("hero_x")][(Integer) request.getSession().getAttribute("hero_y")] = 1;
        model.addAttribute("map", map);
        model.addAttribute("mapSize", (Integer) request.getSession().getAttribute("mapSize"));

        return "playMission";
    }

    @RequestMapping(value = "/moveHero", method = RequestMethod.POST)
    public @ResponseBody String moveTheHero(@RequestParam(value = "move") String move, HttpServletRequest request) {
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

        if (map[desired_x][desired_y] == 0) {
            request.getSession().setAttribute("old_x", hero_x);
            request.getSession().setAttribute("old_y", hero_y);
            hero_x = desired_x;
            hero_y = desired_y;
            request.getSession().setAttribute("hero_x", hero_x);
            request.getSession().setAttribute("hero_y", hero_y);
            return ("OK");
        }

        return null;
    }
}
