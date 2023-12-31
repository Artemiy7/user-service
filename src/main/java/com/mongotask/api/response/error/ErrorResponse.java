package com.mongotask.api.response.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private HttpStatus httpStatus;
    private String path;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime timestamp;
    private String message;
}