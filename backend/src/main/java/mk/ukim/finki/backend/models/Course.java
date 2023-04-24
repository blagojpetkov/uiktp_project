package mk.ukim.finki.backend.models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private User instructor;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments = new ArrayList<>();

    // Defines the category of the course, e.g. Data Science, Android, Angular, React
    private String category;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

    // Image for the course
    @Lob
    @Column(nullable = false)
    private byte[] image;

    public Course(String name, String description, String category, MultipartFile image) {
        this.name = name;
        this.description = description;
        this.category = category;
        try {
            this.image = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Course() {

    }

}