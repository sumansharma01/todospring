package com.suman.demo.controller;

import com.suman.demo.dto.RegisterRequest;
import com.suman.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest){
        authService.registerUser(registerRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
}
