package gamifiedproject.Model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamifiedproject.Model.entities.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {

	List<Lesson> getLessonsByCourseId(Long id);

}
