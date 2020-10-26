package nl.fontys.customer.api.exception;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException {

    private final HttpStatus code;

    public HttpException(HttpStatus code) {
        this.code = code;
    }

    public HttpStatus getCode() {
        return this.code;
    }
}
