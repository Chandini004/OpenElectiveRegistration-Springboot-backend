package com.mine.openElective.Services;
import java.util.List;

import com.mine.openElective.model.ElectiveUser;
public interface UserService {
	public ElectiveUser getUserProfile(String jwt);
	
	public List<ElectiveUser> getAllUsers(); 
	void generateAndSendRandomPasswords();
	public ElectiveUser enrollCourse(String coursename,Long userId,String jwt)throws Exception;
}
