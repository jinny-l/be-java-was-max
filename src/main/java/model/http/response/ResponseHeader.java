package model.http.response;

public class ResponseHeader {

    private final ContentType contentType;
    private final int contentLength;

    public ResponseHeader(String view, int contentLength) {
        contentType = ContentType.from(view);
        this.contentLength = contentLength;
    }

    @Override
    public String toString() {
        return "Content-Type: " + contentType.getValue()
                + "\n" + "Content-Length: " + contentLength;
    }
}
