package jfs.backend.payloads;



import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jfs.backend.repository.RoleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private Integer id;
	@NotEmpty
	@Size(min = 8,message = "User name Must be minimum 4 Charecters")
	private String name;
	@Email(regexp = "^[A-Za-z0-9+_.-]+@gmail\\.com$", message = "Email should be in the format abc@gmail.com")	
	private String email;
	@NotEmpty()
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", 
    message = "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character.")
	@JsonIgnoreProperties
	private String password;
	@NotEmpty()
	private String about;
	
	private Set<RoleDto> roles;
	
}
