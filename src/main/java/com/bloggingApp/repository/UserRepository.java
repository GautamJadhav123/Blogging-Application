package com.bloggingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloggingApp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
