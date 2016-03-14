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

    @RequestMapping(value = "/startMission", method = RequestMethod.GET)
    public String startMission(@RequestParam(value = "heroId") long heroId, Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getName().equalsIgnoreCase("anonymousUser"))
            model.addAttribute("isAuth", false);
        else
            model.addAttribute("isAuth", true);

        Hero hero = heroService.getHeroById(heroId);
        model.addAttribute("thisHero", hero);

        int map[][] = missionService.initMap(hero.getLevel());
        int mapSize = missionService.getMapSize(hero.getLevel());
        int player_x = mapSize / 2;
        int player_y = mapSize / 2;

        model.addAttribute("map_size", mapSize);
        model.addAttribute("player_x", player_x);
        model.addAttribute("player_y", player_y);

        map[player_x][player_y] = 1;
        model.addAttribute("map", map);
        missionService.insesrtInsation("thisHero", hero);

        return "playMission";
    }

    @RequestMapping(value = "/playMission", method = RequestMethod.GET)
    public String playMission(Model model, HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getName().equalsIgnoreCase("anonymousUser"))
            model.addAttribute("isAuth", false);
        else
            model.addAttribute("isAuth", true);

        Hero hero = (Hero) request.getAttribute("thisHero");
        System.out.println("" + hero.getLevel());

        return "playMission";
    }
}
