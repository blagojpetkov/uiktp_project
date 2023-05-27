package mk.ukim.finki.backend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Test {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "test",cascade = CascadeType.REMOVE)
    private List<Question> questions = new ArrayList<>();

    @OneToOne
    private Lesson lesson;
}
