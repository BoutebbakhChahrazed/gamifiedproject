package gamifiedproject.Model.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gamifiedproject.token.Token;
import jakarta.persistence.CascadeType;
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
@Table(name = "specialist")
@Data
//@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
//@Builder
public class Specialist extends Person {
	
	public Specialist(String firstname,String lastname,String gmail,String password,String otp,LocalDateTime otpGeneratedTime,Role role) {
		super(firstname,lastname,gmail,password,otp,otpGeneratedTime,role);
	}
}
//	@JsonIgnore
//    @OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL)
//	private List<Course> courses;
//}
