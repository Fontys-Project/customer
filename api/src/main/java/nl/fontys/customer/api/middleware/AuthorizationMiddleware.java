package nl.fontys.customer.api.middleware;

import nl.fontys.customer.api.security.jwt.TokenParser;
import nl.fontys.customer.api.security.jwt.exception.AuthorizationException;
import nl.fontys.customer.api.security.jwt.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class AuthorizationMiddleware extends OncePerRequestFilter {

    private final static String TOKEN_TYPE = "Bearer";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenParser tokenParser;

    @Autowired
    public AuthorizationMiddleware(TokenParser tokenParser) {
        this.tokenParser = tokenParser;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws AuthorizationException, IOException, ServletException {

        String rawToken = request.getHeader(AUTHORIZATION_HEADER);
        rawToken = rawToken.replace(TOKEN_TYPE, "");
        Token token = this.tokenParser.parse(rawToken);

        request.getSession().setAttribute("session", token);

        filterChain.doFilter(request, response);
    }
}
