package com.mine.openElective.repository;

import com.mine.openElective.model.ElectiveUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ElectiveUser, Long>{
	
	public ElectiveUser findByEmail(String email);
	public List<ElectiveUser> findByRole(String role);

}

