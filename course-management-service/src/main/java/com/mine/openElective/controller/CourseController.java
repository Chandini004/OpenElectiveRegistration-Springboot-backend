package com.mine.openElective.controller;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mine.openElective.model.Course;
import com.mine.openElective.model.ElectiveUserDto;
import com.mine.openElective.service.CourseService;
import com.mine.openElective.service.UserService;

@RestController
@RequestMapping("api/courses")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Course> createCourse(@RequestBody Course course,@RequestHeader("Authorization") String jwt) throws Exception{
		
		
		ElectiveUserDto user = userService.getUserProfile(jwt);
		Course createdCourse = courseService.createCourse(course, user.getRole());
		
		return new ResponseEntity<>(createdCourse,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception{
		
		
//		UserDto user = userService.getUserProfile(jwt);
		Course course = courseService.getCourseById(id);
		
		return new ResponseEntity<>(course,HttpStatus.OK);
	}
	@GetMapping("/course")
	public ResponseEntity<Course> getCourseByCoursename(@RequestParam String coursename,@RequestHeader("Authorization") String jwt) throws Exception{
		
		
//		UserDto user = userService.getUserProfile(jwt);
		Course course = courseService.getCourseByCoursename(coursename);
		
		return new ResponseEntity<>(course,HttpStatus.OK);
	}
	
	
	@GetMapping()
	public ResponseEntity<List<Course>> getAllCourses(@RequestHeader("authorization") String jwt) throws Exception{
		
		
		ElectiveUserDto user = userService.getUserProfile(jwt);
		
		List<Course> courses = courseService.getAllCourses();
		
		
		return new ResponseEntity<>(courses,HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}/update")
	public ResponseEntity<Course> updateCourse(@PathVariable Long id,@RequestBody Course req,@RequestHeader("authorization") String jwt) throws Exception{
		
		
		ElectiveUserDto user = userService.getUserProfile(jwt);
		
		Course courses = courseService.updateCourse(id, req, user.getRole());
		
		
		return new ResponseEntity<>(courses,HttpStatus.OK);
	}
	@PutMapping("/{coursename}/{branch}/updatecount")
	public ResponseEntity<Void> updateBranchCount(@PathVariable String coursename, @PathVariable String branch, @RequestParam Long count, @RequestHeader("authorization") String jwt) throws Exception {
		
//		ElectiveUserDto user = userService.getUserProfile(jwt);
		
//		if (!user.getRole().equals("admin")) {
//			throw new Exception("Only admin can update branch count");
//		}
//		
		courseService.updateBranchCount(coursename, branch, count);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id) throws Exception{		
		
		courseService.deleteCourse(id);		
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
