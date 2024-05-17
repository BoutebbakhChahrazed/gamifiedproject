package gamifiedproject.token;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

	@Query(value = """
		      select t from Token t inner join User u\s
		      on t.user.id = u.id\s
		      where u.id = :id and (t.expired = false or t.revoked = false)\s
		      """)
  List<Token> findAllValidTokenByPersonId( Long id);
	
  Optional<Token> findByToken(String token);
  
}