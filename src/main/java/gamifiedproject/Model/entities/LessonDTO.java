package gamifiedproject.Model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class LessonDTO {
	
	private String title;
    private String description;
    private String questiontitle;
	private String questionOption1;
	private String questionOption2;
	private String questionOption3;
	private String questionOption4;
	
	private List<AnswerDTO> answers;
    
}
