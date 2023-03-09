package com.example.SpringBoot.JWT.auth.controller;

import com.example.SpringBoot.JWT.auth.exception.ResourceNotFoundException;
import com.example.SpringBoot.JWT.auth.model.User;
import com.example.SpringBoot.JWT.auth.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;


@SecurityRequirement(name = "jwtapi")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/findById")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User findById(@RequestParam Long id) {
        return userService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User Not Found"));
    }
}
