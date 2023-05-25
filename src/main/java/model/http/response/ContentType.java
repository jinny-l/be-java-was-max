package model.http.response;

import java.util.Arrays;

public enum ContentType {

    HTML(".html", "text/html;charset=utf-8"),
    CSS(".css", "text/css;charset=utf-8"),
    JAVASCRIPT(".js", "application/javascript;charset=utf-8"),
    PNG(".png", "image/png"),
    FAVICON(".ico", "image/avif");

    private final String fileExtension;
    private final String value;

    ContentType(String fileExtension, String value) {
        this.fileExtension = fileExtension;
        this.value = value;
    }

    public static String valueFrom(String requestTarget) {
        return Arrays.stream(ContentType.values())
                .filter(contentType -> requestTarget.endsWith(contentType.fileExtension))
                .findAny()
                .map(contentType -> contentType.value)
                .orElse(null);
    }
}
