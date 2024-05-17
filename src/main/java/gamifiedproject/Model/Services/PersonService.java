package gamifiedproject.Model.Services;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


import gamifiedproject.Model.Repository.PersonRepository;
import gamifiedproject.Model.entities.ChangePasswordRequest;
import gamifiedproject.Model.entities.Person;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class PersonService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private PersonRepository repository;

	public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

		var user = (Person) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

		// check if the current password is correct
		if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
			throw new IllegalStateException("Wrong password");
		}
		// check if the two new passwords are the same
		if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
			throw new IllegalStateException("Password are not the same");
		}

		// update the password
		user.setPassword(passwordEncoder.encode(request.getNewPassword()));

		// save the new password
		repository.save(user);
	}

	public List<Person> getAllPersons() {
		return repository.findAll();
	}

	public Person getPersonById(Long id) {
		return repository.findById(id).orElseThrow();
	}

	public Person addPerson(Person person) {
		return repository.save(person);
	}

	public Person updatePerson(Long id, Person person) {
		Person existingPerson = getPersonById(id);
		existingPerson.setFirstname(person.getFirstname());
		existingPerson.setLastname(person.getLastname());
		existingPerson.setGmail(person.getGmail());
		existingPerson.setPassword(person.getPassword());
		existingPerson.setRole(person.getRole());
		return repository.save(existingPerson);
	}

	public void deletePerson(Long id) {
		repository.deleteById(id);
	}
	public List<Person> searchPersons(String keyword) {

		return repository.findByKeywords(keyword);
	}

	public Optional<Person> findByUsername(String gmail) {
		// TODO Auto-generated method stub
		return repository.findByGmail(gmail);
	}

}
