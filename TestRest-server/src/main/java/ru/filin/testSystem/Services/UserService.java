package ru.filin.testSystem.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.filin.testSystem.domain.Role;
import ru.filin.testSystem.domain.User;
import ru.filin.testSystem.repositories.UserRepository;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByNickname(s);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepository.findByNickname(user.getNickname());
        if (userFromDb != null) {
            return false;
        }

        user.setRoles(Collections.singleton(Role.USER));

        userRepository.save(user);

        return true;
    }
}
