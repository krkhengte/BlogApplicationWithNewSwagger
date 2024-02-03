package jfs.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jfs.backend.entity.Category;
import jfs.backend.entity.Comment;
import jfs.backend.entity.Post;
import jfs.backend.entity.User;
import jfs.backend.exceptions.ResourceNotFoundException;
import jfs.backend.payloads.CommentDto;
import jfs.backend.payloads.PostDto;
import jfs.backend.payloads.PostResponse;
import jfs.backend.repository.CategoryRepo;
import jfs.backend.repository.CommentRepo;
import jfs.backend.repository.PostRepo;
import jfs.backend.repository.UserRepo;
import jfs.backend.service.PostService;


@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private CommentRepo commentRepo;

	// Create Post

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName(postDto.getImageName());
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post postCreated = this.postRepo.save(post);

		return this.modelMapper.map(postCreated, PostDto.class);
	}

	// Update Post

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		post.setAddedDate(new Date());
		post.setTitle(postDto.getTitle());
		post.setImageName(postDto.getImageName());
		post.setContents(postDto.getContents());
		post.setComments(post.getComments());

		this.postRepo.save(post);

		PostDto postDto2 = this.modelMapper.map(post, PostDto.class);

		return postDto2;
	}

	// Delete Post

	@Override
	public String deletePost(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("postId", "postId", postId));

		postRepo.delete(post);

		return "Post Deleted Successfully..";
	}

	// Get All Post

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = null;

		if (sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {

			sort = Sort.by(sortBy).descending();
		}

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = this.postRepo.findAll(p);

		List<Post> allPost = pagePost.getContent();

		List<PostDto> postDto = allPost.stream().map((posts) -> this.modelMapper.map(posts, PostDto.class))
				.collect(Collectors.toList());

		// System.out.println(postDto);

		PostResponse pr = new PostResponse();
		pr.setPostCcontent(postDto);
		pr.setPageNumber(pagePost.getNumber());
		pr.setPageSize(pagePost.getSize());
		pr.setTotalElements(pagePost.getTotalElements());
		pr.setTotalPages(pagePost.getTotalPages());
		pr.setLastPage(pagePost.isLast());

		return pr;
	}

	// Get Post By Id

	@Override
	public PostDto getPostById(Integer postId) {

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

		PostDto postDto = this.modelMapper.map(post, PostDto.class);

		return postDto;
	}

	// Get All Post By Category

	@Override
	public PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {

		Category category = this.categoryRepo.findById(categoryId)

				.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

		Pageable p = PageRequest.of(pageNumber, pageSize);

		Page<Post> pagePost = this.postRepo.findByCategoryCategoryId(categoryId, p);

		List<Post> allPost = pagePost.getContent();

		List<PostDto> postDto = allPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse pr = new PostResponse();

		pr.setPostCcontent(postDto);
		pr.setPageNumber(pagePost.getNumber());
		pr.setPageSize(pagePost.getSize());
		pr.setTotalElements(pagePost.getTotalElements());
		pr.setTotalPages(pagePost.getTotalPages());
		pr.setLastPage(pagePost.isLast());

		return pr;
	}

	// Get All Post By User

	@Override
	public PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		Pageable p = PageRequest.of(pageNumber, pageSize);

		Page<Post> pagePost = this.postRepo.findByUserId(userId, p);

		List<Post> allPost = pagePost.getContent();

		List<PostDto> postDto = allPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse pr = new PostResponse();

		pr.setPostCcontent(postDto);
		pr.setPageNumber(pagePost.getNumber());
		pr.setPageSize(pagePost.getSize());
		pr.setTotalElements(pagePost.getTotalElements());
		pr.setTotalPages(pagePost.getTotalPages());
		pr.setLastPage(pagePost.isLast());

		return pr;

	}

	// Search Post

	@Override
	public List<PostDto> searchPost(String keyword) {

		List<Post> posts = this.postRepo.findByTitleContaining(keyword);

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public PostResponse getAllCommentByPost(Integer postId, Integer pageNumber, Integer pageSize) {

		Pageable p = PageRequest.of(pageNumber, pageSize);

		Page<Comment> commentsList = this.commentRepo.findAllByPostPostId(postId, p);

		// Page<Post> pagePost = this.postRepo.findAll(p);

		List<Comment> allComments = commentsList.getContent();

		List<CommentDto> postDto = allComments.stream().map((posts) -> this.modelMapper.map(posts, CommentDto.class))
				.collect(Collectors.toList());

		// System.out.println(postDto);

		PostResponse pr = new PostResponse();
		pr.setCommentContent(postDto);
		pr.setPageNumber(commentsList.getNumber());
		pr.setPageSize(commentsList.getSize());
		pr.setTotalElements(commentsList.getTotalElements());
		pr.setTotalPages(commentsList.getTotalPages());
		pr.setLastPage(commentsList.isLast());

		return pr;

	}

}
