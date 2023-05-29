package model.http.request;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import util.HttpRequestUtils;

public class RequestHeader {

    private final Map<String, List<String>> headers;

    public RequestHeader(List<String> requestHeaders) {
        headers = new TreeMap<>();
        parseRequestHeader(requestHeaders);
    }

    private void parseRequestHeader(List<String> requestHeaders) {
        requestHeaders.forEach(requestHeader ->
                headers.putAll(HttpRequestUtils.parseOneLineOfHeader(requestHeader))
        );
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        headers.forEach((key, value) -> stringBuilder.append(key + ": " + value + "\n"));
        return stringBuilder.toString().trim();
    }
}
