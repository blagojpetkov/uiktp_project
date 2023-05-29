package mk.ukim.finki.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    private String answer1;

    private String answer2;

    private String answer3;

    private String answer4;

    private int correctAnswer;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Test test;

    public Question(Test test, String text, String answer1, String answer2, String answer3, String answer4, int correctAnswer) {
        this.test = test;
        this.text = text;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
    }
}
