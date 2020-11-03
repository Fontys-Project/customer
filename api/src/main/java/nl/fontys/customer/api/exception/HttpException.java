package nl.fontys.customer.api.exception;

import nl.fontys.customer.api.model.response.error.Error;
import nl.fontys.customer.api.model.response.error.ErrorResponse;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class HttpException extends RuntimeException {

    private final HttpStatus status;

    public HttpException(HttpStatus status, String cause) {
        super(cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }

    public ErrorResponse createErrorResponse() {
        Error error = new Error(1, new ArrayList<>(), this.getClass().getSimpleName(), this.getMessage());
        return new ErrorResponse(List.of(error));
    }
}
