package com.bloggingApp.payload;

import java.util.Date;

import com.bloggingApp.model.Category;
import com.bloggingApp.model.User;

import jakarta.validation.constraints.NotEmpty;


public class PostDto {
	
	
	private Integer postId;
	
	@NotEmpty (message = "Title Can not be empty")
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addDate;
		
	private CategoryDto category;
	
	private UserDto user;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	
	
	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
