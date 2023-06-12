package model.http.response;

import model.http.Header;

public class HttpResponse {

    private StatusLine statusLine;
    private Header header;
    private byte[] body;

    public HttpResponse() {
        this.header = new Header();
    }

    public void setStatusLine(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public StatusLine getStatusLine() {
        return statusLine;
    }

    public Header getHeader() {
        return header;
    }

    public byte[] getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "\n------start-------"
                + "\n" + statusLine
                + "\n" + header
                + "\n------end-------";
    }
}
