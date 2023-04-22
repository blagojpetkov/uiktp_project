package mk.ukim.finki.backend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
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

}