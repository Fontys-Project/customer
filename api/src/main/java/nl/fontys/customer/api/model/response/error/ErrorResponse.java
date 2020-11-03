package nl.fontys.customer.api.model.response.error;

import nl.fontys.customer.api.model.response.JsonAPIResponse;
import nl.fontys.customer.api.model.response.JsonAPIVersion;

import java.util.List;

public class ErrorResponse extends JsonAPIResponse {

    private final List<Error> errors;

    public ErrorResponse(List<Error> errors) {
        super(new JsonAPIVersion(JsonAPIResponse.V1));
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return this.errors;
    }
}
