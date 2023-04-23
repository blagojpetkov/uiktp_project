package mk.ukim.finki.backend.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Course> courses = new ArrayList<>();

    public ShoppingCart(User user) {
        this.user = user;
    }

    public ShoppingCart() {
    }

}