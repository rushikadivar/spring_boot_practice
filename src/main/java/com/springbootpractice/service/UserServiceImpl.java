package com.springbootpractice.service;

import com.springbootpractice.entity.User;
import com.springbootpractice.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    public User saveUser(User user){
        return userRepo.save(user);
    }

    @Override
    public List<User> fetchAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User fetchUserById(Integer id) {
        return userRepo.findById(id).get();
    }


}
