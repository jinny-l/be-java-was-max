package model.http.request;

import java.util.Map;

public class RequestLine {

    private final String method;
    private final RequestTarget requestTarget;
    private final String httpVersion;

    public RequestLine(String[] requestLine) {
        method = requestLine[0];
        requestTarget = new RequestTarget(requestLine[1]);
        httpVersion = requestLine[2];
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return requestTarget.getPath();
    }

    public Map<String, String> getParams() {
        return requestTarget.getParams();
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
