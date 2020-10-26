package nl.fontys.customer.api.exception;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException {

    private final HttpStatus code;

    public HttpException(HttpStatus code, String message) {
        super(message);
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return this.code;
    }
}
