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
import org.springframework.web.bind.annotation.RestController;

import com.bloggingApp.payload.ApiResponse;
import com.bloggingApp.payload.CategoryDto;
import com.bloggingApp.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/blogging-api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	//creating category
	@PostMapping ("/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto category = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto> (category, HttpStatus.CREATED);
	}
	
	//updating category
	@PutMapping ("/update/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory (@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
		CategoryDto category = this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto> (category, HttpStatus.OK);		
	}
	
	
	//deleting category
	@DeleteMapping ("/delete/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse> (new ApiResponse("category is deleted successfully", true), HttpStatus.OK);
	}
	
	//get single category
	@GetMapping ("/getcategory/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
		CategoryDto category = this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto> (category, HttpStatus.OK);
	}
	
	//get all categories
	@GetMapping ("/getallcategory")
	public ResponseEntity<List<CategoryDto>> getAllCategories (){
	 	List<CategoryDto> categoryList = this.categoryService.getAllCategories();
	 	return new ResponseEntity<List<CategoryDto>> (categoryList, HttpStatus.OK);
	}
	
	
	
	
}
