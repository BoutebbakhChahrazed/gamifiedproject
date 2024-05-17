package gamifiedproject.Model.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "evaluation")
@Data
// @AllArgsConstructor
// @NoArgsConstructor
public class Evaluation {
	@EmbeddedId
	private EvaluationId id;

	@ManyToOne
	@MapsId("student_id")
	private Student student;

	@ManyToOne
	@MapsId("course_id")
	private Course course;

	// Additional attributes

	private double starsnumber;
	private String comment;

}
