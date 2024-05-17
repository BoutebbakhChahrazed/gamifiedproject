package gamifiedproject.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamifiedproject.Model.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher ,Long>{
	
	
	
	Teacher findByFirstnameAndLastname(String teacherName, String teacherlastname);

}
