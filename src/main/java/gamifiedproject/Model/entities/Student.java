package gamifiedproject.Model.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gamifiedproject.token.Token;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@Data
//@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
//@Builder
public class Student extends Person{

	
	//for student 
		private String level;
		private ArrayList<String> recomendedcourses;
		
		public Student(String firstname, String lastname, String gmail, String password, 
				String otp,LocalDateTime otpGeneratedTime,ArrayList<String> recomendedcourses,String level, Role role) {
			super(firstname,lastname,gmail,password,otp,otpGeneratedTime,role);
			
			this.recomendedcourses=recomendedcourses;
			this.level = level;
			
		}
		
		
		@OneToMany(mappedBy = "student")
		private  List<Progress> progresses;
		
		@OneToMany(mappedBy = "student")
		private  List<Evaluation> evaluation;
}
