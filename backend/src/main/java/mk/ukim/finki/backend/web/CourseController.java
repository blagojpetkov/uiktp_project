package mk.ukim.finki.backend.web;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.repository.CourseRepository;
import mk.ukim.finki.backend.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final UserService userService;
    private final CourseRepository courseRepository;

    public CourseController(UserService userService, CourseRepository courseRepository) {
        this.userService = userService;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/add_course")
    public String addCourse(){
        return "add_course";
    }

    @PostMapping("/add_course")
    public String addCourse(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String category,
            @RequestParam MultipartFile image
    ){
        courseRepository.save(new Course(name, description, category, image));
        return "home";
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Course course = courseRepository.findById(id).orElseThrow();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(course.getImage(), headers, HttpStatus.OK);
    }

}
