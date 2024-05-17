package gamifiedproject.Model.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gamifiedproject.Model.entities.Course;
import gamifiedproject.Model.entities.Student;
import gamifiedproject.token.Token;

@Repository
public interface StudentRepository extends JpaRepository<Student ,Long>{

	
	 @Query("SELECT s.recomendedcourses FROM Student s JOIN s.tokens t WHERE t.token = :token")  
    ArrayList<String> findRcomendedCoursesBytoken(@Param("token") String token);
	

}
