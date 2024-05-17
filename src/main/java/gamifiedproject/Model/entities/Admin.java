package gamifiedproject.Model.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gamifiedproject.token.Token;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admin")
@Data
//@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
//@Builder
public class Admin  extends Person{

	public Admin(String firstname,String lastname,String gmail,String password,String otp,LocalDateTime otpGeneratedTime,Role role) {
		super(firstname,lastname,gmail,password, otp, otpGeneratedTime,role);
	}
}
