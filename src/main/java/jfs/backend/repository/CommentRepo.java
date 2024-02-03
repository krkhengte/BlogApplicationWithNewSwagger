
  package jfs.backend.repository;
  
  import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
  
  import jfs.backend.entity.Comment;
import jfs.backend.entity.Post;
import jfs.backend.payloads.PostResponse;
  
  public interface CommentRepo extends JpaRepository<Comment, Integer> {
  
	  Page<Post> findByPostPostId(Integer postId,Pageable pageable);
	  
	  Page<Comment> findAllByPostPostId(Integer postId,Pageable pageable);
	  
	  List<Comment> findByPostPostIdAndUserId(Integer postId,Integer userId);
	  
  }
 