package com.mine.openElective;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
@EnableFeignClients
public class CourseManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseManagementServiceApplication.class, args);
	}

}
