package com.mine.openElective.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mine.openElective.Services.UserService;
import com.mine.openElective.model.ElectiveUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/profile")
	public ResponseEntity<ElectiveUser> getUserProfile(@RequestHeader("Authorization") String jwt){
		
		ElectiveUser user = userService.getUserProfile(jwt);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<ElectiveUser>> getUsers(@RequestHeader("Authorization") String jwt){
		
		List<ElectiveUser> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@PutMapping("/enroll")
	public ResponseEntity<ElectiveUser>enrollCourse(@RequestParam String coursename,@RequestHeader("Authorization") String jwt)throws Exception{
		ElectiveUser user=userService.getUserProfile(jwt);
		ElectiveUser userEnroll=userService.enrollCourse(coursename,user.getId(),jwt);
		
		return new ResponseEntity<>(userEnroll,HttpStatus.CREATED);
	}
	
	@PostMapping("/generate-passwords")
    public ResponseEntity<Void> generateRandomPasswords() {
        userService.generateAndSendRandomPasswords();
        return ResponseEntity.ok().build();
    }
	
}
