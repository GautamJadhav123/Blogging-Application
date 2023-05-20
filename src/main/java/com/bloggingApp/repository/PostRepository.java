package com.bloggingApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingApp.model.Category;
import com.bloggingApp.model.Post;
import com.bloggingApp.model.User;
import com.bloggingApp.payload.PostDto;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category); 
	
}
