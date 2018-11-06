package ru.filin.testSystem.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.filin.DTO.UserDTO;
import ru.filin.testSystem.domain.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    String myBean;

    @GetMapping("users")
    public List<UserDTO> getUser() {
        User user = new User("nickname1", "password1", myBean);
        List<UserDTO> users = new ArrayList<>();
        users.add(user.getUserDTO());
        return users;
    }
}
