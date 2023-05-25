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

    public byte[] getBody() {
        return body;
    }

    public int getBodyLength() {
        return body.length;
    }

    @Override
    public String toString() {
        return statusLine + "\n"
                + responseHeader
                + "\r\n";
    }
}
