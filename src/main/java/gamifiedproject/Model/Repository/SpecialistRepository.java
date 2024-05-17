package gamifiedproject.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamifiedproject.Model.entities.Specialist;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist ,Long> {

}
