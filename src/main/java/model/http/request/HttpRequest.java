package model.http.request;

import java.util.Map;
import model.http.Header;

public class HttpRequest {

    private final RequestLine requestLine;
    private final Header header;
    private final RequestBody requestBody;

    public HttpRequest(RequestLine requestLine, Header header, RequestBody requestBody) {
        this.requestLine = requestLine;
        this.header = header;
        this.requestBody = requestBody;
    }

    public String getUrl() {
        return requestLine.getUrl();
    }

    public Map<String, String> getParams() {
        return requestLine.getParams();
    }

    public String getMethod() {
        return requestLine.getMethod();
    }

    @Override
    public String toString() {
        return "\n------start-------"
                + "\n" + requestLine
                + "\n" + header
                + "\n"
                + "\n" + requestBody
                + "\n------end-------";
    }
}
