package com.portal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portal.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
