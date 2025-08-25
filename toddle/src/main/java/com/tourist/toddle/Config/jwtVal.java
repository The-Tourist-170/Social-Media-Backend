package com.tourist.toddle.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class jwtVal extends OncePerRequestFilter{


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String jwt = request.getHeader(JwtConstant.JWT_HEADER);

                if(jwt != null){
                    try{
                        String email = JwtPro.getEmailFromJwt(jwt);
                        List<GrantedAuthority> auths = new ArrayList<>();
                        Authentication auth = new UsernamePasswordAuthenticationToken(email, null, auths);
                    
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    
                    } catch (Exception e){
                        throw new BadCredentialsException("False Token");
                    }
                } 

                filterChain.doFilter(request, response);
            }

}
