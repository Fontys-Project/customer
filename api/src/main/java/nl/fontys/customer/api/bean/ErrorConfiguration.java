package nl.fontys.customer.api.bean;

import nl.fontys.customer.api.exception.HttpException;
import nl.fontys.customer.api.model.response.error.ErrorResponse;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ErrorConfiguration {

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {

            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
                Map<String, Object> responseKeys = new LinkedHashMap<>();

                Throwable error = this.getError(webRequest);
                if (error instanceof HttpException) {
                    ErrorResponse response = ((HttpException) error).createErrorResponse();
                    responseKeys.put("jsonapi", response.getJsonAPI());
                    responseKeys.put("errors", response.getErrors());
                }

                return responseKeys;
            }
        };
    }
}
