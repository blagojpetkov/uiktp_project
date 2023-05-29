package mk.ukim.finki.backend.service.impl;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.models.ShoppingCart;
import mk.ukim.finki.backend.models.User;
import mk.ukim.finki.backend.repository.CourseRepository;
import mk.ukim.finki.backend.repository.ShoppingCartRepository;
import mk.ukim.finki.backend.repository.UserRepository;
import mk.ukim.finki.backend.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Course> listAllCoursesInShoppingCart(Long userId) {
        ShoppingCart shoppingCart = this.getShoppingCartByUser(userId);
        return shoppingCart.getCourses();
    }

    @Override
    public List<ShoppingCart> findByCoursesContaining(Course course) {
        return shoppingCartRepository.findByCoursesContaining(course);
    }

    @Override
    @Transactional
    public ShoppingCart getShoppingCartByUser(Long userId) {
        return this.shoppingCartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId).get();
                    return this.shoppingCartRepository.save(new ShoppingCart(user));
                });
    }

    @Override
    public ShoppingCart addCourseToShoppingCart(Long userId, Long courseId) {
        ShoppingCart shoppingCart = this.getShoppingCartByUser(userId);
        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        shoppingCart.getCourses().add(course);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart removeCourseFromShoppingCart(Long userId, Long courseId) {
        ShoppingCart shoppingCart = this.getShoppingCartByUser(userId);
        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        shoppingCart.getCourses().remove(course);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void clearShoppingCart(Long userId) {
        ShoppingCart shoppingCart = this.getShoppingCartByUser(userId);
        shoppingCart.getCourses().clear();
        this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void save(ShoppingCart cart) {
        shoppingCartRepository.save(cart);
    }
}