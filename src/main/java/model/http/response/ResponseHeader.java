package model.http.response;

public class ResponseHeader {

    private final String contentType;
    private final int contentLength;

    public ResponseHeader(String url, int contentLength) {
        contentType = ContentType.valueFrom(url);
        this.contentLength = contentLength;
    }

    @Override
    public String toString() {
        return "Content-Type: " + contentType
                + "\n" + "Content-Length: " + contentLength;
    }
}
