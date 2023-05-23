package model.http.request;

public class HttpRequest {

    private final RequestLine requestLine;
    private final RequestHeader requestHeader;

    public HttpRequest(RequestLine requestLine, RequestHeader requestHeader) {
        this.requestLine = requestLine;
        this.requestHeader = requestHeader;
    }

    public String getPath() {
        return requestLine.getPath();
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "requestLine=" + requestLine +
                ", requestHeader=" + requestHeader +
                '}';
    }
}
