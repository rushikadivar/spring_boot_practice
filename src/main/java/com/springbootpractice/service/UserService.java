package com.springbootpractice.service;

import com.springbootpractice.entity.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);

    public List<User> fetchAllUsers();

    public User fetchUserById(Integer id);
}
