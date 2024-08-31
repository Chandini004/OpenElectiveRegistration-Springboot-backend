package com.mine.openElective.Services;




import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.mine.openElective.model.CourseDto;
@FeignClient(name="COURSE-SERVICE",url="http://localhost:4002/")
public interface CourseService {
	
	@GetMapping("/api/courses/course")
	public CourseDto getCourseByCoursename(@RequestParam("coursename") String coursename,@RequestHeader("Authorization") String jwt) throws Exception;
	
	@PutMapping("/api/courses/{coursename}/{branch}/updatecount")
    void updateBranchCount(@PathVariable String coursename, @PathVariable String branch, @RequestParam Long count,@RequestHeader("Authorization") String jwt) throws Exception;
}
