package mk.ukim.finki.backend.web;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.models.ShoppingCart;
import mk.ukim.finki.backend.models.User;
import mk.ukim.finki.backend.repository.CourseRepository;
import mk.ukim.finki.backend.service.ShoppingCartService;
import mk.ukim.finki.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/courses")
public class CoursesRestController {

    private final CourseRepository courseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    public CoursesRestController(
            CourseRepository courseRepository
    ) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<Course> coursesForCategory(@RequestParam(required = false) String category) {
        if (category == null) {
            return this.courseRepository.findAll();
        } else {
            return this.courseRepository.coursesForCategory(category);
        }
    }
    @GetMapping("/add_to_shopping_cart")
    public ResponseEntity<Boolean> addToShoppingCart(@RequestParam(required = false) String courseId, @RequestParam(required = false) String username, @RequestParam(required = false) String password){
//        User user = userService.findByUsernameAndPassword(username,password);
        User user = userService.findByUsername(username);
        ShoppingCart cart = shoppingCartService.getShoppingCartByUser(user.getId());

        Course course = courseRepository.getById(Long.parseLong(courseId));
        cart.getCourses().add(course);
        if(userService.save(user)!=null){
//            return "True";
            return ResponseEntity.ok(true);
//            JSONObject response = new JSONObject();
//            response.put("message", "Course added to shopping cart successfully");
        }
//        return "False";
    return ResponseEntity.ok(false);
//        return "redirect:/courses/shopping_cart";
//        return "True";
    }
}
