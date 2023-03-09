package com.example.SpringBoot.JWT.auth;

import com.example.SpringBoot.JWT.auth.exception.InvalidLoginException;
import com.example.SpringBoot.JWT.auth.exception.ResourceAlreadyExistException;
import com.example.SpringBoot.JWT.auth.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@RestControllerAdvice
public class ExceptionHandlerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerController.class);

    /*@Bean
    public ErrorAttributes errorAttributes() {
        // Hide exception field in the return object
        return new DefaultErrorAttributes() {

//            @Override
            public Map<String, Object> getErrorAttributes(WebRequest requestAttributes, boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
                errorAttributes.remove("exception");
                return errorAttributes;
            }
        };
    }*/


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public AppResponse handleAccessDeniedException(AccessDeniedException ex, HttpServletResponse res) throws IOException {
//        res.sendError(HttpStatus.FORBIDDEN.value(), "Access denied");
        return new AppResponse(HttpStatus.FORBIDDEN.value(), "Access denied :(",LocalDateTime.now());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public AppResponse handleHttpServerErrorException(HttpServerErrorException ex, HttpServletResponse res) throws IOException {
//        res.sendError(ex.getStatusCode().value(),ex.getMessage());
        return new AppResponse(ex.getStatusCode().value(),ex.getMessage(),LocalDateTime.now());
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public AppResponse handleInsufficientAuthenticationException(InsufficientAuthenticationException ex, HttpServletResponse res) throws IOException {
        LOGGER.error("Handled Insufficient Authentication Exception", ex);
//        res.sendError(HttpStatus.FORBIDDEN.value(), "Insufficient Authentication");
        return new AppResponse(HttpStatus.NOT_FOUND.value(), "Insufficient Authentication",LocalDateTime.now());
    }


    @ExceptionHandler(InvalidLoginException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public AppResponse handleInvalidLoginException(Exception ex, HttpServletResponse res) throws IOException {
        return new AppResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(),LocalDateTime.now());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public AppResponse handleResourceNotFoundException(Exception ex, HttpServletResponse res) throws IOException {
        return new AppResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(),LocalDateTime.now());
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public AppResponse handleResourceAlreadyExistException(Exception ex, HttpServletResponse res) throws IOException {
        return new AppResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),LocalDateTime.now());
    }


    @ExceptionHandler(Exception.class)
    public AppResponse handleException(Exception ex, HttpServletResponse res) throws IOException {
        LOGGER.error("Handled Internal Error Exception", ex);
//        res.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong");
        return new AppResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong",LocalDateTime.now());
    }

}
