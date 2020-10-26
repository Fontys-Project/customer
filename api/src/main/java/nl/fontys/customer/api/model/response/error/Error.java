package nl.fontys.customer.api.model.response.error;

import java.util.List;

public class Error {

    private final int code;
    private final List<Source> source;
    private final String title;
    private final String detail;

    public Error(int code, List<Source> source, Throwable cause) {
        this(code, source, cause.getClass().getSimpleName(), cause.getMessage());
    }

    public Error(int code, List<Source> source, String title, String detail) {
        this.code = code;
        this.source = source;
        this.title = title;
        this.detail = detail;
    }

    public int getCode() {
        return this.code;
    }

    public List<Source> getSource() {
        return this.source;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDetail() {
        return this.detail;
    }
}
