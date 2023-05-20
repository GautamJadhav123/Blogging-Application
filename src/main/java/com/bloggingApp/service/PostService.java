package com.bloggingApp.service;

import java.util.List;

import com.bloggingApp.payload.PostDto;
import com.bloggingApp.payload.PostResponse;

public interface PostService {
	
	//create post
	PostDto createPost (PostDto postDto, Integer userId, Integer categoryId);
	
	
	//update post
	PostDto updatePost (PostDto post, Integer postId);
	
	
	//delete post
	void deletePost (Integer postId);
	
	
	//get all post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	
	//get single post
	PostDto getPostById(Integer postId);
	
	
	//get all post by category
	List<PostDto> getPostsByCategory (Integer categoryId);
	
	
	//get all post by user
	List<PostDto> getPostsByUser (Integer userId);
	
	
	//search all post by a keyword
	List<PostDto> searchPosts (String keyword);
	
	
	
	
}
