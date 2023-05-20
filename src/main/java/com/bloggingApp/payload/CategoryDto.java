package com.bloggingApp.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDto {
	
	private Integer categoryId;
	
	@NotEmpty (message = "title can not be empty")
	@Size (min = 3, message = "title must be contain at least 3 letters")
	private String categoryTitle;
	
	@NotEmpty (message = "description can not be empty")
	@Size (min = 10, message = "description must contain more than 10 characters")
	private String categoryDescription;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public CategoryDto(Integer categoryId, String categoryTitle, String categoryDescription) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
	}

	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
}
