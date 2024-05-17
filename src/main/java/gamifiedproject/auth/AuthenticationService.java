package gamifiedproject.auth;

import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import gamifiedproject.Email.EmailUtil;
import gamifiedproject.Email.OptUtil;
import gamifiedproject.Model.Repository.PersonRepository;
import gamifiedproject.Model.Services.CourseServices;
import gamifiedproject.Model.Services.JwtService;
import gamifiedproject.Model.entities.Admin;
import gamifiedproject.Model.entities.Person;
import gamifiedproject.Model.entities.Specialist;
import gamifiedproject.Model.entities.Student;
import gamifiedproject.Model.entities.Teacher;
import gamifiedproject.token.Token;
import gamifiedproject.token.TokenRepository;
import gamifiedproject.token.TokenType;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final OptUtil otpUtil;
	private final EmailUtil emailUtil;
	private final PersonRepository personRepository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final CourseServices courseService;

	public AuthenticationResponse register(RegisterRequest request) {
		String otp;

		otp = otpUtil.generateOtp();

		try {
			emailUtil.sendOtpEmail(request.getGmail(), otp);
		} catch (MessagingException e) {
			throw new RuntimeException("Unable to send otp please try again");
		}
		Person user;

		switch (request.getRole()) {
		case STUDENT:
			user = new Student(request.getFirstname(), request.getLastname(), request.getGmail(),
					passwordEncoder.encode(request.getPassword()), otp, LocalDateTime.now(),
					request.getRecomendedcourses(), request.getLevel(), request.getRole());
			break;
		case TEACHER:
			user = new Teacher(request.getFirstname(), request.getLastname(), request.getGmail(),
					passwordEncoder.encode(request.getPassword()), otp, LocalDateTime.now(), request.getTcourses(),
					request.getRole());
			break;
		case ADMIN:
			user = new Admin(request.getFirstname(), request.getLastname(), request.getGmail(),
					passwordEncoder.encode(request.getPassword()), otp, LocalDateTime.now(), request.getRole());
			break;

		case SPECIALIST:
			user = new Specialist(request.getFirstname(), request.getLastname(), request.getGmail(),
					passwordEncoder.encode(request.getPassword()), otp, LocalDateTime.now(), request.getRole());
			break;

		default:
			throw new IllegalArgumentException("Invalid role");
		}

		Person savedUser = personRepository.save(user);
		var jwtToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		saveUserToken(savedUser, jwtToken);
		return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).role(request.getRole())
				.build();
	}
	 public String verifyAccount(String email, String otp) {
		    Person user = personRepository.findByGmail(email)
		        .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		    if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
		        LocalDateTime.now()).getSeconds() < (1 * 60)) {
		      user.setActive(true);
		      personRepository.save(user);
		      return "OTP verified you can login";
		    }
		    return "Please regenerate otp and try again";
		  }

		  public String regenerateOtp(String email) {
		    Person person = personRepository.findByGmail(email)
		        .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		    String otp = otpUtil.generateOtp();
		    try {
		      emailUtil.sendOtpEmail(email, otp);
		    } catch (MessagingException e) {
		      throw new RuntimeException("Unable to send otp please try again");
		    }
		    person.setOtp(otp);
		    person.setOtpGeneratedTime(LocalDateTime.now());
		    personRepository.save(person);
		    return "Email sent... please verify account within 1 minute";
		  }

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getGmail(), request.getPassword()));
		var user = personRepository.findByGmail(request.getGmail()).orElseThrow();
		if (!user.isActive()) {
	        throw new RuntimeException("Your account is not verified");
	    }
		var jwtToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		var role = user.getRole();

		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken);
		return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).role(role).build();
	}

	private void saveUserToken(Person user, String jwtToken) {
		var token = Token.builder().person(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false)
				.revoked(false).build();
		tokenRepository.save(token);
	}

	private void revokeAllUserTokens(Person user) {
		var validUserTokens = tokenRepository.findAllValidTokenByPersonId(user.getId());
		if (validUserTokens.isEmpty())
			return;
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}

	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String userEmail;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return;
		}
		refreshToken = authHeader.substring(7);
		userEmail = jwtService.extractUsername(refreshToken);
		if (userEmail != null) {
			var user = this.personRepository.findByGmail(userEmail).orElseThrow();
			if (jwtService.isTokenValid(refreshToken, user)) {
				var accessToken = jwtService.generateToken(user);
				revokeAllUserTokens(user);
				saveUserToken(user, accessToken);
				var authResponse = AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken)
						.build();
				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
			}
		}
	}

}
