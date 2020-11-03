package nl.fontys.customer.api.controller;

import nl.fontys.customer.api.exception.HttpException;
import nl.fontys.customer.api.model.response.error.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        return ResponseEntity.status(status).body(this.errorResponseFromException(new HttpException(status,
                ex.getMessage())));
    }

    @ResponseBody
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ErrorResponse> configParseException(HttpException ex) {
        return this.exceptionAsResponseEntity(ex);
    }

    @ResponseBody
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorResponse> handleAll(Exception e) {
        e.printStackTrace();
        return this.exceptionAsResponseEntity(new HttpException(HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected exception occurred."));
    }

    private ResponseEntity<ErrorResponse> exceptionAsResponseEntity(HttpException ex) {
        return ResponseEntity.status(ex.getStatus()).body(errorResponseFromException(ex));
    }

    private ErrorResponse errorResponseFromException(HttpException exception) {
        return exception.createErrorResponse();
    }
}
