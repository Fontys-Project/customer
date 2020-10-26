package nl.fontys.customer.api.security.jwt.exception;

import nl.fontys.customer.api.exception.HttpException;
import org.springframework.http.HttpStatus;

public class MissingTokenException extends HttpException {

    public MissingTokenException() {
        super(HttpStatus.UNAUTHORIZED);
    }
}
