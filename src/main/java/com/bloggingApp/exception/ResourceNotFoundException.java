package com.bloggingApp.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
	
	String resourceName;
	String fieldName;
	Integer fieldValue;
	
	public ResourceNotFoundException (String resouceName, String fieldName, Integer fieldValue) {
		super(String.format("%s is not found with %s : %d", resouceName, fieldName, fieldValue));
		this.resourceName = resouceName;
		this.fieldName 	= fieldName;
		this.fieldValue = fieldValue;
	}

}
