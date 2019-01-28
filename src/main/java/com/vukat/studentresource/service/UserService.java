package com.vukat.studentresource.service;

import com.vukat.studentresource.domain.User;
import com.vukat.studentresource.exception.UsernameAlreadyExistException;
import com.vukat.studentresource.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser){

       try{

           newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

           newUser.setUsername(newUser.getUsername());

           return userRepository.save(newUser);

       }catch(Exception ex){

           throw new UsernameAlreadyExistException("This e-mail already exists. Did you forget your password ? ");
       }


    }







}
