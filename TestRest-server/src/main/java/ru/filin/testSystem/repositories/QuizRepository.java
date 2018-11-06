package ru.filin.testSystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.filin.testSystem.domain.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Quiz findByTitle(String title);
}
