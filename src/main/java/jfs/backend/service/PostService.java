package jfs.backend.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import jfs.backend.entity.Post;
import jfs.backend.payloads.PostDto;
import jfs.backend.payloads.PostResponse;

@Repository
public interface PostService{

	// Create
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	// Update Post
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	// Delete Post
	
	String deletePost(Integer postId);
	
	//Get All Post
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
	
	// Get post by Id
	
	PostDto getPostById(Integer postId);
	
	// Get post by Category
	
	PostResponse getPostByCategory(Integer categoryId,Integer pageNumber, Integer pageSize);
	
	// Get post by User
	
	PostResponse getPostByUser(Integer userId,Integer pageNumber, Integer pageSize);
	
	// Searvh Post
	
	List<PostDto> searchPost(String keyword);
	
	PostResponse getAllCommentByPost(Integer postId,Integer pageNumber, Integer pageSize);
	
}
