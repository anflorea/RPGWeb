package ro.academyplus.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.academyplus.dto.HeroDTO;
import ro.academyplus.model.Hero;
import ro.academyplus.model.HeroType;
import ro.academyplus.model.User;
import ro.academyplus.repository.UserRepository;
import ro.academyplus.service.HeroService;

import javax.validation.Valid;

/**
 * Created by agheboianu on 08.03.2016.
 */
@Controller
public class UserController {

    @RequestMapping("/login")
    public String showLogin(@RequestParam(value = "failed", required = false, defaultValue = "") String failed, Model model) {
        if (!failed.isEmpty()) {
            model.addAttribute("failed", true);
        }
        return "redirect:index";
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    HeroService heroesOfUser;

    @RequestMapping(value = "/selectHero", method = RequestMethod.GET)
    public String selectHero(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findOneByEmail(auth.getName());

        model.addAttribute("heroes", heroesOfUser.listingHero(user.getId()));
        model.addAttribute("heroType", HeroType.values());
        HeroDTO hero = new HeroDTO();
        model.addAttribute("hero", hero);

        if (auth.getName().equalsIgnoreCase("anonymousUser"))
            model.addAttribute("isAuth", false);
        else
            model.addAttribute("isAuth", true);
        return "selectHero";
    }

    @RequestMapping(value = "/createHero", method = RequestMethod.POST)
    public String createHero(@ModelAttribute(value = "hero") @Valid HeroDTO hero, BindingResult bindingResult) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (bindingResult.hasErrors()) {
            return "redirect:selectHero";
        }

        User user = userRepository.findOneByEmail(email);

        hero.setUserId(user.getId());
        Hero heroModel = heroesOfUser.addNewHero(hero);

        return "redirect:/selectHero";
    }

    @RequestMapping(value = "/renameHero", method = RequestMethod.POST)
    public String renameHero(@ModelAttribute(value = "hero") HeroDTO hero, BindingResult bindingResult) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (bindingResult.hasErrors()) {
            return "/selectHero";
        }
        heroesOfUser.updateHero(hero);
        //System.out.println(hero.getHeroName() + " id: " + hero.getId());

        return "redirect:selectHero";
    }

    @RequestMapping(value = "/requestHeroDescription", method = RequestMethod.GET)
    public @ResponseBody String giveHeroDescription(@RequestParam(value = "id") long heroId, Model model) {
        return heroesOfUser.getHeroDescription(heroId);
    }

    @RequestMapping(value = "/deleteHero", method = RequestMethod.DELETE)
    public @ResponseBody String deleteTheHero(@RequestParam(value = "id") long heroId) {
        heroesOfUser.deleteHero(heroId);
        return "redirect:selectHero";
    }
}
