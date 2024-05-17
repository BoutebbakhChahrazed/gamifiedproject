package gamifiedproject.Model.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Embeddable
public class ProgressId implements Serializable {
	
	
	@Column(name = "student_id")
    private Long studentId;

    @Column(name = "course_id")
    private Long courseId;

//    private Long student_id;
//	
//    private Long course_id;

}
