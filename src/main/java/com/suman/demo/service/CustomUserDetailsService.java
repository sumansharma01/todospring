package com.suman.demo.service;

import com.suman.demo.entity.UserEntity;
import com.suman.demo.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserEntity> user= Optional.ofNullable(authRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException(userName + " does not exist!")));
        return new org.springframework.security.core.userdetails.User(user.get().getUserName(),user.get().getPassword(),true,true,true,true,getAuthorties("ROLE_USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorties(String role_user) {
        return Collections.singletonList(new SimpleGrantedAuthority(role_user));
    }
}
