package gamifiedproject.Model.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "progress")
@Data
// @AllArgsConstructor
// @NoArgsConstructor
public class Progress {
	@EmbeddedId
	private ProgressId id;
	

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id" )
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id" )
    private Course course;


	// Additional attributes
	private double progressAmount;

}
