package com.mine.openElective.model;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ElectiveUserDto {
	private Long id;
	private String password;
	private String email;
	private String role;
	private String fullName;
}
