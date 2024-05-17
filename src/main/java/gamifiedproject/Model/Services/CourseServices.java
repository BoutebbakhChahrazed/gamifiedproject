package gamifiedproject.Model.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import java.sql.Blob;
import java.sql.SQLException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import gamifiedproject.Model.Repository.CourseRepository;

import gamifiedproject.Model.Repository.LessonRepository;
import gamifiedproject.Model.Repository.SpecialistRepository;
import gamifiedproject.Model.Repository.StudentRepository;
import gamifiedproject.Model.Repository.TeacherRepository;
import gamifiedproject.Model.Repository.scoreRepository;
import gamifiedproject.Model.entities.Answer;
import gamifiedproject.Model.entities.AnswerDTO;
import gamifiedproject.Model.entities.Course;
import gamifiedproject.Model.entities.CourseDTO;
import gamifiedproject.Model.entities.Lesson;
import gamifiedproject.Model.entities.LessonDTO;
import gamifiedproject.Model.entities.Question;
import gamifiedproject.Model.entities.Score;
import gamifiedproject.Model.entities.Specialist;
import gamifiedproject.Model.entities.Student;
import gamifiedproject.Model.entities.Teacher;
import gamifiedproject.token.Token;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CourseServices {
	
	//  addCourseWithLessonsAndGames a repeter
	
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
    private LessonRepository lessonRepository;
    
    @Autowired
    private SpecialistRepository specialistRepository;  
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired 
    private scoreRepository repo;
    
    
    public List<Course> getAllCourses() {
    	
    	
    	List<Course>  courses=courseRepository.findAll();
    	
        for (Course course : courses) {
        	 
                 Blob photoBlob = course.getPhoto();
                 if (photoBlob != null) {
                     try {
                         byte[] photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
                         String photoBase64 = Base64.getEncoder().encodeToString(photoBytes);
                         course.setPhotoBase64(photoBase64);
                     } catch (SQLException e) {
                         // Handle SQL exception
                    	 
                         e.printStackTrace();
                     }
                 }
             }
        
        
        return courses;
    }

    
    public Course getCourseById(Long id) {
        Course course= courseRepository.findById(id).orElse(null);
        if (course!=null) {
        Blob photoBlob = course.getPhoto();
        if (photoBlob != null) {
            try {
                byte[] photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
                String photoBase64 = Base64.getEncoder().encodeToString(photoBytes);
                course.setPhotoBase64(photoBase64);
            } catch (SQLException e) {
                // Handle SQL exception
           	 
                e.printStackTrace();
            }
        }}
        return course;
    }
    
    public Long addCourse(MultipartFile file, String title, String description, String level, String teacherName ,String teacherlastname) throws IOException, SerialException, SQLException {
		// TODO Auto-generated method stub

   	        Course course =new Course();
    	
 	    	
    		   byte[] photoBytes =file.getBytes();
    		   Blob photoBlob =new SerialBlob(photoBytes);
    		  
    		   course.setPhoto(photoBlob);
    		   course.setTitle(title);
    		   course.setDescription(description);
    		   course.setLevel(level);
    		   
    		   course.setTeacher(teacherRepository.findByFirstnameAndLastname(teacherName,teacherlastname));
    		   
    		 
    		       
    	      courseRepository.save(course);
		return course.getId();
	}

   
//    
//    public Course updateCourse(Long courseId, CourseDTO courseDTO) {
//        Optional<Course> optionalCourse = courseRepository.findById(courseId);
//        if (optionalCourse.isPresent()) {
//            Course existingCourse = optionalCourse.get();
//
//            // Update existing course fields with new values
//            BeanUtils.copyProperties(courseDTO, existingCourse, "id", "lessons");
//
//            // Update lessons, contents, and games
//            updateLessons(existingCourse, courseDTO.getLessons());
//
//            // Save the updated course
//            return courseRepository.save(existingCourse);
//        } else {
//        	throw new NotFoundException("Course not found with id: " + courseId);
//        }
//    }
//    private void updateLessons(Course existingCourse, List<LessonDTO> lessonDTOs) {
//        if (lessonDTOs == null) {
//            return;
//        }
//
//        for (LessonDTO lessonDTO : lessonDTOs) {
//            Lesson existingLesson = findExistingLesson(existingCourse, lessonDTO);
//            if (existingLesson != null) {
//                updateContents(existingLesson, lessonDTO.getContents());
//            }
//        }
//    }
//
//    private Lesson findExistingLesson(Course existingCourse, LessonDTO lessonDTO) {
//        for (Lesson lesson : existingCourse.getLessons()) {
//            if (lesson.getTitle().equals(lessonDTO.getTitle())) {
//                return lesson;
//            }
//        }
//        return null;
//    }
//
//    private void updateContents(Lesson lesson, List<ContentDTO> contentDTOs) {
//        if (contentDTOs == null) {
//            return;
//        }
//
//        for (ContentDTO contentDTO : contentDTOs) {
//            Content existingContent = findExistingContent(lesson, contentDTO);
//            if (existingContent != null) {
//                updateGame(existingContent.getGame(), contentDTO);
//            }
//        }
//    }
//
//    private Content findExistingContent(Lesson lesson, ContentDTO contentDTO) {
//        for (Content content : lesson.getContents()) {
//            if (content.getContent_title().equals(contentDTO.getTitle())) {
//                return content;
//            }
//        }
//        return null;
//    }
//
//    private void updateGame(Game game, ContentDTO contentDTO) {
//        if (game != null) {
//            game.setType(contentDTO.getGameType());
//            // Update other game properties if needed
//        }
//    }


   
    public void deleteCourse(Long courseId) {
        // Check if the course exists
        courseRepository.deleteById(courseId);
    }


	public List<Course> searchCourses(String keyword) {
		// TODO Auto-generated method stub
		return courseRepository.search(keyword);
	}

  
	public void addLessonsAndContentAndGames(Long id ,List <LessonDTO> lessonDTOs) throws IOException, SerialException, SQLException {

	    List<Lesson> lessons = new ArrayList<>();

	    for (LessonDTO lessonDTO : lessonDTOs) {
	        Lesson lesson = new Lesson();
	        lesson.setTitle(lessonDTO.getTitle());
	        lesson.setDescription(lessonDTO.getDescription());

	        Question question = new Question();
	        question.setQuestiontitle(lessonDTO.getQuestiontitle());
	        question.setQuestionOption1(lessonDTO.getQuestionOption1());
	        question.setQuestionOption2(lessonDTO.getQuestionOption2());
	        question.setQuestionOption3(lessonDTO.getQuestionOption3());
	        question.setQuestionOption4(lessonDTO.getQuestionOption4());

	        List<Answer> answers = new ArrayList<>();
	        List<AnswerDTO> answerDTOs = lessonDTO.getAnswers();

	        for (AnswerDTO answerDTO : answerDTOs) {
	            Answer answer = new Answer();
	            answer.setText(answerDTO.getCorrectAnswer());
	            answer.setQuestion(question);
	            answers.add(answer);
	        }

	        question.setAnswers(answers);
	        question.setLesson(lesson);

	        lesson.setQuestion(question);
	        lesson.setCourse(courseRepository.findById(id).orElse(null));
	        lessonRepository.save(lesson);
	    }


	   
	}




	  public ArrayList<String> getRecommendedCourses(String token) {
		    // Implement logic to parse the recommended courses string and fetch corresponding courses from the database
		   
		    return studentRepository.findRcomendedCoursesBytoken(token);
		  }


	public ArrayList<Course> findByRecomendedcourses(ArrayList<String> recomendedCourses) {
		// TODO Auto-generated method stub
		ArrayList<Course> courses=new ArrayList<Course>();
		for (String recomended:recomendedCourses) {
		 courses.addAll(courseRepository.findBytitle(recomended));}
		return courses;
	}


	public Long updateCourse(Long id, CourseDTO courseUpdateRequest) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course not found with id: " + id));

        // Update course properties
        existingCourse.setTitle(courseUpdateRequest.getTitle());
        existingCourse.setDescription(courseUpdateRequest.getDescription());
        existingCourse.setLevel(courseUpdateRequest.getLevel());
        existingCourse.setTeacher(teacherRepository.findByFirstnameAndLastname(courseUpdateRequest.getTeacherName(), courseUpdateRequest.getTeacherLastname()));
      

        // Save and return the ID of the updated course
        return courseRepository.save(existingCourse).getId();
    }

	

}
