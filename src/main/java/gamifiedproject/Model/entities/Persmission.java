package gamifiedproject.Model.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Persmission {

	
	    ADMIN_READ("admin:read"),
	    ADMIN_UPDATE("admin:update"),
	    ADMIN_CREATE("admin:create"),
	    ADMIN_DELETE("admin:delete"),
	    TEACHER_READ("teacher:read"),
	    STUDENT_READ("student:read"),
	    SPECIALIST_READ("specialist:read"),
	    SPECIALIST_UPDATE("specialist:update"),
	    SPECIALIST_CREATE("specialist:create"),
	    SPECIALIST_DELETE("specialist:delete")
	    
	  ;

	    @Getter
	    private final String permission;
}
