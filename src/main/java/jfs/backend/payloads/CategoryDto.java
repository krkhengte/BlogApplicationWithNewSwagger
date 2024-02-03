package jfs.backend.payloads;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	
	private Integer categoryId;
	@NotEmpty
	@Size(min = 4)
	private String categoryTitle;
	@NotEmpty
	@Size(max = 24)
	private String categoryDiscription;
}
