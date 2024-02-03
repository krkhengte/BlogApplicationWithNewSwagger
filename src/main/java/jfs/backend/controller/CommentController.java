
package jfs.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jfs.backend.payloads.CommentDto;
import jfs.backend.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("post/{postId}/user/{userId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId,@PathVariable Integer userId) {

		CommentDto createComment = this.commentService.createComment(commentDto, postId,userId);

		return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
	}

	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<String> deleteComments(@PathVariable Integer commentId) {

		String deleteComment = this.commentService.deleteComment(commentId);

		return new ResponseEntity<String>(deleteComment, HttpStatus.OK);

	}

	@GetMapping("/getAllComments")
	public ResponseEntity<List<CommentDto>> getAllComments() {

		List<CommentDto> allComments = this.commentService.getAllComments();
		return new ResponseEntity<List<CommentDto>>(allComments, HttpStatus.OK);
	}

	@DeleteMapping("/user/{userId}/post/{postId}/comment/{commentId}")
	public ResponseEntity<String> deleteCommentsByUserAndPostId(Integer userId,
			Integer postId, Integer commentId) {

		String delete = this.commentService.deleteCommentByUserIdAndPostId(userId, postId,commentId);

		return new ResponseEntity<String>(delete, HttpStatus.OK);
	}
}
