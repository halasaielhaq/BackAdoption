package com.test.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object data) {
        // Create a response body with a message and data
        ApiResponse<Object> responseBody = new ApiResponse<>(message, status.value(), data);

        // Return the ResponseEntity with the specified status
        return new ResponseEntity<>(responseBody, status);
    }

    public static class ApiResponse<T> {
        private final String message;
        private final int status;
        private final T data;

        public ApiResponse(String message, int status, T data) {
            this.message = message;
            this.status = status;
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }

        public T getData() {
            return data;
        }
    }
}
