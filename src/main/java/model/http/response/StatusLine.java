package model.http.response;

public class StatusLine {

    private static final String HTTP_VERSION = "HTTP/1.1";

    private final int statusCode;
    private final String statusText;

    public StatusLine(int statusCode, String statusText) {
        this.statusCode = statusCode;
        this.statusText = statusText;
    }

    @Override
    public String toString() {
        return HTTP_VERSION + " " + statusCode + " " + statusText;
    }
}
