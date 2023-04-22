package mk.ukim.finki.backend.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Lesson {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String content;
    private int number;

    @ManyToOne
    private Course course;

}