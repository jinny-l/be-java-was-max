package model.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.http.Header;

public class HttpRequestBuilder {

    public static HttpRequest build(BufferedReader br) throws IOException {
        RequestLine requestLine = new RequestLine(br.readLine().split(" "));
        Header header = parseRequestHeader(br);

        int contentLength = header.getContentLength();
        RequestBody requestBody = parseRequestBody(br, contentLength);
        return new HttpRequest(requestLine, header, requestBody);
    }

    private static Header parseRequestHeader(BufferedReader br) throws IOException {
        List<String> requestHeaders = new ArrayList<>();
        String oneLine;
        while (!(oneLine = br.readLine()).equals("")) {
            requestHeaders.add(oneLine);
        }
        return new Header(requestHeaders);
    }

    private static RequestBody parseRequestBody(BufferedReader br, int contentLength) throws IOException {
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < contentLength; i++) {
            body.append(br.read());
        }
        return new RequestBody(body.toString());
    }
}
