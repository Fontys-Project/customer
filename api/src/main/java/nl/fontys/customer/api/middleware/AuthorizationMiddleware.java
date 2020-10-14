package nl.fontys.customer.api.middleware;

import nl.fontys.customer.api.security.jwt.TokenParser;
import nl.fontys.customer.api.security.jwt.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthorizationMiddleware extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenParser tokenParser;

    @Autowired
    public AuthorizationMiddleware(TokenParser tokenParser) {
        this.tokenParser = tokenParser;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException {

        String rawToken = request.getHeader(AUTHORIZATION_HEADER);
        Token token = this.validateToken(rawToken);
    }

    private Token validateToken(String rawToken) throws ServletException {
        try {
            return this.tokenParser.parse(rawToken);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
