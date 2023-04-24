package mk.ukim.finki.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Course course;
    private int grade;
    private String text;
    private Date reviewDate;

    public Review(User user, Course course, int grade, String text, Date reviewDate) {
        this.user = user;
        this.course = course;
        this.grade = grade;
        this.text = text;
        this.reviewDate = reviewDate;
    }
}