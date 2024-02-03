package jfs.backend.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	private Integer postId;
	@NotEmpty(message = "Title Can not be Empty")
	@Size(min = 25,max = 50)
	private String title;
	@NotEmpty(message = "Content Can not be Empty")
	@Size(min = 25,max = 50)
	private String contents;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private List<CommentDto> comments=new ArrayList<>();
	

}
