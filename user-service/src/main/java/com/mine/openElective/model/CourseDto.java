package com.mine.openElective.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
	private Long id;
	private String coursename;
	private String coursebranch;
	private String description;
	private Long totalcount;
	private Long csecount;
	private Long ececount;
	private Long civilcount;
	private Long eeecount;
	private Long mechcount;
	private Long bshcount;	
	
	public void setBranchCount(String branch, Long count) {
	    switch (branch) {
	        case "CSE":
	            this.csecount = count;
	            break;
	        case "ECE":
	            this.ececount = count;
	            break;
	        case "CIVIL":
	            this.civilcount = count;
	            break;
	        case "EEE":
	            this.eeecount = count;
	            break;
	        case "MECH":
	            this.mechcount = count;
	            break;
	        case "BSH":
	            this.bshcount = count;
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid branch");
	    }
	}
}

