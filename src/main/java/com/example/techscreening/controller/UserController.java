package com.example.techscreening.controller;

import com.example.techscreening.config.JwtTokenUtil;
import com.example.techscreening.dto.AuthRequest;
import com.example.techscreening.dto.UserGetDto;
import com.example.techscreening.dto.UserPostDto;
import com.example.techscreening.model.User;
import com.example.techscreening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public UserController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserGetDto>> getAllUsers() {
        try {
            List<User> users = new ArrayList<>();
            users.addAll(userService.findAll());

            if(users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            List<UserGetDto> userDtoList = users.stream()
                    .map(user -> new UserGetDto(user))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(userDtoList, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserGetDto> getUserById(@PathVariable("id") Long id) {
        Optional<User> userData = userService.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(new UserGetDto(userData.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<UserGetDto> createUser(@Valid @RequestBody UserPostDto userPostDto) {
        try {
            User newUser = userService.create(userPostDto.toEntity());
            return new ResponseEntity<>(new UserGetDto(newUser), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserGetDto> patchUser(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserPostDto userPostDto
    ) {
        User user = userPostDto.toEntity();
        return new ResponseEntity<>(new UserGetDto(userService.update(id, user)), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users/login")
    public ResponseEntity<UserGetDto> login(@Valid @RequestBody AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            User user = (User) authenticate.getPrincipal();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                    .body(new UserGetDto(user));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
