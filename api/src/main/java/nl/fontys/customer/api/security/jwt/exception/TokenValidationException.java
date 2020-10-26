package nl.fontys.customer.api.security.jwt.exception;

public class TokenValidationException extends Exception {

    public TokenValidationException(Throwable cause) {
        super(cause);
    }
}
