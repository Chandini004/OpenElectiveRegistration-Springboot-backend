package com.mine.openElective.service;

import java.util.List;


import com.mine.openElective.model.Course;


public interface CourseService {
	Course createCourse(Course course,String requesterRole)throws Exception;
	
	Course getCourseById(Long id) throws Exception;
	
	Course getCourseByCoursename(String coursename) throws Exception;
	
	List<Course> getAllCourses();
	
	Course updateCourse(Long id,Course updatedCourse,String requesterRole)throws Exception;
	
    void deleteCourse(Long id)throws Exception;
    
    void updateBranchCount(String coursename, String branch,Long count) throws Exception;
	 
}
