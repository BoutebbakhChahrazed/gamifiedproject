package gamifiedproject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gamifiedproject.Model.Services.CourseServices;
import gamifiedproject.Model.entities.Course;


@RestController
@RequestMapping("/api/v1/teacher")
@PreAuthorize("hasRole('TEACHER')")
@CrossOrigin(origins = "http://localhost:3000")
public class TeacherController {
	
	
	@Autowired
	public CourseServices courseService;
	
//	@GetMapping("/Courses")
//    @PreAuthorize("hasAuthority('teacher:read')")
//    public List<Course> getAllCourses() {
//        return courseService.getAllCourses();
//    }
    
    
    @GetMapping("/Course/{id}")
    @PreAuthorize("hasAuthority('teacher:read')")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }
    
    
    
    
    @GetMapping("/search/{keyword}")
    @PreAuthorize("hasAuthority('teacher:read')")
    public List<Course> Search(@PathVariable String keyword) {
        return courseService.searchCourses(keyword);
	
    }

}
