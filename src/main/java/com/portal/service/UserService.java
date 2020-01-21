package com.portal.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.portal.dto.in.UserRefDataDTO;
import com.portal.dto.in.UserSearchInDTO;
import com.portal.dto.out.UserSearchOutDTO;
import com.portal.model.User;
import com.portal.model.UserDto;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    
    Page<UserSearchOutDTO> findAll(UserSearchInDTO userSearchInDTO,Pageable pageable );
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
	void remove(Long id);
	
	UserDto update(Long userId,UserDto user);
	
    UserRefDataDTO getRefData();
}
