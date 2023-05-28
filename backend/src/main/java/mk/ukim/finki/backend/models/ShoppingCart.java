package mk.ukim.finki.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    //JsonIgnore needed to fix Infinite recursion
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Course> courses = new ArrayList<>();

    public ShoppingCart(User user) {
        this.user = user;
    }

    public ShoppingCart() {
    }

}