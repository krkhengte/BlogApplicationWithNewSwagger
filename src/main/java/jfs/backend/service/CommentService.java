

  package jfs.backend.service;
  
  import java.util.List;

import jfs.backend.entity.Comment;
import jfs.backend.payloads.CommentDto;
  
  public interface CommentService {
  
  CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);
  
  String deleteComment(Integer commentId);
  
  List<CommentDto> getAllComments();
  
	String deleteCommentByUserIdAndPostId(Integer userId, Integer postId,Integer commentId); 
  
  }
 