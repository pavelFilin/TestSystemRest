package ru.filin.testSystem.domain;



import javax.persistence.*;
import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column
    String title;

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
