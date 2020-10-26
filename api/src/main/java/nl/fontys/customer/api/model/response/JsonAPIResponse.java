package nl.fontys.customer.api.model.response;

public class JsonAPIResponse {

    public static final String V1 = "1.0";

    private final String version;

    public JsonAPIResponse(String version) {
        this.version = version;
    }

    public String getVersion() {
        return this.version;
    }
}
