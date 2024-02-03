package jfs.backend.payloads;

import java.util.List;

import jfs.backend.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse	 {

	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;
	private List<PostDto> postCcontent;
	private List<CommentDto> commentContent;
	
}
