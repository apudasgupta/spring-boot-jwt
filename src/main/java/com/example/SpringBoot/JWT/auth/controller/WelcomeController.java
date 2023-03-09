package com.example.SpringBoot.JWT.auth.controller;

import com.example.SpringBoot.JWT.auth.AppResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class WelcomeController {


    @Value("${spring.application.index}")
    String appIndex;

    @GetMapping({"/welcome", "/"})
    public String welcome() {
        return "Welcome from " + appIndex;
    }

    @PostMapping("/test")
    public AppResponse test() {
        return new AppResponse(HttpStatus.OK.value(), "Welcome from " + appIndex , LocalDateTime.now());
    }
}
