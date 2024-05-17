package gamifiedproject.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gamifiedproject.Model.Services.CourseServices;
import gamifiedproject.Model.Services.LessonServices;
import gamifiedproject.Model.entities.Course;
import gamifiedproject.Model.entities.Lesson;
import gamifiedproject.auth.AuthenticationResponse;
import gamifiedproject.token.Token;

@RestController
@RequestMapping("/api/v1/student")
@PreAuthorize("hasRole('STUDENT')")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

	@Autowired
	private CourseServices courseService;
	
	@Autowired
	private LessonServices lessonService;

	@GetMapping("/Courses")
	@PreAuthorize("hasAuthority('student:read')")
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}

	@GetMapping("/Course/{id}")
	@PreAuthorize("hasAuthority('student:read')")
	public Course getCourseById(@PathVariable("id") Long id) {
		return courseService.getCourseById(id);
	}
	
	@GetMapping("/Course/{id}/lessons")
	@PreAuthorize("hasAuthority('student:read')")
	public List<Lesson> getLessonsByIdCourse(@PathVariable("id") Long id) {
		return lessonService.getLessonsByIdCourse(id);
	}

	@GetMapping("/search/{keyword}")
	@PreAuthorize("hasAuthority('student:read')")
	public List<Course> Search(@PathVariable("keyword") String keyword) {
		return courseService.searchCourses(keyword);
	}
	
	@GetMapping("/recomendedcourses/{token}")
	@PreAuthorize("hasAuthority('student:read')")
	public ArrayList<Course> getAllRecomendedc(@PathVariable("token")String token) {
		ArrayList <String> recomendedCourses= courseService.getRecommendedCourses( token);
		
		return courseService.findByRecomendedcourses(recomendedCourses);
	}

}
