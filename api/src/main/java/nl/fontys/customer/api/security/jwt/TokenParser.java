package nl.fontys.customer.api.security.jwt;

import nl.fontys.customer.api.security.jwt.exception.TokenValidationException;
import nl.fontys.customer.api.security.jwt.model.Token;

public interface TokenParser {

    Token parse(String requestToken) throws TokenValidationException;
}
