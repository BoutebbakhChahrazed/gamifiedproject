package gamifiedproject.audit;


import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import gamifiedproject.Model.entities.Person;

public class ApplicationAuditAware implements AuditorAware<Long> {
	 @Override
	    public Optional<Long> getCurrentAuditor() {
	        Authentication authentication =
	                SecurityContextHolder
	                        .getContext()
	                        .getAuthentication();
	        if (authentication == null ||
	            !authentication.isAuthenticated() ||
	                authentication instanceof AnonymousAuthenticationToken
	        ) {
	            return Optional.empty();
	        }

	        Person userPrincipal = (Person) authentication.getPrincipal();
	        return Optional.ofNullable(userPrincipal.getId());
	    }

}
