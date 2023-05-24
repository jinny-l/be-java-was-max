package model.http.request;

import java.util.Map;

public class HttpRequest {

    private final RequestLine requestLine;
    private final RequestHeader requestHeader;

    public HttpRequest(RequestLine requestLine, RequestHeader requestHeader) {
        this.requestLine = requestLine;
        this.requestHeader = requestHeader;
    }

    public String getUrl() {
        return requestLine.getUrl();
    }

    public Map<String, String> getParams() {
        return requestLine.getParams();
    }

    @Override
    public String toString() {
        return "------start-------" +
                "\nHttpRequest{" +
                "\nrequestLine=" + requestLine +
                ", \nrequestHeader=" + requestHeader +
                '}' +
                "\n------end-------";
    }
}
