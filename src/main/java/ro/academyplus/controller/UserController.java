package ro.academyplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "login";
    }
}
