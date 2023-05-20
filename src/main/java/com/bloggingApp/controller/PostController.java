package com.bloggingApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingApp.payload.ApiResponse;
import com.bloggingApp.payload.PostDto;
import com.bloggingApp.payload.PostResponse;
import com.bloggingApp.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/blogging-api/post")
public class PostController {

		@Autowired
		private PostService postService;
	
		//creating Post
		@PostMapping ("/{userId}/{categoryId}/create")
		public ResponseEntity<PostDto> createPost (@Valid @RequestBody PostDto postDto,
											   @PathVariable Integer userId,
											   @PathVariable Integer categoryId){
		
			PostDto postCreated = this.postService.createPost(postDto, userId, categoryId);
			return new ResponseEntity<PostDto> (postCreated, HttpStatus.CREATED	);
		}
	
		//fetch post by category
		@GetMapping ("/getbycategory/{categoryId}")
			public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		
			List<PostDto> postList = this.postService.getPostsByCategory(categoryId);
			return new ResponseEntity<> (postList, HttpStatus.OK);
		}	
	
	
		//fetch post by user
		@GetMapping ("/getbyuser/{userId}")
		public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
			
			List<PostDto> postList = this.postService.getPostsByUser(userId);
			return new ResponseEntity<> (postList, HttpStatus.OK);
		}
		
		//fetch all posts
		@GetMapping ("/getall")
		public ResponseEntity<PostResponse> getAllPosts(
				@RequestParam (value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
				@RequestParam (value = "pageSize", defaultValue = "3", required = false) Integer pageSize,
				@RequestParam (value = "sortBy", defaultValue = "postId", required = false) String sortBy,
				@RequestParam (value = "sortDir", defaultValue = "asc", required = false) String sortDir){
			
			PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
			return new ResponseEntity<> (postResponse, HttpStatus.OK);
		}
	
		//fetch post by post id
		@GetMapping ("/getpost/{postId}")
		public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
			
			PostDto post = this.postService.getPostById(postId);
			return new ResponseEntity<> (post, HttpStatus.OK);					
		}
	
		//delete post by post Id
		@DeleteMapping ("/delete/{postId}")
		public ResponseEntity<ApiResponse> deletePost (@PathVariable Integer postId){
			
			this.postService.deletePost(postId);
			return new ResponseEntity<> (new ApiResponse("Post deleted Succesfully", true), HttpStatus.OK);
		}
	
		//update post
		@PutMapping ("/update/{postId}")
		public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId, @Valid @RequestBody PostDto postDto){
			
			PostDto postUpdated = this.postService.updatePost(postDto, postId);
			return ResponseEntity.ok(postUpdated);
		}
		
	
}
