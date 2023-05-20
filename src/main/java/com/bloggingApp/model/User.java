package com.bloggingApp.model;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;



@Entity
@Table (name = "users")
public class User {

@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
@Column (name = "user_Id")
private Integer userId;

private String name;

private String email;

private String password;

private String about;


@OneToMany (mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
private List<Post> posts = new ArrayList<>();


public Integer getUserId() {
	return userId;
}

public void setUserId(Integer userId) {
	this.userId = userId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getAbout() {
	return about;
}

public void setAbout(String about) {
	this.about = about;
}

public List<Post> getPosts() {
	return posts;
}

public void setPosts(List<Post> posts) {
	this.posts = posts;
}



public User(Integer userId, String name, String email, String password, String about) {
	super();
	this.userId = userId;
	this.name = name;
	this.email = email;
	this.password = password;
	this.about = about;
}

public User() {
	super();
	// TODO Auto-generated constructor stub
}



	
}
