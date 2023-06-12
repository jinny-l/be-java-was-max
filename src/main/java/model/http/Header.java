package model.http;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import util.HttpRequestUtils;

public class Header {

    private final Map<String, List<String>> headers;

    public Header() {
        headers = new TreeMap<>();
    }

    public Header(List<String> requestHeaders) {
        headers = new TreeMap<>();
        parseRequestHeader(requestHeaders);
    }

    public void add(String key, List<String> values) {
        headers.put(key, values);
    }

    public int getContentLength() {
        return Integer.parseInt(headers.getOrDefault("Content-Length", List.of("0")).get(0)); // TODO: flatMap으로 어떻게 안되나,, 흠
    }

    private void parseRequestHeader(List<String> requestHeaders) {
        requestHeaders.forEach(requestHeader ->
                headers.putAll(HttpRequestUtils.parseOneLineOfHeader(requestHeader))
        );
    }

    @Override
    public String toString() {
        return headers.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + String.join(", ", entry.getValue()))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
