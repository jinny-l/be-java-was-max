package model;

public class RequestLine {

    private final String method;
    private final String requestTarget;
    private final String httpVersion;

    public RequestLine(String[] requestLine) {
        method = requestLine[0];
        requestTarget = requestLine[1];
        httpVersion = requestLine[2];
    }

    public String getMethod() {
        return method;
    }

    public String getRequestTarget() {
        return requestTarget;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    @Override
    public String toString() {
        return "RequestLine{" +
                "method='" + method + '\'' +
                ", requestTarget='" + requestTarget + '\'' +
                ", httpVersion='" + httpVersion + '\'' +
                '}';
    }
}
