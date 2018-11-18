package ru.filin.testSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.filin.testSystem.Services.UserService;
import ru.filin.testSystem.domain.User;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegistration(){
        return "registration";
    }
    
    @PostMapping("/registration")
    public String addUser(@RequestParam String name,@RequestParam String nickname, @RequestParam String email,@RequestParam String password, Model model){
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setNickname(nickname);
        user.setPassword(password);
            if (userService.addUser(user)) {
            return "login";
        } else {
            model.addAttribute("message", "whoops! your date is bad");
            return "registration";
        }
    }
}

