package gamifiedproject.Model.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import gamifiedproject.Model.entities.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person,Long>{

	Optional<Person> findByGmail(String gmail);
	
	@Query(value = "select p from Person p where  p.firstname like %:keyword% or p.lastname like :keyword  ")
	public List<Person> findByKeywords(@Param("keyword") String keyword);


}
