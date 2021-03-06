package nl.fontys.customer.api.security.jwt.exception;

import nl.fontys.customer.api.exception.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthorizationException extends HttpException {

    public AuthorizationException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
