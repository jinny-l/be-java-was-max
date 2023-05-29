package model.http.response;

public class HttpResponse {

    private StatusLine statusLine;
    private ResponseHeader responseHeader;
    private byte[] body;

    public HttpResponse() {
    }

    public void setStatusLine(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public StatusLine getStatusLine() {
        return statusLine;
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public byte[] getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "\n------start-------"
                + "\n" + statusLine
                + "\n" + responseHeader
                + "\n------end-------";
    }
}
