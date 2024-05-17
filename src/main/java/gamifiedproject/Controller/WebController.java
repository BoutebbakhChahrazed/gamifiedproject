package gamifiedproject.Controller;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gamifiedproject.Model.Services.PersonService;
import gamifiedproject.Model.entities.ChangePasswordRequest;

@RestController
//
@AllArgsConstructor
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class WebController {

	 private  PersonService service;

	 
	    @PatchMapping
	    public ResponseEntity<?> changePassword(
	          @RequestBody ChangePasswordRequest request,
	          Principal connectedUser
	    ) {
	        service.changePassword(request, connectedUser);
	        return ResponseEntity.ok().build();
	    }


}

