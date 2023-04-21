package mk.ukim.finki.backend.models;

import javax.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private User instructor;
    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments = new ArrayList<>();
//    private Date startDate;
//    private Date endDate;

    // Constructors, getters, and setters
}