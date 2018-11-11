package ru.filin.testSystem.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.filin.DTO.UserDTO;
import ru.filin.testSystem.domain.Role;
import ru.filin.testSystem.domain.User;
import ru.filin.testSystem.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    String myBean;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public List<User> getUser() {
        User user = new User();
        user.setEmail("test@mail.ru");
        user.setName("name");
        user.setNickname("nickname");
        user.setPassword("password");
        user.setRoles(new HashSet<>());
        user.getRoles().add(Role.Admin);
//        List<UserDTO> users = new ArrayList<>();
//        userRepository.save(user);
//        users.add(user.getUserDTO());
        return userRepository.findAll();
    }
}
