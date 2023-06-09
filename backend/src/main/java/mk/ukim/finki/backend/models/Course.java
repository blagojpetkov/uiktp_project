package mk.ukim.finki.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private User instructor;

    private String category;

    public double price;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<ShoppingCart> shoppingCarts = new ArrayList<>();

    // Image for the course
    @Lob
    @Column(nullable = false)
    private byte[] image;

    public Course(String name, String description, String category, MultipartFile image, User instructor, double price) {
        this.name = name;
        this.description = description;
        this.category = category;
        try {
            this.image = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.instructor = instructor;
        this.price = price;
    }
    public Course() {}
}