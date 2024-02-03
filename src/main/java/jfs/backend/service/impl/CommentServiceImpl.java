
package jfs.backend.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jfs.backend.entity.Comment;
import jfs.backend.entity.Post;
import jfs.backend.entity.User;
import jfs.backend.exceptions.ResourceNotFoundException;
import jfs.backend.payloads.CommentDto;
import jfs.backend.repository.CommentRepo;
import jfs.backend.repository.PostRepo;
import jfs.backend.repository.UserRepo;
import jfs.backend.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		
		
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);

		comment.setPost(post);
		
		comment.setUser(user);

		Comment saveComment = this.commentRepo.save(comment);

		return this.modelMapper.map(saveComment, CommentDto.class);

	}

	@Override
	public String deleteComment(Integer commentId) {

		Comment comment = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		this.commentRepo.delete(comment);

		return "Comment Deleted";
	}

	@Override
	public List<CommentDto> getAllComments() {

		List<Comment> comment = this.commentRepo.findAll();

		List<CommentDto> commentList = comment.stream().map((comm) -> this.modelMapper.map(comm, CommentDto.class))
				.collect(Collectors.toList());

		return commentList;
	}

	@Override
	public String deleteCommentByUserIdAndPostId(Integer userId, Integer postId, Integer commentId) {
	
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "commentId", commentId));
		
		List<Comment> comments = this.commentRepo.findByPostPostIdAndUserId(postId, userId);
		
		Optional<List<Comment>> comments1 =Optional.ofNullable(comments);
		
		comments1.orElseThrow(()-> new ResourceNotFoundException("User", "userID", userId));
		comments1.orElseThrow(()->new ResourceNotFoundException("Post", "postId", postId));
		
		
		if(comments.contains(comment)) {
		
			this.commentRepo.delete(comment);
			
		}else {
			
			throw new ResourceNotFoundException("Comment", "commentId", commentId);
		}
		
		
		
		return "Comment Deleted";
	}

}
