package gamifiedproject.Model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "score")
@Data
 @AllArgsConstructor
 @NoArgsConstructor
public class Score {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double stars;
	private int score;

//	@JsonIgnore
//	@OneToOne(mappedBy = "score")
//	private Answer answer;

}
