package com.example.security.controller;

import com.example.security.config.JwtUtile;
import com.example.security.dao.UserDao;
import com.example.security.model.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager manager;
    private final UserDao userDao;
    private final JwtUtile jwtUtile;
    @PostMapping("authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        final UserDetails user = userDao.finUserDetails(request.getEmail());
        if(user != null){
            return ResponseEntity.ok(jwtUtile.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some Error has occured");
    }
}
