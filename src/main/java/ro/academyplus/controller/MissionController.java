package ro.academyplus.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Flo on 11-Mar-16.
 */
@Controller
public class MissionController {

    @RequestMapping(value = "/startMission", method = RequestMethod.GET)
    public String startMission(@RequestParam(value = "heroId") long HeroId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        int i, j;
        int map[][] = new int[11][11];

        for (i = 0; i < 11; i++)
            for (j = 0; j < 11; j++)
                map[i][j] = 0;
        map[5][5] = 1;

        model.addAttribute("map", map);

        if (auth.getName().equalsIgnoreCase("anonymousUser"))
            model.addAttribute("isAuth", false);
        else
            model.addAttribute("isAuth", true);

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
