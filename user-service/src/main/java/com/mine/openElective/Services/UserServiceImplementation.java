package com.mine.openElective.Services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mine.openElective.config.JwtProvider;
import com.mine.openElective.model.CourseDto;
import com.mine.openElective.model.ElectiveUser;
import com.mine.openElective.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CourseService courseService;
	
	 @Autowired
	 private JavaMailSender mailSender;
	 
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	@Override
	public ElectiveUser getUserProfile(String jwt) {
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		return userRepository.findByEmail(email);
	}
	
	@Override
	public List<ElectiveUser> getAllUsers() {
		
		return userRepository.findAll();
	}


	
	@Override
	public ElectiveUser enrollCourse(String coursename, Long userId, String jwt) throws Exception {
	    Optional<ElectiveUser> userOptional = userRepository.findById(userId);
	    if (!userOptional.isPresent()) {
	        throw new Exception("User not found");
	    }

	    ElectiveUser user = userOptional.get();
	    if (!user.getRole().equals("student")) {
	        throw new Exception("Only students can enroll in courses");
	    }
	    CourseDto course = courseService.getCourseByCoursename(coursename,jwt);
//	    <CourseDto> courseOptional = courseService.getCourseByCoursename(coursename);
	   

//	    Course course = courseOptional.get();
	    
	    String userBranch = user.getBranch();
	    Long branchCount = 0L;
	    switch (userBranch) {
	        case "CSE":
	            branchCount = course.getCsecount();
	            break;
	        case "ECE":
	            branchCount = course.getEcecount();
	            break;
	        case "CIVIL":
	            branchCount = course.getCivilcount();
	            break;
	        case "EEE":
	            branchCount = course.getEeecount();
	            break;
	        case "MECH":
	            branchCount = course.getMechcount();
	            break;
	        case "BSH":
	            branchCount = course.getBshcount();
	            break;
	        default:
	            throw new Exception("Invalid branch");
	    }

	    if (branchCount == 0) {
	        throw new Exception("Seats for your branch are filled");
	    }
	    
	    courseService.updateBranchCount(coursename,userBranch, branchCount - 1,jwt);
	    user.setCourse(coursename);
	    return userRepository.save(user);
	}
	@Override
	public void generateAndSendRandomPasswords() {
		String role="student";
        List<ElectiveUser> students = userRepository.findByRole(role);
        for (ElectiveUser student : students) {
        	String rawPassword = generateRandomPassword();
            String hashedPassword = passwordEncoder.encode(rawPassword); // Hash the password
            student.setPassword(hashedPassword);
            userRepository.save(student);
            sendPasswordResetEmail(student, rawPassword);
        }
    }
	private String generateRandomPassword() {
		int length = 10; // length of the password
	    String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
	    String upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String digits = "0123456789";
	    String allChars = lowerCaseChars + upperCaseChars + digits;
	    Random random = new Random();
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < length; i++) {
	        sb.append(allChars.charAt(random.nextInt(allChars.length())));
	    }
	    return sb.toString();
	}

	private void sendPasswordResetEmail(ElectiveUser user, String newPassword) {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(user.getEmail());
	    message.setSubject("Your new password");
	    message.setText("Hello " + user.getFullName() + ",\n\nYour new password is: " + newPassword + "\n\nPlease log in to your account and change your password as soon as possible.");
	    mailSender.send(message);
	}
	
}
