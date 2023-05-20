package com.bloggingApp.serviceImpl;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.bloggingApp.model.User;
import com.bloggingApp.exception.ResourceNotFoundException;
import com.bloggingApp.model.Category;
import com.bloggingApp.model.Post;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bloggingApp.payload.PostDto;
import com.bloggingApp.payload.PostResponse;
import com.bloggingApp.repository.CategoryRepository;
import com.bloggingApp.repository.PostRepository;
import com.bloggingApp.repository.UserRepository;
import com.bloggingApp.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException ("User", "User Id", userId) );
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(
				() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post postCreated = this.postRepository.save(post);
		
		return this.modelMapper.map(postCreated, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));	
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setAddDate(new Date());	
		post.setImageName(postDto.getImageName());
		Post updatedPost =	this.postRepository.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));	
		this.postRepository.delete(post);
		
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		Sort sort = null;
		if (sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else if (sortDir.equalsIgnoreCase("desc")){
			sort = Sort.by(sortBy).descending();
		}
					
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepository.findAll(p);
		List<Post> postList = pagePost.getContent();
		
		List<PostDto> postDtoList =  postList.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtoList);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));	
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
	
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(
						() -> new ResourceNotFoundException ("category", "category Id", categoryId));
		List<Post> postList = this.postRepository.findByCategory(category);
			List<PostDto> postDtoList = postList.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtoList;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

		User user = this.userRepository.findById(userId).orElseThrow(
						() -> new ResourceNotFoundException ("User", "User Id", userId));
		List<Post> postList = this.postRepository.findByUser(user);
		List<PostDto> postDtoList = postList.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtoList;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
