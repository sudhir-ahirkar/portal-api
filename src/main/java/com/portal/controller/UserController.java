package com.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.portal.dto.in.UserSearchInDTO;
import com.portal.dto.out.UserSearchOutDTO;
import com.portal.model.User;
import com.portal.model.UserDto;
import com.portal.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users", method = RequestMethod.POST)
    public ResponseEntity<Page<UserSearchOutDTO>> listUser(@RequestBody UserSearchInDTO userSearchInDTO, 
    	    Pageable pageable){
        Page<UserSearchOutDTO> userList=userService.findAll(userSearchInDTO,pageable);
         
         return new ResponseEntity<Page<UserSearchOutDTO>>(userList,HttpStatus.OK);
    }
    
    

    //@Secured("ROLE_USER")
//    @PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getOne(@PathVariable(value = "id") Long id){
        return userService.findById(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable(value = "id") Long userId){
         userService.remove(userId);
    }
    

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }
    
    @RequestMapping(value="/users/{id}",method = RequestMethod.PUT)
    public UserDto updateUser(@PathVariable(value ="id") Long userId,@RequestBody UserDto user){
        return userService.update(userId,user);
    }



}
