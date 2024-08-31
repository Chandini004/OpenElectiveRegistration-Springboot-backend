

package com.mine.openElective.Services;





//import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mine.openElective.model.ElectiveUser;
import com.mine.openElective.repository.UserRepository;

@Service
public class CustomUserServiceImplementation implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		ElectiveUser user = userRepository.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("user not found by email"+username);
		}
		List<GrantedAuthority>authorities= new LinkedList<GrantedAuthority>();
		

		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), authorities);
	}
}


