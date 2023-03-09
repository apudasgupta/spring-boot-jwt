package com.example.SpringBoot.JWT.auth.controller;

import com.example.SpringBoot.JWT.auth.AppResponse;
import com.example.SpringBoot.JWT.auth.LoginDto;
import com.example.SpringBoot.JWT.auth.exception.InvalidLoginException;
import com.example.SpringBoot.JWT.auth.exception.ResourceAlreadyExistException;
import com.example.SpringBoot.JWT.auth.model.User;
import com.example.SpringBoot.JWT.auth.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
@SecurityRequirement(name = "jwtapi")
@RestController
@RequestMapping("/authenticate")
public class AthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public AppResponse login(@RequestBody @Valid LoginDto loginDto) {
        String token = userService.signin(loginDto.getUsername(), loginDto.getPassword()).orElseThrow(()->
//                new HttpServerErrorException(HttpStatus.NOT_FOUND, "Login Failed")
                new InvalidLoginException("Login Failed")
                );
        return new AppResponse(HttpStatus.OK.value(),  "Success", LocalDateTime.now(),token);

    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public AppResponse signup(@RequestBody @Valid LoginDto loginDto){

        User user = userService.signup(loginDto.getUsername(), loginDto.getPassword(), loginDto.getFirstName(),
                loginDto.getLastName()).orElseThrow(
                        () -> new ResourceAlreadyExistException("User already exists")
//                                new HttpServerErrorException(HttpStatus.BAD_REQUEST,"User already exists")
        );

        return new AppResponse(HttpStatus.CREATED.value(), "User Created", LocalDateTime.now(),user);
    }

}
