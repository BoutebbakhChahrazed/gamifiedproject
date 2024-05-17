package gamifiedproject.Model.entities;



import org.springframework.security.core.authority.SimpleGrantedAuthority;

import gamifiedproject.Model.entities.Persmission;



import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.*;

@RequiredArgsConstructor

public enum Role {

  USER(Collections.emptySet()),
  ADMIN(Set.of(
		  Persmission.ADMIN_READ,   
		  Persmission.ADMIN_UPDATE,
		  Persmission.ADMIN_DELETE,
		  Persmission.ADMIN_CREATE)),
 TEACHER(Set.of(
				  Persmission.TEACHER_READ)),
 STUDENT(Set.of(
				  Persmission.STUDENT_READ)),
 SPECIALIST(Set.of(
				  Persmission.SPECIALIST_READ,
				  Persmission.SPECIALIST_UPDATE,
				  Persmission.SPECIALIST_DELETE,
				  Persmission.SPECIALIST_CREATE))
		  
         ;

  

 @Getter
  private final Set<Persmission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}

