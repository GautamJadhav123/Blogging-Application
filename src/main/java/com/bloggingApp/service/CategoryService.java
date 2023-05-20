package com.bloggingApp.service;

import java.util.List;

import com.bloggingApp.payload.CategoryDto;

public interface CategoryService {

	
	//create category
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update category
	CategoryDto updateCategory (CategoryDto categoryDto, Integer categoryId);
	
	//delete category
	void deleteCategory (Integer categoryId);
	
	//get single category
	CategoryDto getCategoryById(Integer categoryId);
	
	//get all categories
	List<CategoryDto> getAllCategories();
	
	
}
