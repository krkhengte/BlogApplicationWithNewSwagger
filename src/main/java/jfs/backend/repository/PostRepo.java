package jfs.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jfs.backend.entity.Category;
import jfs.backend.entity.Post;
import jfs.backend.entity.User;

@EnableJpaRepositories
public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
		
	List<Post> findByCategory(Category category);
	
	Page<Post> findByCategoryCategoryId(Integer categoryId, Pageable pageable);
	
	Page<Post> findByUserId(Integer userId, Pageable pageable);
	
	List<Post> findByTitleContaining(String title);
	
	Page<Post> findByCommentsId(Integer commentId, Pageable pageable);
}
