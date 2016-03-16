package ro.academyplus.controller;

import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.metamodel.relational.state.ManyToOneRelationalState;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
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
import ro.academyplus.model.Monster;
import ro.academyplus.service.HeroService;
import ro.academyplus.service.MissionService;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

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

        int map[][] = missionService.initMap(mapSize);
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

        move = missionService.movetheHero(move);
        System.out.println(move + "");
        if (move.equals("MONSTER")) {

            Hero hero = (Hero) request.getSession().getAttribute("thisHero");
            Monster monster = new Monster();
            monster.createMonster(hero.getLevel());
            request.getSession().setAttribute("monster", monster);
            return ("monster" + monster.toString());
        }
        return move;
    }

    @RequestMapping(value ="/winLevel", method = RequestMethod.GET)
    public @ResponseBody String passTheLevel(Model model, HttpServletRequest request) {
        Hero hero = (Hero) request.getSession().getAttribute("thisHero");
        missionService.levelPass(hero);
        System.out.println(hero.getExperience());
        return "selectHero";
    }

    @RequestMapping (value ="/fightMonster", method = RequestMethod.POST)
    public @ResponseBody String fightMonster(Model model, HttpServletRequest request) {

        Hero hero = (Hero) request.getSession().getAttribute("thisHero");
        Monster monster = (Monster) request.getSession().getAttribute("monster");
        String result = missionService.heroFightMonster(hero, monster);

        return result;
    }

    @RequestMapping (value = "/runMonster", method = RequestMethod.POST)
    public @ResponseBody String runMonster(Model model, HttpServletRequest request) {

        String result = "";
        Hero hero = (Hero) request.getSession().getAttribute("thisHero");
        Monster monster = (Monster) request.getSession().getAttribute("monster");
        return result = missionService.runOrFightMonster(hero, monster);
    }

}