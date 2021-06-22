package com.example.spring_boot.service;

import com.example.spring_boot.controller.entity.UserEntity;
import com.example.spring_boot.exceptions.UserAlreadyExistException;
import com.example.spring_boot.exceptions.UserNotFoundException;
import com.example.spring_boot.model.User;
import com.example.spring_boot.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException{
        if(userRepo.findByUsername(user.getUsername()) !=null)
            throw  new UserAlreadyExistException("User with this username is exist, choose another username");
        return userRepo.save(user);
    }
    public User getOne(Long id) throws UserNotFoundException{
        UserEntity user = userRepo.findById(id).get();
        if(user == null){
            throw  new UserNotFoundException("User doesn't exist");
        }
        return User.toModel(user);
    }
    public Long delete(Long id){
        userRepo.deleteById(id);
        return id;
    }
}
