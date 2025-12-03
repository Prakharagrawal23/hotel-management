package com.restapiProject.hotalMgmt.exception;

//import javax.naming.directory.InvalidSearchControlsException;
//
//import org.springframework.http.ResponseEntity;

public class ResourcesNotFoundException extends RuntimeException {
	public ResourcesNotFoundException(String message) {
		super(message);
	}
	
//	public ResponseEntity<String> handleInvaildSearch(InvalidSearchControlsException ex){
//		return ResponseEntity.badRequest().bo
//	}
}

