package ro.academyplus.controller;

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

/**
 * Created by Flo on 11-Mar-16.
 */
@Controller
public class MissionController {

    @Autowired
    HeroService heroService;

    @RequestMapping(value = "/startMission", method = RequestMethod.GET)
    public String startMission(@RequestParam(value = "heroId") long heroId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        int i, j;
        int map_size = 11;
        int player_x = map_size / 2;
        int player_y = map_size / 2;

        model.addAttribute("map_size", map_size);
        model.addAttribute("player_x", player_x);
        model.addAttribute("player_y", player_y);

        int map[][] = new int[map_size][map_size];

        for (i = 0; i < map_size; i++)
            for (j = 0; j < map_size; j++)
                map[i][j] = 0;
        map[player_x][player_y] = 1;

        model.addAttribute("map", map);

        if (auth.getName().equalsIgnoreCase("anonymousUser"))
            model.addAttribute("isAuth", false);
        else
            model.addAttribute("isAuth", true);
        Hero hero = heroService.getHeroById(heroId);
        model.addAttribute("thisHero", hero);

        return "playMission";
    }

    @RequestMapping(value = "/playMission", method = RequestMethod.GET)
    public String playMission(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equalsIgnoreCase("anonymousUser"))
            model.addAttribute("isAuth", false);
        else
            model.addAttribute("isAuth", true);
        return "playMission";
    }
}
