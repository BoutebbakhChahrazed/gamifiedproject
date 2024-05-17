package gamifiedproject.Controller;


import io.swagger.v3.oas.annotations.Hidden;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import gamifiedproject.Model.Services.PersonService;
import gamifiedproject.Model.entities.Person;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
   
	@Autowired
	private PersonService personService;
	
    @GetMapping("/Users")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }
    
    
    @GetMapping("/User/{id}")
    @PreAuthorize("hasAuthority('admin:read')")
    public Person getPersonById(@PathVariable("id") Long id) {
        return personService.getPersonById(id);
    }
    
    
    @PostMapping("/addUser")
    @PreAuthorize("hasAuthority('admin:create')")
    @Hidden
    public Person addPerson(@RequestBody Person person) {
        return personService.addPerson(person);
    }
    
    
    @PutMapping("/updateUser/{id}")
    @PreAuthorize("hasAuthority('admin:update')")
    @Hidden
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }
    
    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public void deletePerson(@PathVariable("id") Long id){
        personService.deletePerson(id);
    }
    
    @GetMapping("/search/{keyword}")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Person> Search(@PathVariable("keyword") String keyword) {
        return personService.searchPersons(keyword);
    }
    

    
    
 



}

