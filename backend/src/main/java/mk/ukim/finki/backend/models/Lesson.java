package mk.ukim.finki.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
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

    public Lesson(String title, String description, String content, int number, Course course) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.number = number;
        this.course = course;
    }
}