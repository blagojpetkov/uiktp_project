package mk.ukim.finki.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;

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

    //Video for the lesson
    @Lob
    @Column(nullable = false)
    private byte[] video;

    //JsonIgnore needed to fix Infinite recursion
    @JsonIgnore
    @ManyToOne
    private Course course;

    @OneToOne(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private Test test;



    public Lesson(String title, String description, String content, int number, Course course, MultipartFile video) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.number = number;
        this.course = course;
        try {
            this.video = video.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}