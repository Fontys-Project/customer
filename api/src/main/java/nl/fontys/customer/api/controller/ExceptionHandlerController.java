package nl.fontys.customer.api.controller;

import nl.fontys.customer.api.model.response.error.Error;
import nl.fontys.customer.api.model.response.error.ErrorResponse;
import nl.fontys.customer.api.security.jwt.exception.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorResponse> configParseException(AuthorizationException ex) {
        return this.createErrorResponse(HttpStatus.UNAUTHORIZED, ex);
    }

    @ResponseBody
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorResponse> handleAll(Exception e) {
        e.printStackTrace();
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
                "An unexpected exception occured.");
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus status, Exception ex) {
        return createErrorResponse(status, ex.getClass().getSimpleName(), ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> createErrorResponse(HttpStatus status, String shortMessage, String message) {
        Error error = new Error(1, new ArrayList<>(), shortMessage, message);

        ErrorResponse response = new ErrorResponse(List.of(error));
        return ResponseEntity.status(status).body(response);
    }
}
