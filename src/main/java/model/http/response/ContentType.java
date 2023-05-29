package model.http.response;

import java.util.Arrays;

public enum ContentType {

    HTML(".html", "text/html;charset=utf-8"),
    CSS(".css", "text/css;charset=utf-8"),
    JAVASCRIPT(".js", "application/javascript;charset=utf-8"),
    PNG(".png", "image/png"),
    FAVICON(".ico", "image/avif"),
    WEB_OPEN_FONT_FORMAT(".woff", "font/woff"),
    TRUE_TYPE_FONT(".ttf", "font/ttf");

    private final String fileExtension;
    private final String value;

    ContentType(String fileExtension, String value) {
        this.fileExtension = fileExtension;
        this.value = value;
    }

    public static ContentType from(String requestTarget) {
        return Arrays.stream(ContentType.values())
                .filter(contentType -> requestTarget.endsWith(contentType.fileExtension))
                .findAny()
                .orElse(HTML);
    }

    public String getValue() {
        return value;
    }
}
