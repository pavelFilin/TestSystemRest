package ru.filin.testSystem.domain;


import org.springframework.stereotype.Repository;
import ru.filin.testSystem.repositories.QuizRepository;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "quiz")
public class Quiz implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name = "title", length = 50)
    String title;

    public Quiz() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
