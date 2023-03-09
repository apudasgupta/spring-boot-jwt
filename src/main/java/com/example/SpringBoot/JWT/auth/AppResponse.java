package com.example.SpringBoot.JWT.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppResponse {
    int status;
    String message;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime date;

    Object data;

    public AppResponse(int status, LocalDateTime date) {
        this.status = status;
        this.date = date;
    }

    public AppResponse(int status, String message, LocalDateTime date) {
        this.status = status;
        this.message = message;
        this.date = date;
    }




}
