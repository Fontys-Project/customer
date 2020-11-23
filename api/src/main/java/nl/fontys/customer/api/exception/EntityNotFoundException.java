package nl.fontys.customer.api.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends HttpException {

    public EntityNotFoundException(String cause) {
        super(HttpStatus.NOT_FOUND, cause);
    }
}
