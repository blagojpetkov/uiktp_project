package mk.ukim.finki.backend.service;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.models.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    public List<Course> listAllCoursesInShoppingCart(Long userId);
    public List<ShoppingCart> findByCoursesContaining(Course course);

    public ShoppingCart getShoppingCartByUser(Long userId);

    public ShoppingCart addCourseToShoppingCart(Long userId, Long courseId);

    public ShoppingCart removeCourseFromShoppingCart(Long userId, Long courseId);

    public void clearShoppingCart(Long userId);

    void save(ShoppingCart cart);
}