package nl.fontys.customer.api.model.response;

public class JsonAPIResponse {

    public static transient final String V1 = "1.0";

    private final JsonAPIVersion jsonapi;

    public JsonAPIResponse(JsonAPIVersion jsonapi) {
        this.jsonapi = jsonapi;
    }

    public JsonAPIVersion getJsonAPI() {
        return this.jsonapi;
    }
}
