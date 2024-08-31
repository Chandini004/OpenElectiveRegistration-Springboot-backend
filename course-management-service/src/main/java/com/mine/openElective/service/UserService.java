package com.mine.openElective.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mine.openElective.model.ElectiveUserDto;

@FeignClient(name="USER-SERVICE",url="http://localhost:4001")
public interface UserService {
	
	@GetMapping("/api/users/profile")
	public ElectiveUserDto getUserProfile(@RequestHeader("Authorization") String jwt);
}
