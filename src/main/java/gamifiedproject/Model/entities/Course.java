package gamifiedproject.Model.entities;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course")
 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 @Setter@Getter

public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String title;
	private String description;
	private String level;
	
	
	@JsonIgnore
    @Lob
    private Blob  photo; 
  
    
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ScoreTable_id", referencedColumnName = "id")
	private ScoreTable scoretable;

//	@ManyToOne
//	@JoinColumn(name = "specialist_id")
//	private Specialist specialist;
//	
    
    @Transient 
    private String photoBase64;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	
	   @OneToMany(mappedBy = "course")
	   private List<Progress> progresses;


	@OneToMany(mappedBy = "course")
	private List<Evaluation> evaluations;

	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Lesson> lessons = new ArrayList<>();

	

	public Course(Blob photoBlob, String title, String description, String level) {
		
		this.photo=photoBlob;
		this.title=title;
		this.description=description;
		this.level=level;
			}



	public void setPhotoBase64(String photoBase64) {
		// TODO Auto-generated method stub
		this.photoBase64=photoBase64;
		
	}
	
	

}
