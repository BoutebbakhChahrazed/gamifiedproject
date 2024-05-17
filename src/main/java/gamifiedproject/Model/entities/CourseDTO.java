package gamifiedproject.Model.entities;

import java.util.ArrayList;
import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDTO {

	    
	    private String title;
	    private String description;
	    private String level;
	    private String teacherName;
	    private String teacherLastname;

	   
}
