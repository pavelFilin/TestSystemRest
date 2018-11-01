package ru.filin.testSystem.restControllers;

import org.springframework.web.bind.annotation.*;
import ru.filin.testSystem.domain.User;

@RestController
public class UserRestController {
    @GetMapping("user")
    public User getUser() {
        User user = new User("nickname1", "password1", "name1");
        return user;
    }
}
