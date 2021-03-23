package com.suman.demo.service;

import com.suman.demo.dto.RegisterRequest;
import com.suman.demo.entity.UserEntity;
import com.suman.demo.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(RegisterRequest registerRequest){
        UserEntity userEntity=new UserEntity();
        userEntity.setUserName(registerRequest.getUserName());
        userEntity.setEmail(registerRequest.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        authRepository.save(userEntity);
    }
}
