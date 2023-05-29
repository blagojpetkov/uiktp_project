package mk.ukim.finki.backend.web;

import mk.ukim.finki.backend.models.*;
import mk.ukim.finki.backend.models.stripe.ChargeRequest;
import mk.ukim.finki.backend.repository.CourseRepository;
import mk.ukim.finki.backend.service.*;
import mk.ukim.finki.backend.service.impl.TestServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final UserService userService;
    private final CourseRepository courseRepository;
    private final LessonService lessonService;
    private final ReviewService reviewService;
    private final ShoppingCartService shoppingCartService;
    private final TestServiceImpl testService;
    private final QuestionService questionService;

    public CourseController(UserService userService, CourseRepository courseRepository, LessonService lessonService, ReviewService reviewService, ShoppingCartService shoppingCartService, TestServiceImpl testService, QuestionService questionService) {
        this.userService = userService;
        this.courseRepository = courseRepository;
        this.lessonService = lessonService;
        this.reviewService = reviewService;
        this.shoppingCartService = shoppingCartService;
        this.testService = testService;
        this.questionService = questionService;
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/delete_course_from_shopping_cart/{courseId}")
    public String deleteFromShoppingCart(@PathVariable Long courseId, Model model){
        User user = userService.getAuthenticatedUser();
        ShoppingCart cart = shoppingCartService.getShoppingCartByUser(user.getId());
        Course course = courseRepository.getById(courseId);
        cart.getCourses().remove(course);
        userService.save(user);
        return "redirect:/courses/shopping_cart";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/add_to_shopping_cart/{courseId}")
    public String addToShoppingCart(@PathVariable Long courseId, Model model){
        User user = userService.getAuthenticatedUser();
        ShoppingCart cart = shoppingCartService.getShoppingCartByUser(user.getId());
        Course course = courseRepository.getById(courseId);
        cart.getCourses().add(course);
        userService.save(user);
        return "redirect:/courses/shopping_cart";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/shopping_cart")
    public String getShoppingCart(Model model){
        User user = userService.getAuthenticatedUser();
        ShoppingCart cart = shoppingCartService.getShoppingCartByUser(user.getId());
        model.addAttribute("courses", cart.getCourses());
        double amount = cart.getCourses().stream().mapToDouble(Course::getPrice).sum();
        model.addAttribute("amount", amount*100);
        model.addAttribute("stripePublicKey", "pk_test_51N5ACrAf5nZU7fOF46OKkqqPDuqVXdIaK4iZzjoRNi3nPcxs0NThwztUOnj77sCaQIRsm6eYe9ehQTn8xXNtgdj600gjODscrU");
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "shopping_cart";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/add_course")
    public String addCourse(){
        return "add_course";
    }

    @PostMapping("/add_course")
    public String addCourse(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String category,
            @RequestParam MultipartFile image,
            @RequestParam double price
    ){
        courseRepository.save(new Course(name, description, category, image, userService.getAuthenticatedUser(), price));
        return "redirect:/courses/my_courses";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/add_lesson/{courseId}")
    public String addLesson(@PathVariable Long courseId, Model model){
        model.addAttribute("courseId", courseId);
        return "add_lesson";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/add_lesson/{courseId}")
    public String addLesson(@PathVariable Long courseId,
                            @RequestParam String title,
                            @RequestParam String description,
                            @RequestParam String content,
                            @RequestParam int number,
                            @RequestParam MultipartFile video,
                                                 Model model){
        model.addAttribute("courseId", courseId);
        lessonService.create(title, description, content, number, courseId, video);
        return "redirect:/courses/course_details/"+courseId;
    }

    @GetMapping ("/delete_lesson/{courseId}/{lessonId}")
    public String deleteLesson(@PathVariable Long courseId, @PathVariable Long lessonId){
        lessonService.delete(lessonId);
        return "redirect:/courses/course_details/"+courseId;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/add_test/{lessonId}")
    public String addTestToLesson(@PathVariable String lessonId, Model model){
        model.addAttribute("lessonId", lessonId);
        return "add_test";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/add_test/{lessonId}")
    public String postAddTestToLesson(@PathVariable Long lessonId,
                                      @RequestParam String question1,
                                      @RequestParam String question2,
                                      @RequestParam String question3,
                                      @RequestParam String q1answer1,
                                      @RequestParam String q1answer2,
                                      @RequestParam String q1answer3,
                                      @RequestParam String q1answer4,
                                      @RequestParam String q2answer1,
                                      @RequestParam String q2answer2,
                                      @RequestParam String q2answer3,
                                      @RequestParam String q2answer4,
                                      @RequestParam String q3answer1,
                                      @RequestParam String q3answer2,
                                      @RequestParam String q3answer3,
                                      @RequestParam String q3answer4,
                                      @RequestParam int int1,
                                      @RequestParam int int2,
                                      @RequestParam int int3,
                                      Model model){
        model.addAttribute("lessonId", lessonId);
        Test test = new Test();

        Lesson lesson = lessonService.findById(lessonId);
        test.setLesson(lesson);
        testService.save(test);

        questionService.save(new Question(test, question1, q1answer1, q1answer2, q1answer3, q1answer4, int1));
        questionService.save(new Question(test, question2, q2answer1, q2answer2, q2answer3, q2answer4, int2));
        questionService.save(new Question(test, question3, q3answer1, q3answer2, q3answer3, q3answer4, int3));


        Course course = courseRepository.findCourseByLessonsId(lessonId).get();
        return "redirect:/courses/course_details/" + course.getId();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/take_test/{lessonId}")
    public String takeTest(@PathVariable Long lessonId, Model model){
        Lesson lesson = lessonService.findById(lessonId);
        Test test = lesson.getTest();
        model.addAttribute("courseId", lesson.getCourse().getId());
        model.addAttribute("test", test);
        return "take_test";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/my_courses")
    public String myCourses(Model model){
        User user = userService.getAuthenticatedUser();
        List<Course> courseList = courseRepository.findCoursesByInstructorUsername(user.getUsername());
        model.addAttribute("courses", courseList);
        return "my_courses";
    }



    @GetMapping("/delete_course/{courseId}")
    @Transactional
    public String deleteCourse(@PathVariable Long courseId){
        //
        Course course = courseRepository.findById(courseId).orElseThrow();
//        course.setShoppingCarts(new ArrayList<>());
//        courseRepository.save(course);
//        courseRepository.flush();
//        List<ShoppingCart> carts = course.getShoppingCarts();

                // Remove the course from shopping carts
        List<ShoppingCart> shoppingCarts = shoppingCartService.findByCoursesContaining(course);
        for (ShoppingCart cart : shoppingCarts) {
            cart.getCourses().remove(course);
            shoppingCartService.save(cart);
        }
        courseRepository.deleteById(courseId);

        return "redirect:/courses/my_courses";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/course_details/{courseId}")
    public String getCourseDetails(@PathVariable Long courseId, Model model){
        User user = userService.getAuthenticatedUser();
        List<Review> reviews = reviewService.reviewsByCourse(courseId);
        model.addAttribute("exists", true);
        model.addAttribute("reviews", reviews);
        model.addAttribute("averageScore", reviewService.averageReviewByCourse(courseId));

        model.addAttribute("course", courseRepository.findById(courseId).get());
        return "course_details";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/sendReview")
    public String addReview(@RequestParam Long courseId,
                            @RequestParam int rating,
                            @RequestParam String comment, Model model){
        Date date = new Date();
        User user = userService.getAuthenticatedUser();
        model.addAttribute("courses", courseRepository.findCoursesByInstructorUsername(user.getUsername()));
        reviewService.create(user.getId(), courseId, rating, comment, date);
        return "redirect:/course_details/" + courseId;
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Course course = courseRepository.findById(id).orElseThrow();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(course.getImage(), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}/video")
    public ResponseEntity<byte[]> getLessonVideo(@PathVariable Long id) {
        Lesson lesson = lessonService.findById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(lesson.getVideo(), headers, HttpStatus.OK);
    }

}
