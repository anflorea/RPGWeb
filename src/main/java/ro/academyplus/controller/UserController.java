package ro.academyplus.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.academyplus.model.User;
import ro.academyplus.repository.UserRepository;
import ro.academyplus.service.GettingHerosOfUser;

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
    GettingHerosOfUser herosOfUser;

    @RequestMapping(value = "/selectHero", method = RequestMethod.GET)
    public String selectHero(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        User user = userRepository.findOneByEmail(auth.getName());

        herosOfUser.listingHero(user.getId());

        if (auth.getName().equalsIgnoreCase("anonymousUser"))
            model.addAttribute("isAuth", false);
        else
            model.addAttribute("isAuth", true);
        return "selectHero";
    }
}
