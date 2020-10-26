package nl.fontys.customer.api.security.jwt;

import nl.fontys.customer.api.security.jwt.exception.AuthorizationException;
import nl.fontys.customer.api.security.jwt.model.Token;

public interface TokenParser {

    Token parse(String requestToken) throws AuthorizationException;
}
