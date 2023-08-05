package com.fullstackbd.tahsin.student.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    private String message;
    private Boolean result;
    private Integer statusCode;

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode.value();
    }
}
