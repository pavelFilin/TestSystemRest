package ru.filin.testSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.filin.testSystem.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByNickname(String userName);
}
