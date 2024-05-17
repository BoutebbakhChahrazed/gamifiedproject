package gamifiedproject.Model.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gamifiedproject.Model.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

	

	@Query(value = "select c from Course c where  c.title like %:keyword% or c.description like %:keyword%  or c.level like %:keyword% ")
	List<Course> search(@Param("keyword") String keyword);
 
	@Query(value = "select c from Course c where  c.title like :recomended ")
	ArrayList<Course> findBytitle(@Param("recomended") String recomended);

	
	
	

}
