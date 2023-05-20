package com.bloggingApp.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloggingApp.exception.ResourceNotFoundException;
import com.bloggingApp.model.Category;
import com.bloggingApp.payload.CategoryDto;
import com.bloggingApp.repository.CategoryRepository;
import com.bloggingApp.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRespository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category addCategory = this.categoryRespository.save(category);
		return this.modelMapper.map(addCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category category = this.categoryRespository.findById(categoryId).orElseThrow(
					() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		this.categoryRespository.save(category);
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category category = this.categoryRespository.findById(categoryId).orElseThrow(
					() -> new ResourceNotFoundException ("Category", "category Id", categoryId));
		this.categoryRespository.delete(category);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
	
		Category category = this.categoryRespository.findById(categoryId).orElseThrow(
					() -> new ResourceNotFoundException ("Category", "Category Id", categoryId));				
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
	
		List<Category> categoryList = this.categoryRespository.findAll();
		List<CategoryDto> categoryDtoList = categoryList.stream().map(
					(category)->this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());		
		return categoryDtoList;
	}

}
