package gamifiedproject.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.sql.rowset.serial.SerialException;

import org.eclipse.angus.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gamifiedproject.Model.Services.AnswerService;
import gamifiedproject.Model.Services.CourseServices;
import gamifiedproject.Model.Services.QuestionService;
import gamifiedproject.Model.entities.Answer;
import gamifiedproject.Model.entities.Course;
import gamifiedproject.Model.entities.CourseDTO;

import gamifiedproject.Model.entities.Lesson;
import gamifiedproject.Model.entities.LessonDTO;
import gamifiedproject.Model.entities.Question;
import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/api/v1/specialist")
@PreAuthorize("hasRole('SPECIALIST')")
@CrossOrigin(origins = "http://localhost:3000")
public class SpecialistController {

	@Autowired
	private CourseServices courseService;

	@Autowired
	private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @PostMapping("/addCourse")
    @PreAuthorize("hasAuthority('specialist:create')")
    @Hidden
    public Long addCourse(@RequestParam("photo") MultipartFile photo,@RequestParam("title") String title,@RequestParam("description") String description, @RequestParam("level") String level ,@RequestParam("teacherName") String teacherName,@RequestParam("teacherLastname") String teacherLastname) throws IOException, SerialException, SQLException {
    	
    	
        return courseService.addCourse(photo, title,description,level,teacherName,teacherLastname);
        
        
    }
    
    @PostMapping("/addLessons/{id}")
    @PreAuthorize("hasAuthority('specialist:create')")
    @Hidden
    public void addCourseWithLessonsAndGames( @PathVariable("id") Long id ,@RequestBody List<LessonDTO> lessonDTOs) throws IOException, SerialException, SQLException {
       courseService.addLessonsAndContentAndGames(id,lessonDTOs);
    }

 // CourseController.java
    @PutMapping("/updateCourse/{id}")
    @PreAuthorize("hasAuthority('specialist:update')")
    @Hidden
    public ResponseEntity<?> updateCourse(
            @PathVariable("id") Long id,
            @RequestBody CourseDTO courseUpdateRequest) {
        try {
            Long updatedCourseId = courseService.updateCourse(id, courseUpdateRequest);
            return ResponseEntity.ok(updatedCourseId);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating course: " + e.getMessage());
        }
    }

    

	@DeleteMapping("/deleteCourse/{id}")
	@PreAuthorize("hasAuthority('specialist:delete')")
	@Hidden
	public ResponseEntity<String> deleteCourse(@PathVariable("id") Long courseId) {
		courseService.deleteCourse(courseId);
		return ResponseEntity.ok("Course deleted successfully");
	}

//    localhost:8080/api/v1/specialist/Courses
	@GetMapping("/Courses")
	@PreAuthorize("hasAuthority('specialist:read')")
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}

	@GetMapping("/Course/{id}")
	@PreAuthorize("hasAuthority('specialist:read')")
	public Course getCourseById(@PathVariable("id") Long id) {
		return courseService.getCourseById(id);
	}

	@GetMapping("/search/{keyword}")
	@PreAuthorize("hasAuthority('specialist:read')")
	public List<Course> Search(@PathVariable("keyword") String keyword) {
		return courseService.searchCourses(keyword);
	}
	
	
	

//	@GetMapping("/Answer")
//	public ResponseEntity<List<Answer>> getAllAnswers() {
//		List<Answer> answers = answerService.getAllAnswers();
//		return ResponseEntity.ok(answers);
//	}
//
//	@GetMapping("/Answer/{id}")
//	public ResponseEntity<Answer> getAnswerById(@PathVariable("id") Long id) {
//		Answer answer = answerService.getAnswerById(id);
//		if (answer != null) {
//			return ResponseEntity.ok(answer);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	@PostMapping("/Answer")
//	public ResponseEntity<Answer> createAnswer(@RequestBody Answer answer) {
//		Answer createdAnswer = answerService.saveAnswer(answer);
//		return ResponseEntity.status(HttpStatus.CREATED).body(createdAnswer);
//	}
//
//	@PutMapping("/Answer/{id}")
//	public ResponseEntity<Answer> updateAnswer(@PathVariable("id") Long id, @RequestBody Answer answer) {
//		Answer existingAnswer = answerService.getAnswerById(id);
//		if (existingAnswer != null) {
//			answer.setId(id);
//			Answer updatedAnswer = answerService.saveAnswer(answer);
//			return ResponseEntity.ok(updatedAnswer);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//
//	@DeleteMapping("/Answer/{id}")
//	public ResponseEntity<Void> deleteAnswer(@PathVariable("id") Long id) {
//		Answer existingAnswer = answerService.getAnswerById(id);
//		if (existingAnswer != null) {
//			answerService.deleteAnswer(id);
//			return ResponseEntity.noContent().build();
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}
//	
//
//	    @GetMapping("/question")
//	    public ResponseEntity<List<Question>> getAllQuestions() {
//	        List<Question> questions = questionService.getAllQuestions();
//	        return ResponseEntity.ok(questions);
//	    }
//
//	    @GetMapping("/question/{id}")
//	    public ResponseEntity<Question> getQuestionById(@PathVariable("id") Long id) {
//	        Question question = questionService.getQuestionById(id);
//	        if (question != null) {
//	            return ResponseEntity.ok(question);
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    }
//
//	    @PostMapping("/question")
//	    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
//	        Question createdQuestion = questionService.saveQuestion(question);
//	        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
//	    }
//
//	    @PutMapping("/question/{id}")
//	    public ResponseEntity<Question> updateQuestion(@PathVariable("id") Long id, @RequestBody Question question) {
//	        Question existingQuestion = questionService.getQuestionById(id);
//	        if (existingQuestion != null) {
//	            question.setId(id);
//	            Question updatedQuestion = questionService.saveQuestion(question);
//	            return ResponseEntity.ok(updatedQuestion);
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    }
//
//	    @DeleteMapping("/question/{id}")
//	    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") Long id) {
//	        Question existingQuestion = questionService.getQuestionById(id);
//	        if (existingQuestion != null) {
//	            questionService.deleteQuestion(id);
//	            return ResponseEntity.noContent().build();
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    }

}
