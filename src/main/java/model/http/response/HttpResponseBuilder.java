package model.http.response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HttpResponseBuilder {

    public static HttpResponse build(HttpResponse httpResponse, String view, int statusCode, String statusText)
            throws IOException {
        byte[] body = Files.readAllBytes(Path.of(view));
        StatusLine statusLine = new StatusLine(statusCode, statusText);
        ResponseHeader responseHeader = new ResponseHeader(view, body.length);

        httpResponse.setBody(body);
        httpResponse.setStatusLine(statusLine);
        httpResponse.setResponseHeader(responseHeader);
        return httpResponse;
    }
}
