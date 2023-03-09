package com.example.SpringBoot.JWT.auth.config;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//@Configuration
//@EnableSwagger2

public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
    private final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);



}