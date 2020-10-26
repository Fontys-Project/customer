package nl.fontys.customer.api.controller;

import nl.fontys.customer.api.model.response.error.Error;
import nl.fontys.customer.api.model.response.error.ErrorResponse;
import nl.fontys.customer.api.security.jwt.exception.AuthorizationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@EnableWebMvc
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorResponse> configParseException(HttpServletRequest req, AuthorizationException exception) {
        Error error = new Error(1, new ArrayList<>(), exception.getClass().getSimpleName(),
                exception.getMessage());

        ErrorResponse response = new ErrorResponse(List.of(error));
        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }
}
