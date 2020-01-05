package com.devglan.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devglan.dto.in.UserSearchInDTO;
import com.devglan.dto.out.UserSearchOutDTO;
import com.devglan.model.User;
import com.devglan.model.UserDto;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    
    Page<UserSearchOutDTO> findAll(UserSearchInDTO userSearchInDTO,Pageable pageable );
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
