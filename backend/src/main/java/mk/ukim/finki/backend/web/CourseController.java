package mk.ukim.finki.backend.web;

import mk.ukim.finki.backend.models.Course;
import mk.ukim.finki.backend.models.Lesson;
import mk.ukim.finki.backend.models.User;
import mk.ukim.finki.backend.repository.CourseRepository;
import mk.ukim.finki.backend.service.LessonService;
import mk.ukim.finki.backend.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final UserService userService;
    private final CourseRepository courseRepository;
    private final LessonService lessonService;
    public CourseController(UserService userService, CourseRepository courseRepository, LessonService lessonService) {
        this.userService = userService;
        this.courseRepository = courseRepository;
        this.lessonService = lessonService;
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
            @RequestParam MultipartFile image
    ){
        courseRepository.save(new Course(name, description, category, image, userService.getAuthenticatedUser()));
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/my_courses")
    public String myCourses(Model model){
        User user = userService.getAuthenticatedUser();
        List<Course> courseList = courseRepository.findCoursesByInstructorUsername(user.getUsername());
        model.addAttribute("courses", courseList);
        return "my_courses";
    }



    @GetMapping("/delete_course/{courseId}")
    public String addCourse(@PathVariable Long courseId){
        courseRepository.deleteById(courseId);
        return "redirect:/courses/my_courses";
    }

    @GetMapping("/course_details/{courseId}")
    public String getCourseDetails(@PathVariable Long courseId, Model model){
        model.addAttribute("course", courseRepository.findById(courseId).get());
        return "course_details";
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
