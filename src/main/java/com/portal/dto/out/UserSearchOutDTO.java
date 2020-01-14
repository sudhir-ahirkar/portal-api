package com.portal.dto.out;

import com.portal.model.User;

import lombok.Data;

@Data
public class UserSearchOutDTO {

	private String username;
	private String password;
	private int age;
	private Long salary;
	private Long id;
	
	public static UserSearchOutDTO toDTO(User user) {
		
		UserSearchOutDTO userSearchOutDTO=new UserSearchOutDTO();
		userSearchOutDTO.setUsername(user.getUsername());
		userSearchOutDTO.setAge(user.getAge());
		userSearchOutDTO.setSalary(user.getSalary());
		userSearchOutDTO.setId(user.getId());
		return userSearchOutDTO;
		
		
	}
}
