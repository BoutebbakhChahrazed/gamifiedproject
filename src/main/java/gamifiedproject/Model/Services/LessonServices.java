package gamifiedproject.Model.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamifiedproject.Model.Repository.LessonRepository;
import gamifiedproject.Model.entities.Lesson;

@Service
public class LessonServices {
	
	@Autowired
	private LessonRepository lessonRepository;

	public List<Lesson> getLessonsByIdCourse(Long id) {
		// TODO Auto-generated method stub
		return lessonRepository.getLessonsByCourseId( id);
	}
	
	

}
