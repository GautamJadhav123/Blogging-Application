package com.bloggingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloggingApp.model.Category;

public interface CategoryRepository  extends JpaRepository <Category, Integer>{

}
