package model.http.request;

import java.util.Collections;
import java.util.Map;
import util.HttpRequestUtils;

public class RequestTarget {

    private static final String QUERY_STRING_DELIMITER = "?";
    private static final int URL_INDEX = 0;

    private final String url;
    private final String path;
    private final Map<String, String> params;

    public RequestTarget(String requestTarget) {
        url = requestTarget;
        path = parsePath(requestTarget);
        params = parseQueryString(requestTarget);
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getParams() {
        return Collections.unmodifiableMap(params);
    }

    private String parsePath(String requestTarget) {
        if (hasQueryString(requestTarget)) {
            return requestTarget.split("\\?")[URL_INDEX];
        }
        return requestTarget;
    }

    private Map<String, String> parseQueryString(String requestTarget) {
        if (hasQueryString(requestTarget)) {
            return HttpRequestUtils.parseQueryParams(requestTarget);
        }
        return Collections.emptyMap();
    }

    private boolean hasQueryString(String requestTarget) {
        return requestTarget.contains(QUERY_STRING_DELIMITER);
    }

    @Override
    public String toString() {
        return url;
    }
}
