package ro.academyplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.academyplus.dto.UserDTO;
import ro.academyplus.model.User;
import ro.academyplus.service.AwesomeService;

import javax.validation.Valid;

/**
 * Created by agheboianu on 01.03.2016.
 */
@Controller
public class AwesomeController {

    @Autowired
    AwesomeService awesomeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String sendRegister(@ModelAttribute(value = "user") @Valid UserDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        User userModel = awesomeService.registerUser(user);
        return "redirect:/";
    }

//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String sendUser(@ModelAttribute(value = "user") @Valid UserDTO user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "create";
//        }
//        User userModel =awesomeService.registerUser(user);
//        return "redirect:/";
//    }
//
//    @RequestMapping(value = "/create", method = RequestMethod.GET)
//    public String createUserForm(Model model) {
//        UserDTO user = new UserDTO();
//        user.setName("Gigi");
//        user.setEmail("gigi@example.com");
//        model.addAttribute("user",user);
//        model.addAttribute("text","Hello world!");
//        return "create";
//    }
}
