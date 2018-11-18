package ru.filin.testSystem.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.filin.DTO.UserDTO;
import ru.filin.testSystem.domain.User;
import ru.filin.testSystem.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public List<UserDTO> getUser() {
        List<User> users = userRepository.findAll();
//        List<UserDTO> allUsers = new ArrayList<>();
//        all.stream().forEach(user -> allUsers.add(new UserDTO(user.getNickname(), user.getPassword(), user.getName())));
        return users.stream()
                .map(user -> new UserDTO(user.getNickname(), user.getPassword(), user.getName()))
                .collect(Collectors.toList());
    }
}
