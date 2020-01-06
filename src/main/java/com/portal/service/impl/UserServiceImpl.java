package com.portal.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.portal.dao.UserDao;
import com.portal.dto.in.UserSearchInDTO;
import com.portal.dto.out.UserSearchOutDTO;
import com.portal.model.User;
import com.portal.model.UserDto;
import com.portal.service.UserService;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
			//authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
		//return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
	public User findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id).get();
	}

	@Override
    public User save(UserDto user) {
	    User newUser = new User();
	    newUser.setUsername(user.getUsername());
	    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setAge(user.getAge());
		newUser.setSalary(user.getSalary());
        return userDao.save(newUser);
    }

	@Override
	public Page<UserSearchOutDTO> findAll(UserSearchInDTO userSearchInDTO, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withStringMatcher(StringMatcher.CONTAINING)
				.withIgnoreCase();
		User user = new User();
	    if(!StringUtils.isEmpty(userSearchInDTO.getKeyword())) {
	    	user.setUsername(userSearchInDTO.getKeyword());
	    }
	    Example<User> userExample = Example.of(user, matcher);
	    Page<User> list = userDao.findAll(userExample, pageable);
	    
	    if(list.getContent().isEmpty()) {
	    	List<User> listUse = new ArrayList<>();
			userDao.findAll().iterator().forEachRemaining(listUse::add);
			
			List<UserSearchOutDTO> dtoList = listUse.stream().map(UserSearchOutDTO::toDTO)
		            .collect(Collectors.toList());
			  
		    
		    return new PageImpl<UserSearchOutDTO>(dtoList, list.getPageable(), listUse.size());
	    }
	    
	    List<UserSearchOutDTO> dtoList = list.getContent().stream().map(UserSearchOutDTO::toDTO)
	            .collect(Collectors.toList());
	    
	   
	    
	    return new PageImpl<UserSearchOutDTO>(dtoList, list.getPageable(), list.getTotalElements());

	}
}
