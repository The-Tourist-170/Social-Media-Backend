package com.tourist.toddle.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourist.toddle.Config.JwtPro;
import com.tourist.toddle.Models.User;
import com.tourist.toddle.Repository.UserRepo;
import com.tourist.toddle.Request.LoginReq;
import com.tourist.toddle.Response.AuthRes;
import com.tourist.toddle.Service.UserDetailsService;
import com.tourist.toddle.Service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Autowired PasswordEncoder passEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/signup")
    public AuthRes newUser(@RequestBody User user) throws Exception{

        User exists = userRepo.findByEmail(user.getEmail());

        if(exists != null){
            throw new Exception("User already exists.");
        }

        User nUser = new User();
        nUser.setEmail(user.getEmail());
        nUser.setfName(user.getfName());
        nUser.setlName(user.getlName());
        nUser.setPass(passEncoder.encode(user.getPass()));        

        User savedUser = userRepo.save(nUser);
        org.springframework.security.core.Authentication auth = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPass());
        String Token = JwtPro.tokenGen(auth);
        AuthRes authRes = new AuthRes(Token, "successfully Registered.");
        return authRes;
    }

    @PostMapping("/signin")    
    public AuthRes login(@RequestBody LoginReq loginReq ){
        
        Authentication auth = authenticate(loginReq.getEmail(), loginReq.getPass());

        String Token = JwtPro.tokenGen(auth);
        AuthRes authRes = new AuthRes(Token, "Successfully logged in.");
        return authRes;

    }

    private Authentication authenticate(String email, String pass) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if(userDetails == null){
            throw new BadCredentialsException("Username not valid.");
        }
        if(!passEncoder.matches(pass, userDetails.getPassword())){
            throw new BadCredentialsException("Wrong Password.");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    } 
}