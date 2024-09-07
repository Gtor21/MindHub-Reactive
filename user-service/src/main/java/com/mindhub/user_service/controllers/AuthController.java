package com.mindhub.user_service.controllers;

import com.mindhub.user_service.configuration.JwtUtils;
import com.mindhub.user_service.dtos.AuthUser;
import com.mindhub.user_service.dtos.UserDTO;
import com.mindhub.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private ReactiveAuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping("/register")
    public Mono<?> registerUser(@RequestBody UserDTO user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody AuthUser userDTO){
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()))
                .map(auth -> {
                    UserDetails userDetails = (UserDetails) auth.getPrincipal();
                    String jwt = jwtUtil.generateToken(userDetails.getUsername());
                    return ResponseEntity.ok(jwt);
                })
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

}