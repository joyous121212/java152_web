package com.itwill.springboot3.dto;

import com.itwill.springboot3.domain.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class EmployeeListItemDto {
	private Integer employeeId;
	private String employeeName;
	private String phoneNumber;
	private String jobTitle;
	private String departmentName;
	
	public static EmployeeListItemDto fromEntity(Employee entity) {
		// deparment와 job이 null일 경우 처리를 하기 위해서.
		String deptName = (entity.getDepartment() != null) ?
				entity.getDepartment().getDepartmentName() : null;
		String jobTitle = (entity.getJob() != null) ?
				entity.getJob().getJobTitle() : null;
		
		return EmployeeListItemDto.builder()
				.employeeId(entity.getId())
				.employeeName(entity.getFirstName() + " " + entity.getLastName())
				.phoneNumber(entity.getPhoneNumber())
				.jobTitle(jobTitle)
				.departmentName(deptName)
				.build();
	}
}
