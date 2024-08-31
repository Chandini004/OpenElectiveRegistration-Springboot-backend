package com.mine.openElective.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.mine.openElective.model.Course;

public interface CourseRepository extends JpaRepository<Course,Long> {

	
	public Course findByCoursename(String coursename);
}

