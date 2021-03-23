package com.suman.demo.jwt;

import com.suman.demo.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String jwt=getJwtFromHeader(httpServletRequest);
        if(StringUtils.hasText(jwt)&& jwtProvider.validateToken(jwt)){
            String userName=jwtProvider.getUserNameFromJwt(jwt);


            //finding the user in database from userdetailsservice
            UserDetails userDetails=customUserDetailsService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null);
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
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
