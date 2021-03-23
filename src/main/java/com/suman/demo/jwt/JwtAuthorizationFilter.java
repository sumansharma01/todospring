package com.suman.demo.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwt=getJwtFromHeader(httpServletRequest);
        if(StringUtils.hasText(jwt)&& jwtProvider.validateToken(jwt)){
            String userName=jwtProvider.getUserNameFromJwt(jwt);
        }
    }

    private String getJwtFromHeader(HttpServletRequest httpServletRequest) {
     String header=httpServletRequest.getHeader("Authorization");
     String jwt="";
     if(StringUtils.hasText(header) && header.startsWith("Bearer "))
        return header.substring(7);
     return header;
    }
}
