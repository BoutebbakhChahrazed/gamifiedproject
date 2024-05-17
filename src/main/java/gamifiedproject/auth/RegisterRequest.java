package gamifiedproject.auth;


import java.util.ArrayList;
import java.util.List;

import gamifiedproject.Model.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	private ArrayList<String> tcourses;
  private String firstname;
  private String gmail;
  private String lastname;
   private String level;
  private String password;
  private  ArrayList<String> recomendedcourses;
  
  private Role role;
}

