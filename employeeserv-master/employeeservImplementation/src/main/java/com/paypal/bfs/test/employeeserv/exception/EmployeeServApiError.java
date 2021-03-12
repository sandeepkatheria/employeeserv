package com.paypal.bfs.test.employeeserv.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Data
public class EmployeeServApiError {
    private String error;
    private String message;
    private HttpStatus status;
    private String path;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public EmployeeServApiError(String error, HttpStatus status, WebRequest request){
         this.timestamp = LocalDateTime.now();
         this.status = status;
         this.error = status.getReasonPhrase();
         this.path = request.getDescription(false).substring(4);
        if(!StringUtils.isEmpty(error)){
            this.message = error;
        }
    }

    public ResponseEntity<?> buildResponseEntity() {
        return new ResponseEntity<>(this, this.getStatus());
    }

}
