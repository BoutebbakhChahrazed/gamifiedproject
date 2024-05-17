package gamifiedproject.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gamifiedproject.Model.entities.Score;

@Repository
public interface scoreRepository extends JpaRepository<Score,Long>{

}
