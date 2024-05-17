package gamifiedproject.Model.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import gamifiedproject.token.Token;


import jakarta.persistence.*;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Person implements UserDetails{
	@Id
	@GeneratedValue
	private Long id;
	private String firstname;
	private String lastname;

	
	@Column(unique = true, length = 45)
	private String gmail;
	private String password;
	
    @Enumerated(EnumType.STRING)
	private Role role;
    
    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private List<Token> tokens;
    
    private boolean active;
    private String otp;
    private LocalDateTime otpGeneratedTime;
    

    public Person(String firstname, String lastname, String gmail, String password, 
			String otp,LocalDateTime otpGeneratedTime, Role role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		
		this.gmail = gmail;
		this.password = password;
		this.otp=otp;
		this.otpGeneratedTime=otpGeneratedTime;
		this.role = role;
	}
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		 
	        return role.getAuthorities();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true ;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return gmail;
	}
	 @Override
	    public String getPassword() {
	        return password;
	    }

	

}
