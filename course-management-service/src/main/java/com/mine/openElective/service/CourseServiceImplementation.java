package com.mine.openElective.service;

import org.springframework.stereotype.Service;

import com.mine.openElective.model.Course;
import com.mine.openElective.repository.CourseRepository;

import java.util.List;

import java.lang.Exception;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class CourseServiceImplementation implements CourseService {
	@Autowired
	private CourseRepository courseRepository;
	
	
	@Override
	public Course createCourse(Course course, String requesterRole) throws Exception {
		if(!requesterRole.equals(("admin"))) {
			throw new Exception("only admin can create task");
		}
		return courseRepository.save(course);
	}
	
	@Override
	public Course getCourseById(Long id) throws Exception {
		
		return courseRepository.findById(id).orElseThrow(()->new Exception("task not found with id "+id));
	}
	
	@Override
	public Course getCourseByCoursename(String coursename) throws Exception {
		
		return courseRepository.findByCoursename(coursename);
	}
	

	@Override
	public List<Course> getAllCourses() {
		List<Course> allCourses = courseRepository.findAll();
		
		
		return allCourses;
	}
	
	@Override
	public Course updateCourse(Long id, Course updatedCourse, String requesterRole) throws Exception {
		if(!requesterRole.equals(("admin"))) {
			throw new Exception("only admin can create task");
		}
		Course existingCourse = getCourseById(id);
		
		if(updatedCourse.getCoursename()!=null) {
			existingCourse.setCoursename(updatedCourse.getCoursename());
		}
		if(updatedCourse.getCoursebranch()!=null) {
			existingCourse.setCoursebranch(updatedCourse.getCoursebranch());
		}
		if(updatedCourse.getTotalcount()!=null) {
			existingCourse.setTotalcount(updatedCourse.getTotalcount());
		}
		
		return courseRepository.save(existingCourse);
	}

	@Override
	public void deleteCourse(Long id) throws Exception {
		// TODO Auto-generated method stub
		getCourseById(id);
		
		courseRepository.deleteById(id);
	}
	
	@Override
	public void updateBranchCount(String coursename, String branch, Long count) throws Exception {
		Course course = courseRepository.findByCoursename(coursename);
		if (course == null) {
			throw new Exception("Course not found");
		}
		course.setBranchCount(branch, count);
		courseRepository.save(course);
	}

}
