package model.http.request;

import java.util.Map;
import java.util.TreeMap;
import util.HttpRequestUtils;

public class RequestBody {

    private final Map<String, String> params;

    public RequestBody(String body) {
        params = new TreeMap<>();
        params.putAll(HttpRequestUtils.parseQueryParams(body));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        params.forEach((key, value) -> stringBuilder.append(key + ": " + value + "\n"));
        return stringBuilder.toString().trim();
    }
}
